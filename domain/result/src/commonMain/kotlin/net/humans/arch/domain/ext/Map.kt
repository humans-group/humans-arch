package net.humans.arch.domain.ext

import net.humans.arch.annotations.domain.DomainLevelApi
import net.humans.arch.domain.DomainResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Converts [DomainResult] success value to another template.
 * */
@DomainLevelApi
@OptIn(ExperimentalContracts::class)
inline infix fun <E : Any, T : Any, R : Any> DomainResult<E, T>.mapSuccess(
    transform: (T) -> R
): DomainResult<E, R> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DomainResult.Success -> DomainResult.success(transform(value))
        is DomainResult.Failure -> this
    }
}

/**
 * Converts [DomainResult] success value to another [DomainResult].
 * */
@DomainLevelApi
@OptIn(ExperimentalContracts::class)
inline infix fun <E : Any, T : Any, R : Any> DomainResult<E, T>.flatMapSuccess(
    transform: (T) -> DomainResult<E, R>
): DomainResult<E, R> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DomainResult.Success -> transform(value)
        is DomainResult.Failure -> this
    }
}

/**
 * Converts [DomainResult] failure value to another template.
 * */
@DomainLevelApi
@OptIn(ExperimentalContracts::class)
inline infix fun <E : Any, T : Any, F : Any> DomainResult<E, T>.mapFailure(
    transform: (E) -> F
): DomainResult<F, T> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DomainResult.Success -> this
        is DomainResult.Failure -> DomainResult.failure(transform(error))
    }
}

/**
 * Converts [DomainResult] failure value to another [DomainResult].
 * */
@DomainLevelApi
@OptIn(ExperimentalContracts::class)
inline infix fun <E : Any, T : Any, F : Any> DomainResult<E, T>.flatMapFailure(
    transform: (E) -> DomainResult<F, T>
): DomainResult<F, T> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DomainResult.Success -> this
        is DomainResult.Failure -> transform(error)
    }
}
