package io.cphandheld.vinpoint.api.request

import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.reactivex.Single

/**
* Created by christian on 11/3/17.
*
* (C) CP Handheld Technologies, LLC
*/

class RequestFactory {

    companion object {
        fun <T> getSecureSingle(credentials: CPCredentials, queue: VolleySingleton, method: Int, url: String, jsonRequest: Any?,
                                responseType: Class<T>, statusResponse: CPStatusResponse? = null): Single<T> {
            return Single.create { subscriber ->
                val request = SecureRequest(credentials, method, url, jsonRequest, responseType, subscriber, statusResponse)
                queue.addToRequestQueue(request)
            }
        }

        fun <T> getSingle(queue: VolleySingleton, method: Int, url: String, jsonRequest: Any?,
                          responseType: Class<T>, statusResponse: CPStatusResponse? = null): Single<T>{
            return Single.create { subscriber ->
                val request = Request(method, url, jsonRequest, responseType, subscriber, statusResponse)
                queue.addToRequestQueue(request)
            }
        }
    }
}