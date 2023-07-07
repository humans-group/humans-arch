package net.humans.arch.remote

import net.humans.arch.annotations.remote.RemoteLevelApi
import net.humans.arch.remote.errors.RemoteError

/**
 * Abstraction remote response.
 *
 * <b>Contract:</b> Must not be beyond remote layer
 */
@RemoteLevelApi
sealed class RemoteResult<out T> {

    /**
     * Success representation of remote operation. Wrapper on value. Can be partially correct, holing some data and list
     * of errors. Most likely to accrue in merged requests
     *
     * @param value requested data
     * @param errors list of errors
     */
    data class Response<T>(
        val value: T?,
        val errors: List<RemoteError>
    ) : RemoteResult<T>() {

        val hasErrors: Boolean get() = errors.isNotEmpty()
    }

    /**
     * Representation of set of errors that happened on application level. Ex, newtwork was not connected,
     * request closed by timeout, etc.
     *
     * @param cause internal errors cause
     */
    data class ConnectionError(val cause: Throwable) : RemoteResult<Nothing>()
}
