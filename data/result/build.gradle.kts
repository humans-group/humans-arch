plugins {
    id("multiplatform-library-convention")
}
dependencies {
    commonMainImplementation(projects.annotations)
    commonMainImplementation(projects.domain.result)
}
group = "net.humans.kmm.arch.data"
