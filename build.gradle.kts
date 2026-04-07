plugins {
    alias(ui.plugins.composeMultiplatform) apply false
    alias(ui.plugins.composeCompiler) apply false
    alias(ui.plugins.kotlinMultiplatform) apply false
    alias(ui.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinPluginSpring) apply false
    alias(libs.plugins.springDependencyManagement) apply false
}