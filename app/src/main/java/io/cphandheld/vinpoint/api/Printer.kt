package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.*
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.models.CPPrinter
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.request.RequestFactory
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.reactivex.Single


class Printer(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val endpoint: String? = VinpointAPI.Environment.PrinterAPIEndpoint

    fun getPrinters(Credentials: CPCredentials, dealershipId: Int, statusResponse: CPStatusResponse? = null): Single<Array<CPPrinter>> {
        val url = "$endpoint/group/$dealershipId/printers"
        return RequestFactory.getSecureSingle(Credentials, queue, GET, url, null, Array<CPPrinter>::class.java, statusResponse)
    }

    fun postPrint(Credentials: CPCredentials, inventoryID: Int, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<CPInventory> {
        val url = "$endpoint/queue/push/"
        return RequestFactory.getSecureSingle(Credentials, queue, POST, url, jsonRequest, CPInventory::class.java, statusResponse)
    }
}