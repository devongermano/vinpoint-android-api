package io.cphandheld.vinpoint.api

import io.cphandheld.vinpoint.api.models.Credentials
import io.cphandheld.vinpoint.api.Request
import io.reactivex.Single

/**
* Created by christian on 11/3/17.
*
* (C) CP Handheld Technologies, LLC
*/

class RequestFactory{

    companion object {
        fun <T> getSecureSingle(credentials: Credentials, queue: VolleySingleton, method: Int, url: String, jsonRequest: Any?, responseType: Class<T>): Single<T> {
            return Single.create { subscriber ->
                val request = SecureRequest(credentials, method, url, jsonRequest, responseType, subscriber)
                queue.addToRequestQueue(request)
            }
        }

        fun <T> getSingle(queue: VolleySingleton, method: Int, url: String, jsonRequest: Any?, responseType: Class<T>): Single<T>{
            return Single.create { subscriber ->
                val request = Request(method, url, jsonRequest, responseType, subscriber)
                queue.addToRequestQueue(request)
            }
        }


    }




}