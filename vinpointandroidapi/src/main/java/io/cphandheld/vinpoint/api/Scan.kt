package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.POST
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Scan(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val scannerEndpoint: String? = VinpointAPI.Environment.ScannerAPIEndpoint

    fun postPrint(credentials: CPCredentials, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<Unit> {
        val url = "$scannerEndpoint/queue/push"
        return RequestFactory.getSecureSingle(credentials, queue, POST, url, jsonRequest, Unit::class.java, statusResponse)
    }
}