import org.springframework.boot.buildpack.platform.build.PullPolicy

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)
    alias(libs.plugins.kotlinPluginJpa)
}

group = "com.korniykom"
version = "0.0.1-SNAPSHOT"
description = "auth-service"

val serviceName = "auth-service"
val namespace = project.property("namespace") as String
val registryUrl = project.property("registryUrl") as String
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

    implementation(libs.springBootStarterWeb)
    implementation(libs.springBootStarterSecurity)
    implementation(libs.springBootStarterValidation)
    implementation(libs.kotlinReflect)
    implementation(libs.springBootStarterDataJpa)
    implementation(libs.flywayCore)
    implementation(libs.springSecurityCrypto)
    implementation(libs.springSecurityOauth2Jose)
    implementation(libs.commonsCodec)
    runtimeOnly(libs.postgresql)
    runtimeOnly(libs.flywayDatabasePostgresql)

    implementation(libs.springCloudConfigClient)

    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.testcontainersPostgresql)
    testImplementation(libs.testcontainersJunitJupiter)
    testImplementation(libs.springBootStarterWebflux)

    implementation(libs.springBootStarterActuator)

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