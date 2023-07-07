@file:Suppress("UnstableApiUsage")

import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("com.vanniktech.maven.publish")
}

val versionSuffix = when (System.getenv("SNAPSHOT")) {
    null -> "-TEST"
    "true" -> "-SNAPSHOT"
    else -> ""
}
project.version = project.version.toString() + versionSuffix

mavenPublishing {
    publishToMavenCentral(SonatypeHost.S01)

    coordinates(
        groupId = project.group.toString(),
        artifactId = project.name,
        version = project.version.toString()
    )

    pom {
        name.set("humans-arch")
        description.set("Basic architectural components for mobile apps (annotations, monads, etc.) by Humans.")
        inceptionYear.set("2023")
        url.set("https://github.com/humans-group/humans-arch")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://github.com/humans-group/humans-arch/blob/main/LICENSE.md")
                distribution.set("https://github.com/humans-group/humans-arch/blob/main/LICENSE.md")
            }
        }
        developers {
            developer {
                id.set("humans-group")
                name.set("Humans Group")
                url.set("mobile_dev_service@humans.net")
            }
        }
        scm {
            url.set("https://github.com/humans-group/humans-arch")
            connection.set("scm:git:github.com/humans-group/humans-arch.git")
            developerConnection.set("scm:git:ssh://github.com/humans-group/humans-arch.git")
        }
    }

    signAllPublications()
}
