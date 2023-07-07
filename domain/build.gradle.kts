plugins {
    id("multiplatform-library-convention")
    id("publish-library-convention")
}
dependencies {
    commonMainImplementation(projects.annotations)
}

group = "net.humans.kmm.arch"
