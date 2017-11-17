package io.cphandheld.vinpoint.api

import android.support.test.InstrumentationRegistry
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import io.cphandheld.vinpoint.api.models.Credentials

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

    @Rule @JvmField
    val grantInternetRule = GrantPermissionRule.grant(android.Manifest.permission.INTERNET);

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("io.cphandheld.vinpoint.api", appContext.packageName)
    }
    private val _token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJBZG1pbiJdLCJpc3MiOiJodHRwczovL2NwaHQuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDU4Yzk1Y2Q1MDBjNGM1NmIzY2Y5ODE1MiIsImF1ZCI6Ilpld2FSdWVHNTdydHNqbDZuNkZaZ1hFMHlIazR3SW5TIiwiaWF0IjoxNTA5OTk0NzMwLCJleHAiOjE1MTAwMzA3MzB9.an2FCCpn6oaGjaxONk_U5wEXe-Hb5jEUNB2MKVBXXu0"

    @Test
    fun getsInventory() {
        val appContext = InstrumentationRegistry.getTargetContext()
        val cred = Credentials(_token,1)
        Log.i("TESTS", "Starting getsInventory");

//        Inventory(appContext).search(cred,"1B", "N/A").subscribe { result ->
//            Log.i("TESTS","InventoryId: " + result.data!![0].InventoryId.toString())
//            Log.i("TESTS", "VIN: " + result.data!![0].VIN)
//            assertEquals("1234", result.data!![0].VIN)
//        }

        val latch = CountDownLatch(1)


        Inventory(appContext).search(cred,"1B", "N/A").subscribe(
                { inv -> Log.i("TESTS", "Received inventory")
                    latch.countDown();
                },
                { e -> Log.e("TESTS", "Error: " + e.toString())
                    latch.countDown();
                }
        )

        latch.await()
    }
}
