package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.POST
import io.cphandheld.vinpoint.api.models.CPSecurity
import io.cphandheld.vinpoint.api.models.CPSecurityRequest
import io.cphandheld.vinpoint.api.request.RequestFactory
import io.cphandheld.vinpoint.api.singleton.VolleySingleton
import io.reactivex.Single


class Security(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)

    fun login(username: String, password: String): Single<CPSecurity> {

        val url = "https://cpht.auth0.com/oauth/ro"

        val request = CPSecurityRequest()

        request.username = username
        request.password = password
        request.client_id = "ZewaRueG57rtsjl6n6FZgXE0yHk4wInS"
        request.connection = "Username-Password-Authentication"
        request.scope = "openid roles uid"
        request.grant_type = "password"

        return RequestFactory.getSingle(queue, POST, url, request, CPSecurity::class.java)
    }

}