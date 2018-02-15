package io.cphandheld.vinpoint.api.models

import java.io.Serializable

/**
 * Created by devon on 2/13/18.
 * Bear with me here. Get it. Bear?
 * When adding a new filter, of a non generic type specifically Int or String,
 * be sure to add it to the getSelectedElements call switch statement
 * Copyright 2018 CPHandheld Technologies LLC.
 */

class CPFilterResponse: Serializable {

    var Dealerships: List<CPFilterDealership>? = null
    var Locations: List<CPFilterLocation>? = null
    var Tags: List<CPFilterTag>? = null
    var Makes: List<String>? = null
    var Models: List<String>? = null
    var Colors: List<String>? = null
    var Years: List<Int>? = null
}

class CPSelectableFilterResponse(filterResponse: CPFilterResponse) {

    var Dealerships = CPSelectableFilterCollection(filterResponse.Dealerships!!)
    var Locations =  CPSelectableFilterCollection(filterResponse.Locations!!)
    var Tags = CPSelectableFilterCollection(filterResponse.Tags!!)
    var Makes = CPSelectableFilterCollection(filterResponse.Makes!!)
    var Models = CPSelectableFilterCollection(filterResponse.Models!!)
    var Colors = CPSelectableFilterCollection(filterResponse.Colors!!)
    var Years = CPSelectableFilterCollection(filterResponse.Years!!)
}


class CPFilterRequest(selectableFilterResponse: CPSelectableFilterResponse): Serializable {

    var DealershipIds: List<Int> = selectableFilterResponse.Dealerships.getSelectedElements()
    var LocationIds: List<Int> = selectableFilterResponse.Locations.getSelectedElements()
    var TagDefIds: List<Int> = selectableFilterResponse.Tags.getSelectedElements()
    var Makes: List<String> = selectableFilterResponse.Makes.getSelectedElements()
    var Models: List<String> = selectableFilterResponse.Models.getSelectedElements()
    var Colors: List<String> = selectableFilterResponse.Colors.getSelectedElements()
    var Years: List<Int> = selectableFilterResponse.Years.getSelectedElements()
}

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

class CPSelectableFilterCollection<T>(collection: List<T>) {

    var collection: List<T> = collection
    var selected: HashSet<Int> = HashSet() // Indices

    /* Selects the element, by adding or removing index from the collection */
    fun selectElement(element: Int) {
        if(element in this.selected) {
            selected.remove(element)
        } else {
            selected.add(element)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <R> getSelectedElements(): List<R> {

        val selected: ArrayList<T> = arrayListOf()

        return this.selected.mapTo(selected) { collection[it] }.map({ x ->
            when(x) {
                is CPFilterDealership -> return@map x.DealershipId as R
                is CPFilterLocation -> return@map x.LocationId as R
                is CPFilterTag -> return@map x.DefinitionId as R
                is String -> return@map x as R
                is Int -> return@map x as R
                else -> {
                    return@map x as R
                }
            }
        })
    }
}

