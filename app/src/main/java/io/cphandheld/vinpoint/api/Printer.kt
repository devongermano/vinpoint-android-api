package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.*
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPPrinter
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Printer(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val printerEndpoint: String? = VinpointAPI.Environment.PrinterAPIEndpoint

    fun getPrinters(credentials: CPCredentials, dealershipId: Int, statusResponse: CPStatusResponse? = null): Single<Array<CPPrinter>> {
        val url = "$printerEndpoint/group/$dealershipId/printers"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, Array<CPPrinter>::class.java, statusResponse)
    }
}