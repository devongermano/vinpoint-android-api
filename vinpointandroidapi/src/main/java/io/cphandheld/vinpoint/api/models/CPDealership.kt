package io.cphandheld.vinpoint.api.models

import java.io.Serializable


open class CPDealership : Serializable {

    var RecordId: Int? = null
    var Name: String? = null
    var OrganizationId: Int? = null
    var PrinterGroupId: Int? = null
    var ScannerGroupId: Int? = null
    var Address: String? = null
    var City: String? = null
    var State: String? = null
    var Zip: String? = null
    var Phone: String? = null
    var UserDealershipId: Int? = null

    override fun toString(): String {
        return this.Name ?: ""
    }
}