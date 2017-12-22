package io.cphandheld.vinpoint.api.models


class CPCredentials(token: String, orgId: Int) {

    var authToken: String? = null
    var orgID: Int? = null

    init {
        authToken = token
        orgID = orgId
    }
}