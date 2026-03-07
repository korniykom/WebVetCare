plugins {
    alias(ui.plugins.androidApplication)
    alias(ui.plugins.composeMultiplatform)
    alias(ui.plugins.composeCompiler)
}

dependencies {
    implementation(ui.compose.uiToolingPreview)
    implementation(ui.androidx.activity.compose)
    implementation(project(":composeApp"))
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
