plugins {
    `maven-publish`
    signing
}

val releaseMode = project.findProperty("releaseMode")
val versionSuffix = when (releaseMode) {
    "RELEASE" -> ""
    else -> "-SNAPSHOT"
}
val deploySonatypeUrl = when (releaseMode) {
    "RELEASE" -> "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
    else -> "https://oss.sonatype.org/content/repositories/snapshots/"
}

project.version = project.version.toString() + versionSuffix

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    repositories {
        maven {
            name = "sonatype"
            setUrl("deploySonatypeUrl")
            credentials {
                username = System.getenv("SONATYPE_USERNAME")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
    }
    publications.withType<MavenPublication> {
        artifact(javadocJar)
        pom {
            name.set(project.name)
            url.set("https://github.com/humans-group/humans-arch")
            description.set("Common architecture components")
            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("humans-group")
                    name.set("Humans Group")
                    email.set("info@humans.net")
                }
            }
            scm {
                url.set("https://github.com/humans-group/humans-arch")
            }
        }
    }
}

signing {
    val passPhase = System.getenv("GPG_PASSPHRASE")
    project.ext.set("signing.keyId", "TODO: projectId")
    project.ext.set("signing.password", passPhase)
    project.ext
        .set("signing.secretKeyRingFile", "${project.rootProject.rootDir}/scripts/secring.gpg")
    passPhase?.let { sign(publishing.publications) }
}
