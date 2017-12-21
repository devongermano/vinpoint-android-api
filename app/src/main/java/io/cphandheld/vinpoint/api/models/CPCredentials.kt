package io.cphandheld.vinpoint.api.models


class CPCredentials(token: String, id: Int) {

    var authToken: String? = null
    var orgID: Int? = null

    init {
        authToken = token
        orgID = id
    }
}