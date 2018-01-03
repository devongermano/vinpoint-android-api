package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Scan(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val endpoint: String? = VinpointAPI.Environment.ScannerAPIEndpoint

    fun postPrint(Credentials: CPCredentials, inventoryID: Int, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<CPInventory> {
        val url = "$endpoint/v1/Inventory/$inventoryID"
        return RequestFactory.getSecureSingle(Credentials, queue, Request.Method.PUT, url, jsonRequest, CPInventory::class.java, statusResponse)
    }
}