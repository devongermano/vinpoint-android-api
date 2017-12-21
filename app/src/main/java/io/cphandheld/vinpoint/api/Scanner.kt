package io.cphandheld.vinpoint.api

import android.content.Context
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.cphandheld.vinpoint.api.sync.SyncInterface
import io.reactivex.Single


class Scanner(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    var mContext: Context = context

    fun scan(Credentials: CPCredentials, data: String, dealershipId: String,
             syncInterface: SyncInterface, statusResponse: CPStatusResponse): Single<CPInventory> {

        return syncInterface.getInventory(Credentials, data, dealershipId)
    }
}