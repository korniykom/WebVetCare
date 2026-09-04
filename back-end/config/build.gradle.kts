import org.springframework.boot.buildpack.platform.build.PullPolicy

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)}

group = "com.korniykom"
version = "0.0.1"
description = "config"

val serviceName = "config"
val namespace = "webvetcare"
val registryUrl = "ghcr.io/korniykom"
val imageTag = project.version.toString()
val mImageName = "$namespace-$serviceName"
val fullImageName = "$registryUrl/$mImageName:$imageTag"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.springCloudDependencies))
    implementation(libs.springCloudConfigServer)
    implementation(libs.springBootStarterWeb)
}

kotlin {
    jvmToolchain(17)
}

tasks.bootBuildImage {
    imageName = "$mImageName:$imageTag"
    imagePlatform = "linux/amd64"
    pullPolicy = PullPolicy.IF_NOT_PRESENT
    cleanCache = false
    environment = mapOf("BP_JVM_VERSION" to libs.versions.java.get())
}

tasks.register<Exec>("tagImage") {
    dependsOn(tasks.bootBuildImage)
    commandLine("docker", "tag", "$mImageName:$imageTag", fullImageName)
}

tasks.register<Exec>("pushImage") {
    dependsOn("tagImage")
    commandLine("docker", "push", fullImageName)
}