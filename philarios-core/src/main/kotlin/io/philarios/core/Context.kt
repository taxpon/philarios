package io.philarios.core

import io.philarios.util.registry.Registry
import io.philarios.util.registry.emptyRegistry

interface Context<out C> {
    val value: C
}

fun emptyContext(): Context<Any?> = ValueContext(null)

fun <C> contextOf(value: C): Context<C> = ValueContext(value)

internal class ValueContext<out C>(override val value: C) : Context<C>

suspend fun <C, T : Any> Context<C>.map(
        scaffolder: Scaffolder<C, T>,
        registry: Registry = emptyRegistry()
): Context<T> {
    return contextOf(value.let {
        scaffolder
                .createScaffold(it)
                .resolve(registry)
    })
}

inline fun <C, T> Context<C>.map(transform: (C) -> T): Context<T> {
    return contextOf(value.let(transform))
}

fun <T> Context<T>.unwrap(): T {
    return value
}
