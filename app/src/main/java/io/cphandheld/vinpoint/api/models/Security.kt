package io.cphandheld.vinpoint.api.models

/**
 * Created by christian on 10/31/17.
 */

class LoginRequest {

    var email = ""

    var password = ""

    var provider = ""

}

class LoginResponse {

    var authToken = ""

    var message = ""

    var status = ""
}
