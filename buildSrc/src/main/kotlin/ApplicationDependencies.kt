object ApplicationDependencies {

    private const val core = "androidx.core:core-ktx:${ApplicationDependencyVersions.core}"
    private const val activity = "androidx.activity:activity-compose:${ApplicationDependencyVersions.activity}"
    private const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${ApplicationDependencyVersions.lifecycle}"
    private const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${ApplicationDependencyVersions.lifecycle}"
    private const val kotlinStandardLib = "org.jetbrains.kotlin:kotlin-stdlib:${ApplicationDependencyVersions.kotlin}"
    private const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${ApplicationDependencyVersions.kotlinCoroutinesCore}"
    private const val composeUI = "androidx.compose.ui:ui:${ApplicationDependencyVersions.composeUI}"
    private const val composeMaterial3 = "androidx.compose.material3:material3:${ApplicationDependencyVersions.composeMaterial3}"
    private const val composeMaterial3WindowSize = "androidx.compose.material3:material3-window-size-class:${ApplicationDependencyVersions.composeMaterial3}"
    private const val composeUIToolingPreview = "androidx.compose.ui:ui-tooling-preview:${ApplicationDependencyVersions.composeUI}"
    private const val percentageUnits = "com.github.slaviboy:JetpackComposePercentageUnits:${ApplicationDependencyVersions.percentageUnits}"
    private const val daggerHilt = "com.google.dagger:hilt-android:${ApplicationDependencyVersions.daggerHilt}"
    private const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${ApplicationDependencyVersions.daggerHilt}"
    private const val gson = "com.google.code.gson:gson:${ApplicationDependencyVersions.gson}"
    private const val raamcostaCore = "io.github.raamcosta.compose-destinations:core:${ApplicationDependencyVersions.raamcosta}"
    private const val raamcostaKsp = "io.github.raamcosta.compose-destinations:ksp:${ApplicationDependencyVersions.raamcosta}"

    // Testing
    const val junit = "junit:junit:${ApplicationDependencyVersions.junit}"

    val dependenciesApp = arrayOf(
        kotlinStandardLib,
        kotlinxCoroutinesCore,
        composeUI,
        composeMaterial3,
        composeMaterial3WindowSize,
        composeUIToolingPreview,
        core,
        activity,
        lifecycle,
        lifecycleViewModel,
        percentageUnits,
        daggerHilt,
        gson,
        raamcostaCore
    )

    val dependenciesLibrary = arrayOf(
        kotlinStandardLib,
        kotlinxCoroutinesCore,
        composeUI,
        composeMaterial3,
        composeMaterial3WindowSize,
        composeUIToolingPreview,
        daggerHilt
    )

    val kapt = arrayOf(
        daggerHiltCompiler
    )

    val ksp = arrayOf(
        raamcostaKsp
    )
}