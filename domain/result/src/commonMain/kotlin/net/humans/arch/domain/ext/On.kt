package net.humans.arch.domain.ext

import net.humans.arch.annotations.domain.DomainLevelApi
import net.humans.arch.domain.DomainResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Handle of [DomainResult] and process failure variant. Skip success variation.
 *
 * @param failureBlock lambda to handle and process failure state
 * */
@DomainLevelApi
@OptIn(ExperimentalContracts::class)
inline infix fun <E : Any> DomainResult<E, *>.onFailure(
    failureBlock: (E) -> Unit
) {
    contract {
        callsInPlace(failureBlock, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DomainResult.Failure -> failureBlock(error)
        else -> Unit
    }
}

/**
 * Handle failure state and continue process [DomainResult]. Skip success variation.
 * */
@DomainLevelApi
@OptIn(ExperimentalContracts::class)
inline infix fun <E : Any, T : Any> DomainResult<E, T>.doOnFailure(
    failureBlock: (E) -> Unit
): DomainResult<E, T> {
    contract {
        callsInPlace(failureBlock, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DomainResult.Failure) failureBlock(error)
    return this
}

/**
 * Handle of [DomainResult] and process success variant. Skip failure variation.
 * */
@DomainLevelApi
@OptIn(ExperimentalContracts::class)
inline infix fun <T : Any> DomainResult<*, T>.onSuccess(
    successBlock: (T) -> Unit
) {
    contract {
        callsInPlace(successBlock, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DomainResult.Success<T> -> successBlock(value)
        else -> Unit
    }
}

/**
 * Handle success state and continue process [DomainResult]. Skip failure variation.
 * */
@DomainLevelApi
@OptIn(ExperimentalContracts::class)
inline infix fun <E : Any, T : Any> DomainResult<E, T>.doOnSuccess(
    successBlock: (T) -> Unit
): DomainResult<E, T> {
    contract {
        callsInPlace(successBlock, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DomainResult.Success) successBlock(value)
    return this
}
