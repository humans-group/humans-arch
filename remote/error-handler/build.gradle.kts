plugins {
    id("multiplatform-library-convention")
    id("publish-convention")
}
dependencies {
    commonMainImplementation(projects.annotations)
    commonMainImplementation(projects.remote.result)
}

group = "net.humans.arch.kmm.remote"
