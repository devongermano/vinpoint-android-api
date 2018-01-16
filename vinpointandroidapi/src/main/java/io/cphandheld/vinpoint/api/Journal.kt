package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPJournal
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Journal(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)

    private val vinpointEndpoint: String? = VinpointAPI.Environment.APIEndpoint
    private val scannerEndpoint: String? = VinpointAPI.Environment.ScannerAPIEndpoint
    private val printerEndpoint: String? = VinpointAPI.Environment.PrinterAPIEndpoint

    fun getVinpointJournal(credentials: CPCredentials, inventoryId: Int, statusResponse: CPStatusResponse? = null): Single<Array<CPJournal>> {
        val url = "$vinpointEndpoint/v1/Journal/ByInventory/$inventoryId"
        return RequestFactory.getSecureSingle(credentials, queue, Request.Method.GET, url, null, Array<CPJournal>::class.java, statusResponse)
    }

    fun getPrinterJournal(credentials: CPCredentials, vin: String, statusResponse: CPStatusResponse? = null): Single<Array<CPJournal>> {
        val url = "$printerEndpoint/queue/journal/$vin"
        return RequestFactory.getSecureSingle(credentials, queue, Request.Method.GET, url, null, Array<CPJournal>::class.java, statusResponse)
    }

    fun getScannerJournal(credentials: CPCredentials, vin: String, statusResponse: CPStatusResponse? = null): Single<Array<CPJournal>> {
        val url = "$scannerEndpoint/queue/journal/$vin"
        return RequestFactory.getSecureSingle(credentials, queue, Request.Method.GET, url, null, Array<CPJournal>::class.java, statusResponse)
    }
}