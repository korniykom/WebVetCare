package com.korniykom.buildlogic
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.springframework.boot.buildpack.platform.build.PullPolicy
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

class DockerConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        val extension = extensions.create("docker", DockerExtension::class.java)
        val serviceName = project.name

        plugins.withId("org.springframework.boot") {
            tasks.named("bootBuildImage", BootBuildImage::class.java) {
                imageName.set(provider {
                    val imageName = "${extension.namespace.get()}-$serviceName"
                    val imageTag = extension.imageTag.get()
                    "$imageName:$imageTag"
                })
                imagePlatform.set("linux/amd64")
                pullPolicy.set(PullPolicy.IF_NOT_PRESENT)
                environment.set(mapOf(
                    "BP_JVM_VERSION" to extension.jvmVersion.getOrElse("17")
                ))
            }

            tasks.register("tagImage", Exec::class.java) {
                dependsOn("bootBuildImage")
                notCompatibleWithConfigurationCache("Uses extension values in doFirst")

                executable = "docker"
                doFirst {
                    val imageName = "${extension.namespace.get()}-$serviceName"
                    val imageTag = extension.imageTag.get()
                    val fullImageName = "${extension.registryUrl.get()}/$imageName:$imageTag"
                    args = listOf(
                        "tag",
                        "$imageName:$imageTag",
                        fullImageName
                    )
                }
            }

            tasks.register("pushImage", Exec::class.java) {
                dependsOn("tagImage")
                notCompatibleWithConfigurationCache("Uses extension values in doFirst")

                executable = "docker"
                doFirst {
                    val imageName = "${extension.namespace.get()}-$serviceName"
                    val imageTag = extension.imageTag.get()
                    val fullImageName = "${extension.registryUrl.get()}/$imageName:$imageTag"
                    args = listOf(
                        "push",
                        fullImageName
                    )
                }
            }
        }
    }
}