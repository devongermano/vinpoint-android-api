package io.cphandheld.vinpoint.api.request

import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.reactivex.SingleEmitter

/**
 * Created by christian on 11/1/17.
 *
 * (C) CP Handheld Technologies, LLC
 */

class SecureRequest<T>(credentials: CPCredentials, method: Int, url: String, jsonRequest: Any?, responseType: Class<T>,
                       subscriber: SingleEmitter<T>, statusResponse: CPStatusResponse? = null)

    : Request<T>(method, url, jsonRequest, responseType, subscriber, statusResponse) {

    private val credentials = credentials

    //Set Headers
    override fun getHeaders(): MutableMap<String, String> {
        val headers = HashMap<String, String>()
        headers.put("Content-Type", "application/json")
        headers.put("Authorization", "Bearer ${credentials.authToken}")
        return headers
    }

}