package io.cphandheld.vinpoint.api

import android.content.Context
import io.cphandheld.vinpoint.api.models.Credentials
import io.cphandheld.vinpoint.api.models.InventoryModel
import io.cphandheld.vinpoint.api.models.StatusResponse
import io.reactivex.Single

/**
 * Created by gencarnacion on 12/5/17.
 */

class Scanner(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    var mContext: Context = context

    fun scan(Credentials: Credentials, data: String, dealershipId: String, syncInterface: SyncInterface, statusResponse: StatusResponse): Single<InventoryModel> {

       return syncInterface.getInventory(Credentials, data, dealershipId)

    }
}