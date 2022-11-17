package net.humans.arch.annotations.presentation

/**
 * Presentation level API annotation.
 *
 * Must be used only for `public` definitions of:
 * - classes
 * - interfaces
 * - functions
 * - type aliases
 * */
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPEALIAS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
@RequiresOptIn(
    level = RequiresOptIn.Level.ERROR,
    message = "This is an experimental API. \n" +
        "No compatibility guarantees are provided.\n" +
        "If you want to use this API just add @OptIn(PresentationLevelApi::class)."
)
annotation class PresentationLevelApi
