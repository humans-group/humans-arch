plugins {
    id("multiplatform-library-convention")
    id("publish-library-convention")
}
dependencies {
    commonMainImplementation(projects.annotations)
    commonMainImplementation(projects.domain)
}
group = "net.humans.kmm.arch"
