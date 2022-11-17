package net.humans.arch.remote.errors

import net.humans.arch.annotations.remote.RemoteLevelApi

/**
 * Fallback error
 */
@RemoteLevelApi
data class Undefined(
    override val message: String,
    override val traceId: String?,
    override val path: List<String> = emptyList()
) : RemoteError
