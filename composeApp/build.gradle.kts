import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(ui.plugins.kotlinMultiplatform)
    alias(ui.plugins.composeMultiplatform)
    alias(ui.plugins.composeCompiler)
    alias(ui.plugins.kotlinSerialization)
}

val serviceName = "compose-web"
val namespace = "webvetcare"
val registryUrl = "ghcr.io/korniykom"
val imageTag = "0.0.1"

val imageName = "$namespace-$serviceName"
val fullImageName = "$registryUrl/$imageName:$imageTag"

kotlin {

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {

        commonMain.dependencies {
            implementation(ui.compose.runtime)
            implementation(ui.compose.foundation)
            implementation(ui.compose.material3)
            implementation(ui.compose.ui)
            implementation(ui.compose.components.resources)
            implementation(ui.compose.uiToolingPreview)
            implementation(ui.androidx.lifecycle.viewmodelCompose)
            implementation(ui.androidx.lifecycle.runtimeCompose)
            implementation(ui.koin.core)
            implementation(ui.koin.compose.viewmodel)
            implementation(ui.windowSizeClass)
            implementation(ui.jetbrains.navigation3.ui)
            implementation(ui.jetbrains.material3.adaptiveNavigation3)
            implementation(ui.jetbrains.lifecycle.viewmodelNavigation3)
            implementation(ui.kotlinx.serialization)
            implementation(ui.bundles.ktor.common)
            implementation(ui.touchlab.kermit)
            implementation(ui.kotlin.stdlib)



        }
        commonTest.dependencies {
            implementation(ui.kotlin.test)
        }
        jsMain.dependencies {
            implementation(ui.kotlin.stdlib.js)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(ui.kotlinx.coroutinesSwing)

        }
    }
}


compose.desktop {
    application {
        mainClass = "com.korniykom.webvetcare.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.korniykom.webvetcare"
            packageVersion = "1.0.0"
        }
    }
}

tasks.register<Exec>("buildImage") {
    dependsOn("jsBrowserProductionWebpack")

    commandLine(
        "docker", "build",
        "-t", fullImageName,
        rootProject.projectDir.absolutePath
    )
}

tasks.register<Exec>("pushImage") {
    dependsOn("buildImage")

    commandLine(
        "docker", "push",
        fullImageName
    )
}