plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)}

group = "com.korniykom"
version = "unspecified"

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