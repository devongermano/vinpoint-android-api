package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.POST
import io.cphandheld.vinpoint.api.models.LoginRequest
import io.cphandheld.vinpoint.api.models.LoginResponse
import io.reactivex.Single

/**
* Created by christian on 11/2/17.
*
* (C) CP Handheld Technologies, LLC
*/

class Security(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)


    fun login(clientID: Int, email: String, password: String): Single<LoginResponse> {

        val url = queue.buildURL("/Security/login")

        val request = LoginRequest()

        request.email = email
        request.password = password
        request.provider = "Engenx"

        return RequestFactory.getSingle(queue, POST, url, request, LoginResponse::class.java)

    }
}