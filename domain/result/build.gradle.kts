plugins {
    id("multiplatform-library-convention")
}
dependencies {
    commonMainImplementation(projects.annotations)
}

group = "net.humans.arch.kmm.domain"
