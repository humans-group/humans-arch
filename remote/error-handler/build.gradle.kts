plugins {
    id("multiplatform-library-convention")
}
dependencies {
    commonMainImplementation(projects.annotations)
    commonMainImplementation(projects.remote.result)
}

group = "net.humans.arch.kmm.remote"
