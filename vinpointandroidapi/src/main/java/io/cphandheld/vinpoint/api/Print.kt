package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.*
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Print(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val printerEndpoint: String? = VinpointAPI.Environment.PrinterAPIEndpoint

    fun postPrint(credentials: CPCredentials, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<Unit> {
        val url = "$printerEndpoint/queue/push/"
        return RequestFactory.getSecureSingle(credentials, queue, POST, url, jsonRequest, Unit::class.java, statusResponse)
    }
}