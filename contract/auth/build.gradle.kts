@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinJvm)
//    alias(ui.plugins.kotlinMultiplatform)
    alias(ui.plugins.kotlinSerialization)
}

group = "com.korniykom"
version = "0.0.1-SNAPSHOT"
description = "contract-user"

dependencies {
               implementation(ui.kotlinx.serialization)
}
//
//kotlin {
//    jvm()
//
//    js {
//        browser()
//        binaries.executable()
//    }
//
//    wasmJs {
//        browser()
//        binaries.executable()
//    }
//
//    sourceSets {
//        commonMain.dependencies {
//            implementation(ui.kotlinx.serialization)
//        }
//
//
//    }
//
//    compilerOptions {
//        freeCompilerArgs.addAll(
//            "-Xjsr305=strict",
//            "-Xannotation-default-target=param-property"
//        )
//    }
//}

