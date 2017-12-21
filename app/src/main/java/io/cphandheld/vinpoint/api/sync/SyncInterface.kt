package io.cphandheld.vinpoint.api.sync

import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.InventoryModel
import io.reactivex.Single

/**
 * Created by gencarnacion on 12/5/17.
 */
interface SyncInterface{
    fun getInventory(Credentials: CPCredentials, vin: String, dealershipId: String) : Single<InventoryModel>
}