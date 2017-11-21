package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.Request.Method.GET
import io.cphandheld.vinpoint.api.models.Credentials
import io.cphandheld.vinpoint.api.models.InventoryModel
import io.reactivex.Single

/**
* Created by christian on 11/2/17.
*
* (C) CP Handheld Technologies, LLC
*/

class Inventory(context: Context){

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)

    fun search(Credentials: Credentials, idFragment: String, filter: String): Single<Array<InventoryModel>> {

        var url = queue.buildURL("/v1/Inventory/extendedsearch/$idFragment/${Credentials.orgID}")

        return RequestFactory.getSecureSingle(Credentials, queue, GET, url, null, Array<InventoryModel>::class.java)

    }

    fun get(Credentials: Credentials, inventoryID: Int): Single<InventoryModel> {

        var url = queue.buildURL("/v1/Inventory/$inventoryID")

        return RequestFactory.getSecureSingle(Credentials, queue, GET, url, null, InventoryModel::class.java)

    }

//    fun markRemoved(inventoryModel: InventoryModel){
//
//        var url = queue.buildURL("/v1/Inventory/delete")
//
//    }

}