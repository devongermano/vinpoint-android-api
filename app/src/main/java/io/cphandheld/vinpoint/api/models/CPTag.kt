package io.cphandheld.vinpoint.api.models

import io.realm.RealmObject


open class CPTag: RealmObject() {

    var RecordId: Int? = null
    var TagDefinitionId: Int? = null
    var InventoryId: Int? = null
    var Color: String? = null
    var Label: String? = null
    var Status: Int? = null
    var OrganizationId: Int? = null
}
