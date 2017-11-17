package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.Request.Method.GET
import io.cphandheld.vinpoint.api.models.Credentials
import io.cphandheld.vinpoint.api.models.InventoryArray
import io.cphandheld.vinpoint.api.models.InventoryModel
import io.cphandheld.vinpoint.api.models.InventoryObject
import io.reactivex.Single

/**
* Created by christian on 11/2/17.
*
* (C) CP Handheld Technologies, LLC
*/

class Inventory(context: Context){

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)

    fun search(Credentials: Credentials, idFragment: String, filter: String): Single<InventoryArray> {

        var url = queue.buildURL("/v1/Inventory/extendedsearch/$idFragment/${Credentials.orgID}")

        return RequestFactory.getSecureSingle(Credentials, queue, GET, url, null, InventoryArray::class.java)

    }

    fun get(Credentials: Credentials, inventoryID: Int): Single<InventoryObject> {

        var url = queue.buildURL("/v1/Inventory/$inventoryID")

        return RequestFactory.getSecureSingle(Credentials, queue, GET, url, null, InventoryObject::class.java)

    }

//    fun markRemoved(inventoryModel: InventoryModel){
//
//        var url = queue.buildURL("/v1/Inventory/delete")
//
//    }

}