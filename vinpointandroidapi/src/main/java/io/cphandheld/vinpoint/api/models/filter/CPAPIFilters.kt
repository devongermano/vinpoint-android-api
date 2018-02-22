package io.cphandheld.vinpoint.api.models.filter

import java.io.Serializable

/**
 * Created by devon on 2/21/18.
 * Copyright 2018 CPHandheld Technologies LLC.
 */

const val DEALERSHIPS = "Dealerships"
const val LOCATIONS = "Locations"
const val TAGS = "Tags"
const val MAKES = "Makes"
const val MODELS = "Models"
const val COLORS  = "Colors"
const val YEARS = "Years"

class CPFilterResponse: Serializable {

    var Dealerships: List<CPFilterDealership>? = null
    var Locations: List<CPFilterLocation>? = null
    var Tags: List<CPFilterTag>? = null
    var Makes: List<String>? = null
    var Models: List<String>? = null
    var Colors: List<String>? = null
    var Years: List<Int>? = null

    fun toSelectableFilters(): CPSelectableFilters {
        val selectableFilters = CPSelectableFilters()

        selectableFilters.insert(MAKES, Makes!!, { m, id ->
            return@insert CPFilterEntry(m, id, m)
        })

        selectableFilters.insert(DEALERSHIPS, Dealerships!!, { d, id ->
            return@insert CPFilterEntry(d.DealershipName!!, id, d.DealershipId!!)
        })

        selectableFilters.insert(LOCATIONS, Locations!!, { l, id ->
            return@insert CPFilterEntry(l.LocationName!!, id, l.LocationId!!)
        })

        selectableFilters.insert(TAGS, Tags!!, { t, id ->
            return@insert CPFilterEntry(t.TagName!!, id, t.DefinitionId!!)
        })

        selectableFilters.insert(MODELS, Models!!, { mo, id ->
            return@insert CPFilterEntry(mo, id, mo)
        })

        selectableFilters.insert(COLORS, Colors!!, { c, id ->
            return@insert CPFilterEntry(c, id, c)
        })

        selectableFilters.insert(YEARS, Years!!, { y, id ->
            return@insert CPFilterEntry(y.toString(), id, y)
        })
        return selectableFilters
    }
}

class CPFilterRequest(selectableFilters: CPSelectableFilters): Serializable {

    var DealershipIds: List<Int> = selectableFilters.get(DEALERSHIPS)
    var LocationIds: List<Int> = selectableFilters.get(LOCATIONS)
    var TagDefIds: List<Int> = selectableFilters.get(TAGS)
    var Makes: List<String> = selectableFilters.get(MAKES)
    var Models: List<String> = selectableFilters.get(MODELS)
    var Colors: List<String> = selectableFilters.get(COLORS)
    var Years: List<Int> = selectableFilters.get(YEARS)
}