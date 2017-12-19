package io.cphandheld.vinpoint.api

import io.cphandheld.vinpoint.api.models.Credentials
import io.cphandheld.vinpoint.api.models.InventoryModel
import io.reactivex.Single

/**
 * Created by gencarnacion on 12/5/17.
 */
interface SyncInterface{
    fun getInventory(Credentials: Credentials, vin: String, dealershipId: String) : Single<InventoryModel>
}