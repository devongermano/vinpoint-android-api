package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.*
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.request.RequestFactory
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.reactivex.Single


class Inventory(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)

    fun search(Credentials: CPCredentials, idFragment: String, filter: String, statusResponse: CPStatusResponse? = null): Single<Array<CPInventory>> {
        val url = queue.buildURL("/v1/Inventory/extendedsearch/$idFragment/${Credentials.orgID}")
        return RequestFactory.getSecureSingle(Credentials, queue, GET, url, null, Array<CPInventory>::class.java, statusResponse)
    }

    fun getInventory(Credentials: CPCredentials, statusResponse: CPStatusResponse? = null): Single<Array<CPInventory>> {
        val url = queue.buildURL("/v1/Inventory/ByOrg/${Credentials.orgID}")
        return RequestFactory.getSecureSingle(Credentials, queue, GET, url, null, Array<CPInventory>::class.java, statusResponse)
    }

    fun getInventoryItem(Credentials: CPCredentials, inventoryID: Int, statusResponse: CPStatusResponse? = null): Single<CPInventory> {
        val url = queue.buildURL("/v1/Inventory/$inventoryID")
        return RequestFactory.getSecureSingle(Credentials, queue, GET, url, null, CPInventory::class.java, statusResponse)
    }

    fun postInventoryItem(Credentials: CPCredentials, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<CPInventory> {
        val url = queue.buildURL("/v1/Inventory")
        return RequestFactory.getSecureSingle(Credentials, queue, POST, url, jsonRequest, CPInventory::class.java, statusResponse)
    }

    fun putInventoryItem(Credentials: CPCredentials, inventoryID: Int, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<CPInventory> {
        val url = queue.buildURL("/v1/Inventory/$inventoryID")
        return RequestFactory.getSecureSingle(Credentials, queue, PUT, url, jsonRequest, CPInventory::class.java, statusResponse)
    }
}