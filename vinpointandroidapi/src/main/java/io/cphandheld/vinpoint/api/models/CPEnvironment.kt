package io.cphandheld.vinpoint.api.models

import java.io.Serializable


open class CPEnvironment: Serializable {

    var APIEndpoint: String? = null
    var PrinterAPIEndpoint: String? = null
    var ScannerAPIEndpoint: String? = null
    var Auth0Endpoint: String? = null
    var VinpointAuth0ClientID: String? = null
    var ScannerAuth0ClientID: String? = null
}