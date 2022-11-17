package net.humans.arch.di

abstract class ComponentHolder<Module : Any> {

    abstract val components: List<Component<Module>>

    fun modules(): List<Module> = components.flatMap { component -> component.modules() }
}
