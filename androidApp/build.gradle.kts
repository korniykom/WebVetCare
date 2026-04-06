plugins {
    alias(ui.plugins.androidApplication)
    alias(ui.plugins.composeMultiplatform)
    alias(ui.plugins.composeCompiler)
}

dependencies {
    implementation(ui.compose.runtime)
    implementation(ui.compose.foundation)
    implementation(ui.compose.material3)
    implementation(ui.compose.ui)
    implementation(ui.compose.components.resources)
    implementation(ui.compose.uiToolingPreview)
    implementation(ui.androidx.activity.compose)
    debugImplementation(ui.androidx.compose.uiTooling)
    debugImplementation(ui.compose.components.resources)
    implementation(ui.compose.ui.preview)
    implementation(project(":composeApp"))
    implementation(ui.koin.android)
    implementation(ui.koin.androidx.compose)
    implementation(ui.koin.compose.viewmodel)
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

android {
    namespace = "com.korniykom.webvetcare"
    compileSdk = ui.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = ui.versions.android.minSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
