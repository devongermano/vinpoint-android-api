package io.cphandheld.vinpoint.api.models.filter

import java.util.HashSet

/**
 * Created by devon on 2/21/18.
 * Copyright 2018 CPHandheld Technologies LLC.
 */

class CPSelectableFilters {

    val categories = HashMap<String, HashMap<Int, CPFilterEntry>>()
    val selectedIds = HashSet<Int>()
    var nextId = 0

    fun <T> get(category: String): List<T> {
        val selected = getSelectedForCategory(category)
        return selected.map({ x ->
            return@map x.result as T
        })
    }

    fun getCategories(): List<String> {
        return categories.map {
            it.key
        }
    }

    fun getElementsForCategory(category: String): List<CPFilterEntry> {
        return categories[category]!!.map {
            it.value
        }
    }

    fun <T> insert(categoryName: String, elements: List<T>, f: (T, Int) -> CPFilterEntry) {

        val category = HashMap<Int, CPFilterEntry>()

        for(i in elements) {
            category[nextId] = f(i, nextId)
            nextId += 1
        }

        categories[categoryName] = category
    }

    fun select(id: Int) {
        if(selectedIds.contains(id)) {
            selectedIds.remove(id)
        } else {
            selectedIds.add(id)
        }
    }

    fun getSelectedForCategory(category: String): List<CPFilterEntry> {
        return categories[category]!!.map {
            it.value
        }.filter {
            selectedIds.contains(it.id)
        }
    }
}