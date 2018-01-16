package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.GET
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Scan(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val scannerEndpoint: String? = VinpointAPI.Environment.ScannerAPIEndpoint

    fun verifyVehicle(credentials: CPCredentials, vin: String, dealershipId: Int, orgId: Int, statusResponse: CPStatusResponse? = null): Single<CPInventory> {
        val url = "$scannerEndpoint/v1/Scanner/VerifyVehicle/$vin/DealershipId/$dealershipId/OrganizationId/$orgId"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, CPInventory::class.java, statusResponse)
    }
}