package io.cphandheld.vinpoint.api

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.HttpResponse
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.cphandheld.vinpoint.api.models.StatusResponse
import io.reactivex.SingleEmitter

open class Request<T>
(method: Int, url: String, jsonRequest: Any?, responseType: Class<T>, subscriber: SingleEmitter<T>)
    : JsonObjectRequest(method, url, null, Response.Listener { response ->
    //Check if expected response is json or just a status code
    if(responseType == StatusResponse().javaClass){
        val result = getStatus(response as HttpResponse, responseType as Class<T>)
        subscriber.onSuccess(result)
    }
    else{
        val gson = GsonBuilder()
                .registerTypeAdapter(responseType, GsonDataDeserializer<T>("data"))
                .create()
        val result = gson.fromJson(response.toString(), responseType)
        subscriber.onSuccess(result)
    }

}, Response.ErrorListener { error ->
    Log.e("Error", error.toString())
    subscriber.onError(error)
}) {

    private val body: String = Gson().toJson(jsonRequest)

    //Set Body Content Type
    override fun getBodyContentType(): String {
        return "application/json"
    }

    //Set Headers
    override fun getHeaders(): MutableMap<String, String> {
        val headers = HashMap<String, String>()
        headers.put("Content-Type", "application/json")
        return headers
    }

    //Set Body
    override fun getBody(): ByteArray {
        return body.toByteArray()
    }

}
