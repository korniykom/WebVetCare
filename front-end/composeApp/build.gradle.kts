import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(ui.plugins.kotlinMultiplatform)
    alias(ui.plugins.composeMultiplatform)
    alias(ui.plugins.composeCompiler)
    alias(ui.plugins.kotlinSerialization)
}
val baseUrl = System.getenv("BASE_URL") ?: "http://localhost:8080/api"

val serviceName = "compose-web"

val namespace = project.property("namespace") as String
val registryUrl = project.property("registryUrl") as String
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
            implementation(ui.ktor.client.cio)



        }
        commonTest.dependencies {
            implementation(ui.kotlin.test)
        }
        webMain.dependencies {
        }
        jsMain.dependencies {
            implementation(ui.ktor.client.js)

        }
        wasmJsMain.dependencies {
            implementation(ui.ktor.client.js)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(ui.kotlinx.coroutinesSwing)
            implementation(ui.datastore.preferences)
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
    commandLine(
        "docker", "build",
        "--build-arg", "BASE_URL=${System.getenv("BASE_URL") ?: "http://webvetcare.ua/api"}",
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