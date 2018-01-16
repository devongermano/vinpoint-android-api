package io.cphandheld.vinpoint.api.testing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import io.cphandheld.vinpoint.api.*
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPEnvironment
import io.cphandheld.vinpoint.api.models.CPInventory
import kotlinx.android.synthetic.main.activity_testing.*


class TestingActivity : AppCompatActivity() {

    val username = "test@test.com"
    val password = "Password1"
    val orgId = 1
    // Immutable
    val testPass = "PASS"
    val testFail = "FAIL"

    // Variable
    var inventoryInstance: Inventory? = null
    var dealershipInstance: Dealership? = null
    var printerInstance: Printer? = null
    var organizationInstance: Organization? = null
    var journalInstance: Journal? = null

    var credentials: CPCredentials? = null
    var inventory: Array<CPInventory>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)
        testLogin()
    }


    private fun testLogin() {

        val environment = CPEnvironment()
        environment.APIEndpoint = "https://orion.cpht.io/unison-api"
        environment.PrinterAPIEndpoint = "https://orion.cpht.io/printer-api"
        environment.ScannerAPIEndpoint = "https://orion.cpht.io/scanner-api"
        environment.Auth0ClientID = "ZewaRueG57rtsjl6n6FZgXE0yHk4wInS"
        environment.Auth0Endpoint = "https://cpht.auth0.com/oauth/ro"

        VinpointAPI.Environment = environment

        inventoryInstance = Inventory(applicationContext)
        dealershipInstance = Dealership(applicationContext)
        printerInstance = Printer(applicationContext)
        organizationInstance = Organization(applicationContext)
        journalInstance = Journal(applicationContext)

        val security = Security(applicationContext)
       security.login(username, password)
        .subscribe({ response ->

            updateTestUiResult(textView_auth0login_stat, true)

            credentials = CPCredentials(response.id_token!!, orgId)
            testGetNetworkInventory()

        }, { error ->
            updateTestUiResult(textView_auth0login_stat, false)
        })
    }

    private fun testGetNetworkInventory() {
        inventoryInstance!!.getInventory(credentials!!)
                .subscribe({ response ->

                    updateTestUiResult(textView_get_network_inventory_stat, true)

                    this.inventory = response

                    testGetNetworkInventoryItem()

                }, { error ->
                    updateTestUiResult(textView_get_network_inventory_stat, false)
                    testGetNetworkInventoryItem()
                })
    }

    private fun testGetNetworkInventoryItem() {

        val inventoryItem = this.inventory!![0].InventoryId

        inventoryInstance!!.getInventoryItem(credentials!!, inventoryItem!!)
                .subscribe({ response ->
                    updateTestUiResult(textView_get_network_inventory_item_stat, true)
                    testPostNetworkInventoryItem()
                }, { error ->
                    updateTestUiResult(textView_get_network_inventory_item_stat, false)
                    testPostNetworkInventoryItem()
                })
    }

    private fun testPostNetworkInventoryItem() {

        updateTestUiResult(textView_post_network_inventory_item_stat, false)
        testPutNetworkInventoryItem()

    }

    private fun testPutNetworkInventoryItem() {

        val inventoryItem = this.inventory!![0]

        inventoryItem.Color = RandomInventoryGenerator(applicationContext).generateRandomColor()

        inventoryInstance!!.postInventoryItem(credentials!!, inventoryItem)
                .subscribe({ response ->
                    updateTestUiResult(textView_put_network_inventory_item_stat, true)
                    testGetNetworkDealership()
                }, { error ->
                    updateTestUiResult(textView_put_network_inventory_item_stat, false)
                    testGetNetworkDealership()
                })
    }

    private fun testGetNetworkDealership() {

        val dealershipId = this.inventory!![0].DealershipId

        dealershipInstance!!.getDealership(credentials!!, dealershipId!!)
                .subscribe({ response ->
                    updateTestUiResult(textView_get_network_dealership_stat, true)
                    testGetNetworkPrinters()
                }, { error ->
                    updateTestUiResult(textView_get_network_dealership_stat, false)
                    testGetNetworkPrinters()
                })
    }

    private fun testGetNetworkPrinters() {
        val dealershipId = this.inventory!![0].DealershipId

        printerInstance!!.getPrinters(credentials!!, dealershipId!!)
                .subscribe({ response ->
                    updateTestUiResult(textView_get_printers_stat, true)
                    testGetOrganizations()
                }, { error ->
                    updateTestUiResult(textView_get_printers_stat, false)
                    testGetOrganizations()
                })
    }

    private fun testGetOrganizations() {

        organizationInstance!!.getOrganizationsByUserId(credentials!!)
                .subscribe({ response ->
                    updateTestUiResult(textView_get_organizations_stat, true)
                    testGetJournal()
                }, { error ->
                    updateTestUiResult(textView_get_organizations_stat, false)
                    testGetJournal()
                })
    }

    private fun testGetJournal() {

        journalInstance!!.getVinpointJournal(credentials!!, this.inventory!![0].InventoryId!!)
                .subscribe({ response ->
                    updateTestUiResult(textView_get_journal_stat, true)
                }, { error ->
                    updateTestUiResult(textView_get_journal_stat, false)
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
