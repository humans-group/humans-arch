package net.humans.arch.domain

import net.humans.arch.annotations.domain.DomainLevelApi

/**
 * Domain result monad with:
 * [E] - error state on the left side and
 * [T] - success on the right side
 * */
@DomainLevelApi
sealed class DomainResult<out E : Any, out T : Any> {

    /**
     * Returns `true` if this instance represents a successful outcome.
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Success state implementation. Produce generic [T] value.
     * */
    data class Success<out T : Any> @PublishedApi internal constructor(
        val value: T
    ) : DomainResult<Nothing, T>()

    /**
     * Failure state implementation. Produce generic [E] error.
     * */
    data class Failure<out E : Any> @PublishedApi internal constructor(
        val error: E
    ) : DomainResult<E, Nothing>()

    companion object {
        /**
         * Success result factory method.
         * */
        fun <T : Any> success(value: T): DomainResult<Nothing, T> = Success(value = value)

        /**
         * Failure result factory method.
         * */
        fun <E : Any> failure(error: E): DomainResult<E, Nothing> = Failure(error = error)
    }
}

/**
 * Generic conversion [T] to success domain result state.
 * */
@DomainLevelApi
fun <T : Any> T.toSuccessResult() = DomainResult.success(this)

/**
 * Generic conversion [E] to failure domain result state.
 * */
@DomainLevelApi
fun <E : Any> E.toFailureResult() = DomainResult.failure(this)
