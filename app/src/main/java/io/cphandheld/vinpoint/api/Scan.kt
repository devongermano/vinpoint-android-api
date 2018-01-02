package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.request.RequestFactory
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.reactivex.Single

/**
 * Created by devon on 1/2/18.
 */

class Scan(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)

    fun postPrint(Credentials: CPCredentials, inventoryID: Int, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<CPInventory> {
        val url = queue.buildURL("/v1/Inventory/$inventoryID")
        return RequestFactory.getSecureSingle(Credentials, queue, Request.Method.PUT, url, jsonRequest, CPInventory::class.java, statusResponse)
    }
}