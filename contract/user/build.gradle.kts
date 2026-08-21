@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(ui.plugins.kotlinMultiplatform)
    alias(ui.plugins.kotlinSerialization)
}

group = "com.korniykom"
version = "0.0.1-SNAPSHOT"
description = "contract-user"

kotlin {
    jvm()

    js {
        browser()
        binaries.executable()
    }

    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(ui.kotlinx.serialization)
        }


    }

    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xannotation-default-target=param-property"
        )
    }
}

