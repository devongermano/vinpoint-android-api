package io.cphandheld.vinpoint.api.models

import com.auth0.android.jwt.JWT


class CPCredentials(val authToken: String, val orgID: Int) {
    var parsed_jwt = JWT(authToken)

    public fun getUserId(): String? {
        return parsed_jwt.getClaim("sub").asString()
    }
}