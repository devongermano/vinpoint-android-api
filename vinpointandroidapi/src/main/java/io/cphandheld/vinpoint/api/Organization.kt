package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.GET
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPOrganization
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Organization(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val vinpointEndpoint: String? = VinpointAPI.Environment.APIEndpoint

    fun getOrganization(credentials: CPCredentials, orgId:Int, statusResponse: CPStatusResponse? = null): Single<CPOrganization> {
        val url = "$vinpointEndpoint/v1/Organizations/$orgId"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, CPOrganization::class.java, statusResponse)
    }

    fun getOrganizationsByUserId(credentials: CPCredentials, statusResponse: CPStatusResponse? = null): Single<Array<CPOrganization>> {
        val url = "$vinpointEndpoint/v1/organizations/getbyuserid"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, Array<CPOrganization>::class.java, statusResponse)
    }
}