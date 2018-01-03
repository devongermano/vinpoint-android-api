package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.GET
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPOrganization
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.request.RequestFactory
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.reactivex.Single

class Organization(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val endpoint: String? = VinpointAPI.Environment.APIEndpoint

    fun getOrganizations(Credentials: CPCredentials, statusResponse: CPStatusResponse? = null): Single<Array<CPOrganization>> {
        val url = "$endpoint/v1/organizations/getbyuserid"
        return RequestFactory.getSecureSingle(Credentials, queue, GET, url, null, Array<CPOrganization>::class.java, statusResponse)
    }

}
