package io.cphandheld.vinpoint.api.models

/**
 * Created by christian on 11/2/17.
 */

class Credentials(token: String, id: Int){

    var authToken: String = ""
    var orgID: Int = 0

    init{

        //Set authToken
        authToken = token
        //Set orgID
        orgID = id

    }
}