plugins {
    alias(ui.plugins.kotlinMultiplatform)
    alias(ui.plugins.kotlinSerialization)
}

kotlin {
    jvm()

    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(ui.kotlinx.serialization)
        }
        jvmMain.dependencies {
            implementation(ui.kotlinx.serialization.json.jvm)
        }
    }
}

