package io.cphandheld.vinpoint.api.models.filter

import java.io.Serializable

/**
 * Created by devon on 2/21/18.
 * Copyright 2018 CPHandheld Technologies LLC.
 */

class CPFilterDealership : Serializable {
    var DealershipId: Int? = null
    var DealershipName: String? = null
}

class CPFilterLocation : Serializable {
    var LocationId: Int? = null
    var LocationName: String? = null
}

class CPFilterTag {
    var DefinitionId: Int? = null
    var TagName: String? = null
    var Color: String? = null
}