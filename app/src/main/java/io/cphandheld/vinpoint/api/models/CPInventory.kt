package io.cphandheld.vinpoint.api.models

import io.realm.RealmObject


class CPInventory : RealmObject() {

    var DealershipName: String? = null
    var LocationName: String? = null
    var Status: Number? = null;
    var Journal: Any? = null
    var JournalVehicle: Any? = null
    var Tags: Array<CPTag>? = null
    var InventoryId: Number? = null
    var InventoryStatus: String? = null
    var VehicleId: Number? = null
    var OrganizationId: Number? = null
    var DealershipId: Number? = null
    var LocationId: Number? = null
    var VIN: String? = null
    var Year: Number? = null
    var Make: String? = null
    var Model: String? = null
    var Stock: String? = null
    var Color: String? = null
    var Mileage: Number? = null
    var Cost: Number? = null
    var Price: Number? = null
    var Classification: String? = null
    var Latitude: Number? = null
    var Longitude: Number? = null
    var CreatedDate: String? = null
    var ScannedDate: String? = null
    var DeletedDate: String? = null
    var LastLabelDate: String? = null
    var DeletedReason: String? = null
    var ActivatedReason: String? = null
}

