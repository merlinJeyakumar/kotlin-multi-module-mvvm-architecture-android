package com.nativedevps.arch.login

import com.domain.datasources.remote.api.RestDataSource
import com.domain.model.example_list.ExampleApiModel
import com.nativedevps.support.utility.debugging.JLogE
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
class HiltLoginAPITest {
    @Inject
    lateinit var restDataSource: RestDataSource

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun before() {
        hiltRule.inject()
    }

    @Test
    fun testCheckSessionExpiry() = runBlocking {
        val result: ExampleApiModel? = restDataSource.getExampleList()
        JLogE("LoginAPITest", "result: $result")
    }
}
