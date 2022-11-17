package net.humans.arch.data.ext

import net.humans.arch.annotations.data.DataLevelApi
import net.humans.arch.annotations.domain.DomainLevelApi
import net.humans.arch.data.DataResult
import net.humans.arch.domain.DomainResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Converts [DataResult] success value to another.
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
inline infix fun <E : Any, T : Any, R : Any> DataResult<E, T>.mapSuccess(
    transform: (T) -> R
): DataResult<E, R> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DataResult.Success -> DataResult.success(transform(value))
        is DataResult.Failure -> this
    }
}

/**
 * Converts [DataResult] success value to another [DataResult]
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
inline infix fun <E : Any, T : Any, R : Any> DataResult<E, T>.flatMapSuccess(
    transform: (T) -> DataResult<E, R>
): DataResult<E, R> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DataResult.Success -> transform(value)
        is DataResult.Failure -> this
    }
}

/**
 * Converts [DataResult] success value to another.
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
@DomainLevelApi
inline infix fun <E : Any, T : Any, R : Any> DataResult<E, T>.mapSuccessDomain(
    transform: (T) -> R
): DomainResult<E, R> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DataResult.Success -> DomainResult.success(value = transform(value))
        is DataResult.Failure -> DomainResult.failure(error = this.error)
    }
}

/**
 * Converts [DataResult] failure value to another.
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
inline infix fun <E : Any, T : Any, F : Any> DataResult<E, T>.mapFailure(
    transform: (E) -> F
): DataResult<F, T> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DataResult.Success -> this
        is DataResult.Failure -> DataResult.failure(transform(error))
    }
}

/**
 * Converts [DataResult] failure value to another [DataResult].
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
inline infix fun <E : Any, T : Any, F : Any> DataResult<E, T>.flatMapFailure(
    transform: (E) -> DataResult<F, T>
): DataResult<F, T> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DataResult.Success -> this
        is DataResult.Failure -> transform(error)
    }
}

/**
 * Converts [DataResult] failure value to another.
 * */
@OptIn(ExperimentalContracts::class)
@DataLevelApi
@DomainLevelApi
inline infix fun <E : Any, T : Any, F : Any> DataResult<E, T>.mapFailureDomain(
    transform: (E) -> F
): DomainResult<F, T> {
    contract {
        callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
    }
    return when (this) {
        is DataResult.Success -> DomainResult.success(value = this.value)
        is DataResult.Failure -> DomainResult.failure(error = transform(error))
    }
}

@DataLevelApi
@DomainLevelApi
inline fun <E : Any, T : Any> DataResult<E, T>.toDomainResult(): DomainResult<E, T> {
    return when (this) {
        is DataResult.Success -> DomainResult.success(value = this.value)
        is DataResult.Failure -> DomainResult.failure(error = this.error)
    }
}
