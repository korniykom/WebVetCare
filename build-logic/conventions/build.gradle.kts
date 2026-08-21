import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins{
    `kotlin-dsl`
}

dependencies {
    implementation(libs.springBootGradlePlugin)
    implementation(libs.springBootBuildPackPlatform)
}

gradlePlugin {
    plugins {
        register("dockerConventionPlugin") {
            id = libs.plugins.dockerConventionPlugin.get().pluginId
            implementationClass = "com.korniykom.buildlogic.DockerConventionPlugin"
        }
    }
}