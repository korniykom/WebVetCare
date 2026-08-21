package com.korniykom.buildlogic

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class DockerExtension @Inject constructor(
    objects: ObjectFactory
) {
    val imageTag: Property<String> =
        objects.property(String::class.java)
    val registryUrl: Property<String> =
        objects.property(String::class.java)
    val namespace: Property<String> =
        objects.property(String::class.java)
    val jvmVersion: Property<String> =
        objects.property(String::class.java)

    // Name of the task that must run before `docker build .` for non-Spring-Boot
    // modules (e.g. "wasmJsBrowserDistribution"). Leave unset for Spring Boot
    // modules — they use bootBuildImage instead and don't need this.
    val dockerfileBuildTaskDependency: Property<String> =
        objects.property(String::class.java)
}