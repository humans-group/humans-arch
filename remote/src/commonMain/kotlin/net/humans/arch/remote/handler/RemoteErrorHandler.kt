package net.humans.arch.remote.handler

import net.humans.arch.annotations.remote.RemoteLevelApi
import net.humans.arch.remote.RemoteResult
import net.humans.arch.remote.errors.RemoteError

/**
 * [RemoteResult] error handler for handle any specific non-null remote error
 * */
@RemoteLevelApi
interface RemoteErrorHandler<E : Any> {

    /**
     * Handle partial errors of [RemoteResult] and convert to specific error instance
     * */
    fun handleErrors(errors: List<RemoteError>): E

    /**
     * Handle failure state of [RemoteResult] and convert to specific error instance
     * */
    fun handleRemoteResultError(remoteResultError: RemoteResult.ConnectionError): E
}
