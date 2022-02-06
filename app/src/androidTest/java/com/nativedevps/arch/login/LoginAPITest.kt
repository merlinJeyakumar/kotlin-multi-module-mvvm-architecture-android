package com.nativedevps.arch.login

import android.util.Log
import com.domain.datasources.remote.api.RestDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginAPITest {
    @Inject
    lateinit var restDataSource: RestDataSource

    @Test
    fun testCheckSessionExpiry() = runBlocking {
//        val result = restDataSource.login(LoginSendModel("9042886538")).execute()
//        Log.e("JK", result.message())
    }
}
