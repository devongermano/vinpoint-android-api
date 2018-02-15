package io.cphandheld.vinpoint.api.testing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import io.cphandheld.vinpoint.api.*
import io.cphandheld.vinpoint.api.models.*
import kotlinx.android.synthetic.main.activity_testing.*


class TestingActivity : AppCompatActivity() {

    val mTag = "TestingActivity"

    val username = "test@test.com"
    val password = "Password1"
    val orgId = 1
    // Immutable
    val testPass = "PASS"
    val testFail = "FAIL"

    // Variable
    var securityInstance: Security? = null
    var inventoryInstance: Inventory? = null
    var dealershipInstance: Dealership? = null
    var printerInstance: Printer? = null
    var organizationInstance: Organization? = null
    var journalInstance: Journal? = null
    var filterInstance: Filter? = null

    var vinpointCredentials: CPCredentials? = null


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
        environment.VinpointAuth0ClientID = "ZewaRueG57rtsjl6n6FZgXE0yHk4wInS"
        environment.Auth0Endpoint = "https://cpht.auth0.com/oauth/ro"
        environment.ScannerAPIEndpoint = "496priyO44FWmZu5YQ27s6AJDJmQU702"

        VinpointAPI.Environment = environment

        inventoryInstance = Inventory(applicationContext)
        dealershipInstance = Dealership(applicationContext)
        printerInstance = Printer(applicationContext)
        organizationInstance = Organization(applicationContext)
        journalInstance = Journal(applicationContext)
        securityInstance = Security(applicationContext)
        filterInstance = Filter(applicationContext)

       securityInstance!!.login(username, password)
        .subscribe({ response ->

            updateTestUiResult(textView_auth0login_stat, true)

            vinpointCredentials = CPCredentials(response.id_token!!, orgId)
//            testGetNetworkInventory()
            testDelegateScannerAPI()

        }, { error ->
            updateTestUiResult(textView_auth0login_stat, false)
        })
    }

    private fun testDelegateScannerAPI() {
        securityInstance!!.delegateScannerAPI(vinpointCredentials!!).subscribe({result ->

            updateTestUiResult(textView_scannerDelegate_stat, true)
            testGetNetworkInventory()

        }, {error ->
            updateTestUiResult(textView_scannerDelegate_stat, false)
            testGetNetworkInventory()
        })
    }

    private fun testGetNetworkInventory() {
        inventoryInstance!!.getInventory(vinpointCredentials!!)
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

        inventoryInstance!!.getInventoryItem(vinpointCredentials!!, inventoryItem!!)
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

//        inventoryItem.Color = RandomInventoryGenerator(applicationContext).generateRandomColor()

        inventoryInstance!!.postInventoryItem(vinpointCredentials!!, inventoryItem)
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

        dealershipInstance!!.getDealership(vinpointCredentials!!, dealershipId!!)
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

        printerInstance!!.getPrinters(vinpointCredentials!!, dealershipId!!)
                .subscribe({ response ->
                    updateTestUiResult(textView_get_printers_stat, true)
                    testGetOrganizations()
                }, { error ->
                    updateTestUiResult(textView_get_printers_stat, false)
                    testGetOrganizations()
                })
    }

    private fun testGetOrganizations() {

        organizationInstance!!.getOrganizationsByUserId(vinpointCredentials!!)
                .subscribe({ response ->
                    updateTestUiResult(textView_get_organizations_stat, true)
                    testGetJournal()
                }, { error ->
                    updateTestUiResult(textView_get_organizations_stat, false)
                    testGetJournal()
                })
    }

    private fun testGetJournal() {

        journalInstance!!.getVinpointJournal(vinpointCredentials!!, this.inventory!![0].InventoryId!!)
                .subscribe({ response ->
                    updateTestUiResult(textView_get_journal_stat, true)
                    testGetFilters()
                }, { error ->
                    updateTestUiResult(textView_get_journal_stat, false)
                    testGetFilters()
                })
    }

    private fun testGetFilters() {
        filterInstance!!.getFilters(vinpointCredentials!!).subscribe({ response ->
            updateTestUiResult(textView_get_filter_stat, true)
            testPostFilterDealerships(response)
        }, { error ->
            updateTestUiResult(textView_get_filter_stat, false)
        })
    }

    private fun testPostFilterDealerships(filterResponse: CPFilterResponse) {

        val selectableFilterResponse = CPSelectableFilterResponse(filterResponse)
        selectableFilterResponse.Makes.selectElement(3)
        val filterRequest: CPFilterRequest = CPFilterRequest(selectableFilterResponse)

        val pair: Pair<String, CPFilterRequest> = Pair("data", filterRequest)
        val hashMap: HashMap<String, CPFilterRequest> = hashMapOf(pair)

        filterInstance!!.postFilters(vinpointCredentials!!, hashMap).subscribe({ response ->
            Log.e("Result", response.toString())
            updateTestUiResult(textView_post_filter_dealerships_stat, true)
            testPostFilterYears(filterResponse)
        }, { error ->
            updateTestUiResult(textView_post_filter_dealerships_stat, false)
            testPostFilterYears(filterResponse)
        })
    }

    private fun testPostFilterYears(filterResponse: CPFilterResponse) {
        val selectableFilterResponse = CPSelectableFilterResponse(filterResponse)
        selectableFilterResponse.Years.selectElement(3)
        val filterRequest = CPFilterRequest(selectableFilterResponse)

        val pair: Pair<String, CPFilterRequest> = Pair("data", filterRequest)
        val hashMap: HashMap<String, CPFilterRequest> = hashMapOf(pair)

        filterInstance!!.postFilters(vinpointCredentials!!, hashMap).subscribe({ response ->
            Log.e("Result", response.toString())
            updateTestUiResult(textView_post_filter_year_stat, true)
            testPostFilterLocations(filterResponse)
        }, { error ->
            updateTestUiResult(textView_post_filter_year_stat, false)
            testPostFilterLocations(filterResponse)
        })
    }

    private fun testPostFilterLocations(filterResponse: CPFilterResponse) {
        val selectableFilterResponse = CPSelectableFilterResponse(filterResponse)
        selectableFilterResponse.Locations.selectElement(3)
        val filterRequest = CPFilterRequest(selectableFilterResponse)

        val pair: Pair<String, CPFilterRequest> = Pair("data", filterRequest)
        val hashMap: HashMap<String, CPFilterRequest> = hashMapOf(pair)

        filterInstance!!.postFilters(vinpointCredentials!!, hashMap).subscribe({ response ->
            Log.e("Result", response.toString())
            updateTestUiResult(textView_post_filter_locations_stat, true)
            testPostFilterModels(filterResponse)
        }, { error ->
            updateTestUiResult(textView_post_filter_locations_stat, false)
            testPostFilterModels(filterResponse)
        })
    }

    private fun testPostFilterModels(filterResponse: CPFilterResponse) {
        val selectableFilterResponse = CPSelectableFilterResponse(filterResponse)
        selectableFilterResponse.Models.selectElement(3)
        val filterRequest = CPFilterRequest(selectableFilterResponse)

        val pair: Pair<String, CPFilterRequest> = Pair("data", filterRequest)
        val hashMap: HashMap<String, CPFilterRequest> = hashMapOf(pair)

        filterInstance!!.postFilters(vinpointCredentials!!, hashMap).subscribe({ response ->
            Log.e("Result", response.toString())
            updateTestUiResult(textView_post_filter_model_stat, true)
            testPostFilterMakes(filterResponse)
        }, { error ->
            updateTestUiResult(textView_post_filter_model_stat, false)
            testPostFilterMakes(filterResponse)
        })
    }

    private fun testPostFilterMakes(filterResponse: CPFilterResponse) {
        val selectableFilterResponse = CPSelectableFilterResponse(filterResponse)
        selectableFilterResponse.Makes.selectElement(3)
        val filterRequest = CPFilterRequest(selectableFilterResponse)

        val pair: Pair<String, CPFilterRequest> = Pair("data", filterRequest)
        val hashMap: HashMap<String, CPFilterRequest> = hashMapOf(pair)

        filterInstance!!.postFilters(vinpointCredentials!!, hashMap).subscribe({ response ->
            Log.e("Result", response.toString())
            updateTestUiResult(textView_post_filter_make_stat, true)
            testPostFilterColors(filterResponse)
        }, { error ->
            updateTestUiResult(textView_post_filter_make_stat, false)
            testPostFilterColors(filterResponse)
        })
    }

    private fun testPostFilterColors(filterResponse: CPFilterResponse) {
        val selectableFilterResponse = CPSelectableFilterResponse(filterResponse)
        selectableFilterResponse.Colors.selectElement(3)
        val filterRequest = CPFilterRequest(selectableFilterResponse)

        val pair: Pair<String, CPFilterRequest> = Pair("data", filterRequest)
        val hashMap: HashMap<String, CPFilterRequest> = hashMapOf(pair)

        filterInstance!!.postFilters(vinpointCredentials!!, hashMap).subscribe({ response ->
            Log.e("Result", response.toString())
            updateTestUiResult(textView_post_filter_color_stat, true)
            testPostFilterTags(filterResponse)
        }, { error ->
            updateTestUiResult(textView_post_filter_color_stat, false)
            testPostFilterTags(filterResponse)
        })
    }

    private fun testPostFilterTags(filterResponse: CPFilterResponse) {
        val selectableFilterResponse = CPSelectableFilterResponse(filterResponse)
        selectableFilterResponse.Tags.selectElement(3)
        val filterRequest = CPFilterRequest(selectableFilterResponse)

        val pair: Pair<String, CPFilterRequest> = Pair("data", filterRequest)
        val hashMap: HashMap<String, CPFilterRequest> = hashMapOf(pair)

        filterInstance!!.postFilters(vinpointCredentials!!, hashMap).subscribe({ response ->
            Log.e("Result", response.toString())
            updateTestUiResult(textView_post_filter_tags_stat, true)
        }, { error ->
            updateTestUiResult(textView_post_filter_tags_stat, false)
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
