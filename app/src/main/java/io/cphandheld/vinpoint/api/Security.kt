package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.POST
import io.cphandheld.vinpoint.api.models.CPSecurity
import io.cphandheld.vinpoint.api.models.CPSecurityRequest
import io.cphandheld.vinpoint.api.request.RequestFactory
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.reactivex.Single

/**
* Created by christian on 11/2/17.
*
* (C) CP Handheld Technologies, LLC
*/

class Security(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)

    fun login(clientID: Int, username: String, password: String): Single<CPSecurity> {

        val url = queue.buildURL("/Security/login")

        val request = CPSecurityRequest()

        request.username = username
        request.password = password

        return RequestFactory.getSingle(queue, POST, url, request, CPSecurity::class.java)
    }
}