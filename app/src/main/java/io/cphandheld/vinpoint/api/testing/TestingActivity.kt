package io.cphandheld.vinpoint.api.testing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.android.volley.VolleyError
import io.cphandheld.vinpoint.api.Inventory
import io.cphandheld.vinpoint.api.R
import io.cphandheld.vinpoint.api.Security
import io.cphandheld.vinpoint.api.models.CPCredentials
import kotlinx.android.synthetic.main.activity_testing.*

class TestingActivity : AppCompatActivity() {

    // Environment Specific
    val username = "test@test.com"
    val password = "Password1"
    val orgId = 1

    // Local
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
       security.login(username, password)
        .subscribe({ response ->

            updateTestUiResult(textView_auth0login_stat, true)

            val credentials = CPCredentials(response.id_token!!, orgId)
            testGetNetworkInventory(credentials)

        }, { error ->
            val volleyError = error as VolleyError
            Log.e("Response Error", volleyError.networkResponse.statusCode.toString())
            updateTestUiResult(textView_auth0login_stat, false)
        })
    }

    private fun testGetNetworkInventory(credentials: CPCredentials) {
        val inventory = Inventory(applicationContext)
        inventory.getInventory(credentials)
                .subscribe({ response ->
                    Log.d("Response", response.toString())
                    updateTestUiResult(textView_get_inventory_stat, true)
                }, { error ->
                    val volleyError = error as VolleyError
                    Log.e("Response Error", volleyError.networkResponse.statusCode.toString())
                    updateTestUiResult(textView_get_inventory_stat, false)
                })
    }

    private fun updateTestUiResult(textView: TextView, testPassed: Boolean) {
        if(!testPassed) {
            textView.text = testFail
            textView.setTextColor(resources.getColor(R.color.red))
        } else {
            textView.text = testPass
            textView.setTextColor(resources.getColor(R.color.green))
        }
    }
}
