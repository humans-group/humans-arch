@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "humans-arch"

includeBuild("build-logic")

include(
    ":annotations",
    ":di",
    ":data",
    ":domain",
    ":remote",
)
