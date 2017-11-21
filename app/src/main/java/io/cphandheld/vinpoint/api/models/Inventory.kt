package io.cphandheld.vinpoint.api.models

import android.nfc.Tag

/**
 * Created by christian on 10/31/17.
 */

class InventoryModel{

    var DealershipName = ""
    var LocationName = ""
    var Status = 0
    var Journal: Any? = null
    var JournalVehicle: Any? = null
    var Tags: Array<Tag>? = null
    var InventoryId = 0
    var InventoryStatus = ""
    var VehicleId = 0
    var OrganizationId = 0
    var DealershipId = 0
    var LocationId = 0
    var VIN = ""
    var Year = 0
    var Make = ""
    var Model = ""
    var Stock = ""
    var Color = ""
    var Mileage = 0
    var Cost = 0
    var Price = 0
    var Classification = ""
    var Latitude = 0.0
    var Longitude = 0.0
    var CreatedDate = ""
    var ScannedDate = ""
    var DeletedDate = ""
    var LastLabelDate: String? = null
    var DeletedReason: String? = null
    var ActivatedReason: String? = null

}

