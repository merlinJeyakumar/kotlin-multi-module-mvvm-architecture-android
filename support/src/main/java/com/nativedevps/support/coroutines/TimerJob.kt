package com.nativedevps.support.coroutines

import com.nativedevps.support.utility.date_time_utility.MillisecondUtility.now
import kotlinx.coroutines.*

object TimerJob {

    /**
     * Infinite job occurring every delay within same thread,
     * [milliseconds] are the delay to [block] get called,
     * [block] get called on same thread
     */
    fun emitOnDelay(milliseconds: Long, block: () -> Unit): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                block()
                delay(milliseconds)
            }
        }
    }

    /**
     * Emit until duration [milliseconds] when interval meets [interval] got called
     * until duration meets [block] not called, if duration meets equal or more than
     * duration [block] called
     */
    fun emitPostDuration(
        milliseconds: Long,
        intervalMillis: Long = 0,
        interval: (suspend CoroutineScope.(interval: Long) -> Unit?)? = null,
        block: suspend CoroutineScope.() -> Unit,
    ): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            val initialTime = now
            var nextInterval = intervalMillis
            while (isActive) {
                val currentDuration = now - initialTime
                if (currentDuration >= milliseconds) {
                    block()
                    break
                } else if (intervalMillis != 0L && currentDuration >= nextInterval) {
                    interval?.let { it(currentDuration) }
                    nextInterval += intervalMillis
                }
                delay(if (intervalMillis != 0L) intervalMillis else milliseconds)
            }
        }
    }

    fun runAfter(durationMilliseconds: Long, block: () -> Unit): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            delay(durationMilliseconds)
            block()
        }
    }

    suspend fun runOnUiThread(callback: () -> Unit) {
        withContext(Dispatchers.Main) {
            callback()
        }
    }
}