package io.cphandheld.vinpoint.api.models

import io.realm.RealmObject
import io.realm.annotations.Ignore

/**
 * Created by gencarnacion on 12/19/17.
 */
class CPDecodedVIN : RealmObject() {

    @Ignore
    var Year = 0

    var VINStart = ""
    var Make = ""
    var Model = ""
}