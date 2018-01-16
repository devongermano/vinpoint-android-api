package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.*
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Inventory(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val vinpointEndpoint: String? = VinpointAPI.Environment.APIEndpoint

    fun search(credentials: CPCredentials, idFragment: String, filter: String, statusResponse: CPStatusResponse? = null): Single<Array<CPInventory>> {
        val url = "$vinpointEndpoint/v1/Inventory/extendedsearch/$idFragment/${credentials.orgID}"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, Array<CPInventory>::class.java, statusResponse)
    }

    fun getInventory(credentials: CPCredentials, statusResponse: CPStatusResponse? = null): Single<Array<CPInventory>> {
        val url = "$vinpointEndpoint/v1/Inventory/ByOrg/${credentials.orgID}"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, Array<CPInventory>::class.java, statusResponse)
    }

    fun getInventoryItem(credentials: CPCredentials, inventoryID: Int, statusResponse: CPStatusResponse? = null): Single<CPInventory> {
        val url = "$vinpointEndpoint/v1/Inventory/$inventoryID"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, CPInventory::class.java, statusResponse)
    }

    fun postInventoryItem(credentials: CPCredentials, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<Unit> {
        val url = "$vinpointEndpoint/v1/Inventory"
        return RequestFactory.getSecureSingle(credentials, queue, POST, url, jsonRequest, Unit::class.java, statusResponse)
    }

    fun putInventoryItem(credentials: CPCredentials, inventoryID: Int, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<Unit> {
        val url = "$vinpointEndpoint/v1/Inventory/$inventoryID"
        return RequestFactory.getSecureSingle(credentials, queue, PUT, url, jsonRequest, Unit::class.java, statusResponse)
    }
}