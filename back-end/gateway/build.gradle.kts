import org.springframework.boot.buildpack.platform.build.PullPolicy

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)
}

group = "com.korniykom"
version = "0.0.1-SNAPSHOT"
description = "gateway"

val serviceName = "gateway"
val namespace = "webvetcare"
val registryUrl = "ghcr.io/korniykom"
val imageTag = project.version.toString()
val mImageName = "$namespace-$serviceName"
val fullImageName = "$registryUrl/$mImageName:$imageTag"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}
dependencies {
    implementation(platform(libs.springCloudDependencies))
    implementation(libs.springCloudGateway)
    implementation(libs.springBootStarterWebflux)
    implementation(libs.springBootStarterOauth2ResourceServer)
    implementation(libs.springSecurityOauth2Jose)
    implementation(libs.kotlinReflect)
    implementation(libs.jacksonModuleKotlin)
    implementation(libs.springCloudLoadBalancer)

    implementation(libs.springCloudConfigClient)
    implementation(libs.springBootStarterActuator)


    testImplementation(libs.kotlinTestJunit5)
    testRuntimeOnly(libs.junitPlatformLauncher)
}

dependencyManagement {
    imports {
        mavenBom(libs.springCloudDependencies.get().toString())
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
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