package io.cphandheld.vinpoint.api.sync

import android.content.Context
import com.android.volley.Request
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.cphandheld.vinpoint.api.hasActiveInternetConnection
import io.cphandheld.vinpoint.api.models.CPDecodedVIN
import io.reactivex.Single
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.request.RequestFactory
import io.realm.Realm
import io.realm.kotlin.where


class SyncAdapter constructor(context: Context) : SyncInterface {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    var mContext: Context = context

    override fun getInventory(Credentials: CPCredentials, vin: String, dealershipId: String) : Single<CPInventory> {

        var url = queue.buildURL("/v1/Scanner/VerifyVehicle/$vin/DealershipId/$dealershipId/OrganizationId/${Credentials.orgID}")

        var inventory: Single<CPInventory>

        // check internet connection
        // If no internet, check local (uses SyncAdapter)
        if (hasActiveInternetConnection(mContext)) {

            // verify vehicle call
            inventory = RequestFactory.getSecureSingle(Credentials, queue, Request.Method.GET, url, null, CPInventory::class.java)
        } else {
            // getInventoryItem from local cache
            inventory = Realm.getDefaultInstance().use {
                it.where<CPInventory>().equalTo("vin", vin).findFirst() as Single<CPInventory>
            }

            if (inventory == null) {
                // getInventoryItem data from Stacey db
                // this will only have vin, year, make, and model
                var decodedVIN = Realm.getDefaultInstance().use {
                    it.where<CPDecodedVIN>().equalTo("vin", vin.substring(0, 7)).findFirst() as Single<CPDecodedVIN>
                }

            }
        }
        return inventory
    }

}