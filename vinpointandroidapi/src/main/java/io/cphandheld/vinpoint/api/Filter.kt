package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.GET
import com.android.volley.Request.Method.POST
import io.cphandheld.vinpoint.api.models.*
import io.cphandheld.vinpoint.api.models.filter.CPFilterResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single

/**
 * Created by devon on 2/13/18.
 * Copyright 2018 CPHandheld Technologies LLC.
 */

class Filter(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val vinpointEndpoint: String? = VinpointAPI.Environment.APIEndpoint


    fun getFilters(credentials: CPCredentials, statusResponse: CPStatusResponse? = null): Single<CPFilterResponse> {
        val url = "$vinpointEndpoint/v1/filters/byOrg/${credentials.orgID}"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, CPFilterResponse::class.java)
    }

    fun postFilters(credentials: CPCredentials, filterRequest: Any, statusResponse: CPStatusResponse? = null): Single<Array<CPInventory>> {
        val url = "$vinpointEndpoint/v1/filters/filterInventory/${credentials.orgID}"
        return RequestFactory.getSecureSingle(credentials, queue, POST, url, filterRequest, Array<CPInventory>::class.java, statusResponse)
    }
}