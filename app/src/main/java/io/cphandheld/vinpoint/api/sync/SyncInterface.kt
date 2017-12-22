package io.cphandheld.vinpoint.api.sync

import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.reactivex.Single


interface SyncInterface{
    fun getInventory(Credentials: CPCredentials, vin: String, dealershipId: String) : Single<CPInventory>
}