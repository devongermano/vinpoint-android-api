package io.cphandheld.vinpoint.api.models

import java.io.Serializable


open class CPTag : Serializable {

    var RecordId: Int? = null
    var TagDefinitionId: Int? = null
    var InventoryId: Int? = null
    var Color: String? = null
    var Label: String? = null
    var Status: Int? = null
    var OrganizationId: Int? = null
    var DefaultStatus: Int? = null
    var Type: String? = null
}