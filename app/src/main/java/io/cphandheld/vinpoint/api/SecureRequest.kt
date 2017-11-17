package io.cphandheld.vinpoint.api

import io.cphandheld.vinpoint.api.models.Credentials
import io.reactivex.SingleEmitter

/**
 * Created by christian on 11/1/17.
 */

class SecureRequest<T>
constructor(credentials: Credentials, method: Int, url: String, jsonRequest: Any?, responseType: Class<T>, subscriber: SingleEmitter<T>)
    : Request<T>(method, url, jsonRequest, responseType, subscriber){

    private val credentials = credentials

    //Set Headers
    override fun getHeaders(): MutableMap<String, String> {
        val headers = HashMap<String, String>()
        headers.put("Content-Type", "application/json")
        headers.put("Authorization", "Bearer ${credentials.authToken}")
        return headers
    }

}
