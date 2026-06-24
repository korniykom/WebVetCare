
plugins {
    alias(libs.plugins.dockerConventionPlugin)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)
}

group = "com.korniykom"
version = "0.0.2-SNAPSHOT"
description = "gateway"

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
    implementation(platform(libs.springCloudDependencies))
    implementation(libs.springCloudGateway)
    implementation(libs.springBootStarterWebflux)
    implementation(libs.springBootStarterOauth2ResourceServer)
    implementation(libs.springSecurityOauth2Jose)
    implementation(libs.kotlinReflect)
    implementation(libs.jacksonModuleKotlin)
    implementation(libs.springCloudLoadBalancer)

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
