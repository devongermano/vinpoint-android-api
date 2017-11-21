package io.cphandheld.vinpoint.api

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Created by ericrudisill on 11/21/17.
 *
 * https://stackoverflow.com/questions/23070298/get-nested-json-object-with-gson-using-retrofit
 */

open class GsonDataDeserializer<T>(elementNameIn: String) : JsonDeserializer<T>
{
    private val elementName = elementNameIn

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): T {
        val element: JsonElement? = json!!.asJsonObject.get(elementName)
        val root = element ?: json
        return Gson().fromJson(root, typeOfT)
    }
}

