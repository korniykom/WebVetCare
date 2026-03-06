import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(ui.plugins.kotlinMultiplatform)
    alias(ui.plugins.composeMultiplatform)
    alias(ui.plugins.composeCompiler)
    alias(ui.plugins.composeHotReload)
}

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
        }
        commonTest.dependencies {
            implementation(ui.kotlin.test)
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
