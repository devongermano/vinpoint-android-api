package io.cphandheld.vinpoint.api.testing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.volley.VolleyError
import io.cphandheld.vinpoint.api.Inventory
import io.cphandheld.vinpoint.api.R
import io.cphandheld.vinpoint.api.Security
import io.cphandheld.vinpoint.api.models.CPCredentials
import kotlinx.android.synthetic.main.activity_testing.*

class TestingActivity : AppCompatActivity() {

    val testPass = "PASS"
    val testFail = "FAIL"

    var credentials: CPCredentials? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)
        testLogin()
    }

    private fun testLogin() {
        val security = Security(applicationContext)
       security.login(0, "test@test.com", "Password1")
        .subscribe({ response ->
            textView_auth0login_stat.text = testPass
            textView_auth0login_stat.setTextColor(resources.getColor(R.color.green))

            credentials = CPCredentials(response.access_token!!, 0)

            testGetInventoryItem()

        }, { error ->
            val volleyError = error as VolleyError
            Log.e("Response Code: ", volleyError.networkResponse.statusCode.toString())
            textView_auth0login_stat.text = testFail
            textView_auth0login_stat.setTextColor(resources.getColor(R.color.red))
        })
    }

    private fun testGetInventoryItem() {
        val inventory = Inventory(applicationContext)
        inventory.getInventory(credentials!!)
                .subscribe({ response ->
                    textView_scan_stat.text = testPass
                    textView_scan_stat.setTextColor(resources.getColor(R.color.green))
                }, { error ->
                    textView_scan_stat.text = testFail
                    textView_scan_stat.setTextColor(resources.getColor(R.color.red))
                })
    }

}
