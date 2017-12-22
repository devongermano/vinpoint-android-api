package io.cphandheld.vinpoint.api.models

import io.realm.RealmList
import io.realm.RealmObject


open class CPInventory : RealmObject() {

    var DealershipName: String? = null
    var LocationName: String? = null
    var Status: Int? = null
//    var Journal: RealmList<CPJournal>? = null
//    var JournalVehicle: Any? = null
    var Tags: RealmList<CPTag>? = null
    var InventoryId: Int? = null
    var InventoryStatus: String? = null
    var VehicleId: Int? = null
    var OrganizationId: Int? = null
    var DealershipId: Int? = null
    var LocationId: Int? = null
    var VIN: String? = null
    var Year: Int? = null
    var Make: String? = null
    var Model: String? = null
    var Stock: String? = null
    var Color: String? = null
    var Mileage: Int? = null
    var Cost: Int? = null
    var Price: Int? = null
    var Classification: String? = null
    var Latitude: Double? = null
    var Longitude: Double? = null
    var CreatedDate: String? = null
    var ScannedDate: String? = null
    var DeletedDate: String? = null
    var LastLabelDate: String? = null
    var DeletedReason: String? = null
    var ActivatedReason: String? = null
}
