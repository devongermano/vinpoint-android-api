package io.cphandheld.vinpoint.api.models

import io.realm.RealmObject
import io.realm.annotations.Ignore


class CPDecodedVIN : RealmObject() {

    @Ignore // Ignore this when adding this object to the database
    var Year: Number? = null
    var VINStart: String? = null
    var Make: String? = null
    var Model: String? = null
}