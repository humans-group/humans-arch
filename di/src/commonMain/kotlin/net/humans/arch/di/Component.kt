package net.humans.arch.di

/**
 * Arch component DI module abstraction.
 * */
interface Component<Module : Any> {

    fun modules(): List<Module>
}
