package io.cphandheld.vinpoint.api

import android.content.Context
import com.android.volley.Request.Method.POST
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single
import io.cphandheld.vinpoint.api.models.*
import io.cphandheld.vinpoint.api.utility.RequestFactory.Companion.getSingle


class Security(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)

    private val vinpointAuth0ClientID: String? = VinpointAPI.Environment.VinpointAuth0ClientID
    private val scannerAuth0ClientID: String? = VinpointAPI.Environment.ScannerAuth0ClientID
    private val auth0Endpoint: String? = VinpointAPI.Environment.Auth0Endpoint


    fun login(username: String, password: String): Single<CPSecurity> {

        val request = CPSecurityRequest()

        request.username = username
        request.password = password
        request.client_id = VinpointAPI.Environment.VinpointAuth0ClientID
        request.connection = "Username-Password-Authentication"
        request.scope = "openid roles uid"
        request.grant_type = "password"

        return getSingle(queue, POST, auth0Endpoint!!, request, CPSecurity::class.java)
    }

    fun delegateScannerAPI(credentials: CPCredentials, statusResponse: CPStatusResponse? = null): Single<CPSecurity> {

        val delegator = CPDelegator()

        delegator.id_token = credentials.authToken
        delegator.client_id = vinpointAuth0ClientID
        delegator.grant_type = "urn:ietf:params:oauth:grant-type:jwt-bearer"
        delegator.scope = "openid roles"
        delegator.target = scannerAuth0ClientID

        return getSingle(queue, POST, "https://cpht.auth0.com/delegation", delegator, CPSecurity::class.java)

    }
}