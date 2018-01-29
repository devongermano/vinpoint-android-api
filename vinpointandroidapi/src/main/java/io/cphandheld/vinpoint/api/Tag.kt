package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.GET
import com.android.volley.Request.Method.POST
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPOrganization
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.models.CPTag
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Tag(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val endpoint: String? = VinpointAPI.Environment.APIEndpoint

    fun getTags(credentials: CPCredentials, orgId: Int, statusResponse: CPStatusResponse? = null): Single<Array<CPTag>> {
        val url = "$endpoint/v1/Organizations/$orgId"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, Array<CPTag>::class.java, statusResponse)
    }

    fun upsertTags(credentials: CPCredentials, jsonRequest: Any, statusResponse: CPStatusResponse? = null): Single<Array<CPTag>> {
        val url = "$endpoint/v1/tags/UpsertTagsBatch"
        return RequestFactory.getSecureSingle(credentials, queue, POST, url, jsonRequest, Array<CPTag>::class.java, statusResponse)
    }
}