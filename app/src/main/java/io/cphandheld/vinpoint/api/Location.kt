package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPLocation
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Location(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val vinpointEndpoint: String? = VinpointAPI.Environment.APIEndpoint

    fun getLocation(credentials: CPCredentials, locationId: Int): Single<CPLocation> {
        val url = "$vinpointEndpoint/v1/Locations/$locationId"
        return RequestFactory.getSecureSingle(credentials, queue, Request.Method.GET, url, null, CPLocation::class.java)
    }

    fun getLocationsByDealershipId(credentials: CPCredentials, dealershipId: Int): Single<Array<CPLocation>> {
        val url = "$vinpointEndpoint/v1/Locations/Dealership/$dealershipId"
        return RequestFactory.getSecureSingle(credentials, queue, Request.Method.GET, url, null, Array<CPLocation>::class.java)
    }

    fun getLocationsByOrgId(credentials: CPCredentials, orgId: Int): Single<Array<CPLocation>> {
        val url = "$vinpointEndpoint/v1/Locations/ByOrg/$orgId"
        return RequestFactory.getSecureSingle(credentials, queue, Request.Method.GET, url, null, Array<CPLocation>::class.java)
    }
}