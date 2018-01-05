package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPDealership
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Dealership(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val vinpointEndpoint: String? = VinpointAPI.Environment.APIEndpoint

    fun getDealership(Credentials: CPCredentials, dealershipID: Int, statusResponse: CPStatusResponse? = null): Single<CPDealership> {
        val url = "$vinpointEndpoint/v1/Dealerships/$dealershipID"
        return RequestFactory.getSecureSingle(Credentials, queue, Request.Method.GET, url, null, CPDealership::class.java, statusResponse)
    }

    fun getDealerships(Credentials: CPCredentials, organizationId: Int, statusResponse: CPStatusResponse? = null): Single<Array<CPDealership>> {
        val url = "$vinpointEndpoint/v1/Dealerships/GetByOrg/$organizationId"
        return RequestFactory.getSecureSingle(Credentials, queue, Request.Method.GET, url, null, Array<CPDealership>::class.java, statusResponse)
    }
}