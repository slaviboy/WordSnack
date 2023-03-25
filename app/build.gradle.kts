kapt {
    correctErrorTypes = true
}

plugins {
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"
}

dependencies {
    implementation(*ApplicationDependencies.dependenciesApp)
    ksp(*ApplicationDependencies.ksp)
}