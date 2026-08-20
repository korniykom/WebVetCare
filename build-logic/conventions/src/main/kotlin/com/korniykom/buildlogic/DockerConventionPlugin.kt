package com.korniykom.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Exec
import org.springframework.boot.buildpack.platform.build.PullPolicy
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

class DockerConventionPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        val extension = extensions.create("docker", DockerExtension::class.java)
        extension.namespace.convention(providers.gradleProperty("namespace"))
        extension.registryUrl.convention(providers.gradleProperty("registryUrl"))
        extension.jvmVersion.convention("17")
        extension.imageTag.convention(
            providers.gradleProperty("version").orElse(project.version.toString())
        )

        val serviceName = project.name.lowercase()
        val localImageName: Provider<String> =
            provider { "${extension.namespace.get()}-$serviceName" }
        val localImageRef: Provider<String> =
            provider { "${localImageName.get()}:${extension.imageTag.get()}" }
        val fullImageRef: Provider<String> =
            provider { "${extension.registryUrl.get()}/${localImageRef.get()}" }

        plugins.withId("org.springframework.boot") {
            check(extension.dockerfileBuildTaskDependency.orNull == null) {
                "Module '${project.name}' applies org.springframework.boot and also sets " +
                        "dockerfileBuildTaskDependency — pick one Docker build strategy, not both."
            }

            tasks.named("bootBuildImage", BootBuildImage::class.java) {
                imageName.set(localImageRef)
                imagePlatform.set("linux/amd64")
                pullPolicy.set(PullPolicy.IF_NOT_PRESENT)
                environment.set(provider {
                    mapOf("BP_JVM_VERSION" to extension.jvmVersion.get())
                })
            }

            tasks.register("tagImage", Exec::class.java) {
                dependsOn("bootBuildImage")
                notCompatibleWithConfigurationCache("Uses extension values in doFirst")
                executable = "docker"
                doFirst {
                    args = listOf("tag", localImageRef.get(), fullImageRef.get())
                }
            }

            val cleanupImage = tasks.register("cleanupImage", Exec::class.java) {
                notCompatibleWithConfigurationCache("Uses extension values in doFirst")
                executable = "docker"
                isIgnoreExitValue = true
                doFirst {
                    args = listOf("rmi", "-f", localImageRef.get(), fullImageRef.get())
                }
            }

            tasks.register("pushImage", Exec::class.java) {
                dependsOn("tagImage")
                notCompatibleWithConfigurationCache("Uses extension values in doFirst")
                executable = "docker"
                doFirst {
                    args = listOf("push", fullImageRef.get())
                }
                finalizedBy(cleanupImage)
            }
        }

        afterEvaluate {
            extension.dockerfileBuildTaskDependency.orNull?.let { buildDep ->
                tasks.register("buildImage", Exec::class.java) {
                    dependsOn(buildDep)
                    notCompatibleWithConfigurationCache("Uses extension values in doFirst")
                    executable = "docker"
                    doFirst {
                        args = listOf("build", "-t", fullImageRef.get(), ".")
                    }
                }

                val cleanupImage = tasks.register("cleanupImage", Exec::class.java) {
                    notCompatibleWithConfigurationCache("Uses extension values in doFirst")
                    executable = "docker"
                    isIgnoreExitValue = true
                    doFirst {
                        args = listOf("rmi", "-f", fullImageRef.get())
                    }
                }

                tasks.register("pushImage", Exec::class.java) {
                    dependsOn("buildImage")
                    notCompatibleWithConfigurationCache("Uses extension values in doFirst")
                    executable = "docker"
                    doFirst {
                        args = listOf("push", fullImageRef.get())
                    }
                    finalizedBy(cleanupImage)
                }
            }
        }
    }
}