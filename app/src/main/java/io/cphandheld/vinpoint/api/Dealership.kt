package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPDealership
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.request.RequestFactory
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.reactivex.Single


class Dealership(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)

    fun getDealership(Credentials: CPCredentials, dealershipID: Int, statusResponse: CPStatusResponse? = null): Single<CPDealership> {
        val url = queue.buildURL("/v1/Dealerships/$dealershipID")
        return RequestFactory.getSecureSingle(Credentials, queue, Request.Method.GET, url, null, CPDealership::class.java, statusResponse)
    }
}