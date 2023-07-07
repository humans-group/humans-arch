package net.humans.arch.data

import net.humans.arch.annotations.data.DataLevelApi

/**
 * Data result monad with:
 * [E] - error state on the left side and
 * [T] - success on the right side
 * */
@DataLevelApi
sealed class DataResult<out E : Any, out T : Any> {
    /**
     * Returns `true` if this instance represents a successful outcome.
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Success state implementation. Produce generic [T] value.
     * */
    data class Success<out T : Any> @PublishedApi internal constructor(
        val value: T
    ) : DataResult<Nothing, T>()

    /**
     * Failure state implementation. Produce generic [E] error.
     * */
    data class Failure<out E : Any> @PublishedApi internal constructor(
        val error: E
    ) : DataResult<E, Nothing>()

    companion object {
        /**
         * Success result factory method.
         * */
        fun <T : Any> success(value: T): DataResult<Nothing, T> = Success(value = value)

        /**
         * Failure result factory method.
         * */
        fun <E : Any> failure(error: E): DataResult<E, Nothing> = Failure(error = error)
    }
}

/**
 * Generic conversion [T] to success data result state.
 * */
@DataLevelApi
fun <T : Any> T.toSuccessDataResult() = DataResult.success(this)

/**
 * Generic conversion [E] to failure domain result state.
 * */
@DataLevelApi
fun <E : Any> E.toFailureDataResult() = DataResult.failure(this)
