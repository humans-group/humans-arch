plugins {
    id("multiplatform-library-convention")
    id("publish-convention")
}
dependencies {
    commonMainImplementation(projects.annotations)
}
group = "net.humans.arch.kmm.remote"
