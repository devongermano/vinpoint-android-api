package io.cphandheld.vinpoint.api

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Created by ericrudisill on 11/21/17.
 *
 * https://stackoverflow.com/questions/23070298/getInventoryItem-nested-json-object-with-gson-using-retrofit
 */

open class GsonDataDeserializer<T>(elementNameIn: String) : JsonDeserializer<T>
{
    private val elementName = elementNameIn

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): T {
        // If user passed Unit::class.java as type, they don't care about the body and
        // just want the status code.  So return an empty object.
        if (typeOfT == Unit::class.java) return Gson().fromJson("{}", typeOfT)

        // The user is requesting a specific type of object, so check to see if the
        // json includes a wrapping element, such as "data". If so, set the parsing
        // root to that element and parse.  Otherwise, just parse from the current location.
        val element: JsonElement? = json!!.asJsonObject.get(elementName)
        val root = element ?: json
        return Gson().fromJson(root, typeOfT)
    }
}

