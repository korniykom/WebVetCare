package com.korniykom.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.springframework.boot.buildpack.platform.build.PullPolicy
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

class DockerConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        val extension = extensions.create("docker", DockerExtension::class.java)

        extension.namespace.convention(providers.gradleProperty("namespace"))
        extension.registryUrl.convention(providers.gradleProperty("registryUrl"))
        extension.jvmVersion.convention("17")
        extension.imageTag.convention(project.version.toString())

        val serviceName = project.name

        val localImageName = provider { "${extension.namespace.get()}-$serviceName" }
        val localImageRef = provider { "${localImageName.get()}:${extension.imageTag.get()}" }
        val fullImageRef = provider { "${extension.registryUrl.get()}/${localImageRef.get()}" }

        plugins.withId("org.springframework.boot") {
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

            tasks.register("pushImage", Exec::class.java) {
                dependsOn("tagImage")
                notCompatibleWithConfigurationCache("Uses extension values in doFirst")
                executable = "docker"
                doFirst {
                    args = listOf("push", fullImageRef.get())
                }
            }
        }
    }
}