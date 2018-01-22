package io.cphandheld.vinpoint.api.models

import java.io.Serializable


open class CPLocation : Serializable {

    var RecordId: Int? = null
    var DealershipId: Int? = null
    var Name: String? = null
    var IsGeoEnabled: String? = null
    var CreatedDate: String? = null
    var CreatedUser: Int? = null
    var DeletedDate: String? = null
    var DeletedUser: Int? = null
    var LastUpdatedDate: String? = null
    var LastUpdatedUser: Int? = null
    var GeoFence: String? = null

    override fun toString(): String {
        return this.Name ?: ""
    }
}