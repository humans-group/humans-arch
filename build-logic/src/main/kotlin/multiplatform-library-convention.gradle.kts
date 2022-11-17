plugins {
    id("org.jetbrains.kotlin.multiplatform")
}

kotlin {
    ios()
    jvm()

    sourceSets {
        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
    }
}
