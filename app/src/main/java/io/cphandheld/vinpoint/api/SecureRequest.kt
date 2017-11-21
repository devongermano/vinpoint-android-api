package io.cphandheld.vinpoint.api

import io.cphandheld.vinpoint.api.models.Credentials
import io.cphandheld.vinpoint.api.models.StatusResponse
import io.reactivex.SingleEmitter

/**
 * Created by christian on 11/1/17.
 *
 * (C) CP Handheld Technologies, LLC
 */

class SecureRequest<T>(credentials: Credentials, method: Int, url: String, jsonRequest: Any?, responseType: Class<T>,
                       subscriber: SingleEmitter<T>, statusResponse: StatusResponse? = null)

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
