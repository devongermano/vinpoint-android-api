package io.cphandheld.vinpoint.api

import android.support.test.InstrumentationRegistry
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import io.cphandheld.vinpoint.api.models.Credentials
import io.cphandheld.vinpoint.api.models.InventoryModel
import io.reactivex.rxkotlin.subscribeBy

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.concurrent.CountDownLatch

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val grantInternetRule = GrantPermissionRule.grant(android.Manifest.permission.INTERNET);

    // Admin user, expires end of 2018
    private val _token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJBZG1pbiJdLCJpc3MiOiJodHRwczovL2NwaHQuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDU4Yzk1Y2Q1MDBjNGM1NmIzY2Y5ODE1MiIsImF1ZCI6Ilpld2FSdWVHNTdydHNqbDZuNkZaZ1hFMHlIazR3SW5TIiwiaWF0IjoxNTA5OTk0NzMwLCJleHAiOjE1NDYzMDA3OTl9.uQUyp0zeKSvdB8Q2e_iOr778D5NwwnHP8qE0C3i8l4Q"
    private val cred = Credentials(_token, 1)

    private val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("io.cphandheld.vinpoint.api", appContext.packageName)
    }

    @Test
    fun getsInventory() {
        val latch = CountDownLatch(1)
        var result: InventoryModel? = null
        Inventory(appContext).get(cred, 1234).subscribeBy(
                onSuccess = { result = it; latch.countDown() },
                onError = { throw it }
        )
        latch.await()
        assertEquals("1FTBF2A69GEC79810", result!!.VIN);
    }

    @Test
    fun searchInventory() {
        val latch = CountDownLatch(1)
        var result: Array<InventoryModel>? = null
        Inventory(appContext).search(cred, "1B", "N/A").subscribeBy(
                onSuccess = { result = it; latch.countDown() },
                onError = { throw it }
        )
        latch.await()
        assertEquals(18, result!!.size)
    }
}
