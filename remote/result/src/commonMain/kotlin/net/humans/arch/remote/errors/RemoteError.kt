package net.humans.arch.remote.errors

import net.humans.arch.annotations.remote.RemoteLevelApi

/**
 * Error data holder
 */
@RemoteLevelApi
interface RemoteError {

    /**
     * Remote result message
     */
    val message: String

    val traceId: String?

    val path: List<String>

    fun getPathLine(separator: String = SEPARATOR): String =
        if (path.isEmpty()) EMPTY_PATH else path.joinToString(separator = separator)

    companion object {
        private const val EMPTY_PATH = ""
        const val SEPARATOR = ";"
    }
}
