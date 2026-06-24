
plugins {
    alias(libs.plugins.dockerConventionPlugin)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)
    alias(libs.plugins.kotlinPluginJpa)
}

group = "com.korniykom"
version = "0.0.2-SNAPSHOT"
description = "user-service"


docker {
    imageTag.set(version.toString())
    namespace.set("webvetcare")
    registryUrl.set("ghcr.io/korniykom")
    jvmVersion.set("17")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}
dependencies {
    implementation(libs.springBootStarterWeb)
    implementation(libs.springBootStarterSecurity)
    implementation(libs.springBootStarterValidation)
    implementation(libs.springBootStarterDataJpa)
    implementation(libs.springSecurityOauth2ResourceServer)
    implementation(libs.springSecurityOauth2Jose)
    implementation(libs.kotlinReflect)
    implementation(libs.flywayCore)

    runtimeOnly(libs.postgresql)
    runtimeOnly(libs.flywayDatabasePostgresql)

    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.testcontainersPostgresql)
    testImplementation(libs.testcontainersJunitJupiter)
    testImplementation(libs.springBootStarterWebflux)
}
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
