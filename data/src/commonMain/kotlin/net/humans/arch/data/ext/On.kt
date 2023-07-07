package net.humans.arch.data.ext

import net.humans.arch.annotations.data.DataLevelApi
import net.humans.arch.data.DataResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Handle of [DataResult] and process success variant. Skip failure variation.
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
inline infix fun <T : Any> DataResult<*, T>.onSuccess(
    successBlock: (T) -> Unit
) {
    contract {
        callsInPlace(successBlock, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DataResult.Success<T> -> successBlock(value)
        else -> Unit
    }
}

/**
 * Handle success state and continue process [DataResult]. Skip failure variation.
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
inline infix fun <E : Any, T : Any> DataResult<E, T>.doOnSuccess(
    successBlock: (T) -> Unit
): DataResult<E, T> {
    contract {
        callsInPlace(successBlock, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DataResult.Success) successBlock(value)
    return this
}

/**
 * Handle of [DataResult] and process failure variant. Skip success variation.
 *
 * @param failureBlock lambda to handle and process failure state
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
inline infix fun <E : Any> DataResult<E, *>.onFailure(
    failureBlock: (E) -> Unit
) {
    contract {
        callsInPlace(failureBlock, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DataResult.Failure -> failureBlock(error)
        else -> Unit
    }
}

/**
 * Handle failure state and continue process [DataResult]. Skip success variation.
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
inline infix fun <E : Any, T : Any> DataResult<E, T>.doOnFailure(
    failureBlock: (E) -> Unit
): DataResult<E, T> {
    contract {
        callsInPlace(failureBlock, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DataResult.Failure) failureBlock(error)
    return this
}
