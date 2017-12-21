package io.cphandheld.vinpoint.api.request

import android.util.Log
import com.android.volley.NetworkResponse
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.cphandheld.vinpoint.api.GsonDataDeserializer
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.reactivex.SingleEmitter
import org.json.JSONObject

open class Request<T>(method: Int, url: String, jsonRequest: Any?,
                      private val responseType: Class<T>, subscriber: SingleEmitter<T>,
                      private val statusResponse: CPStatusResponse? = null)

    : JsonObjectRequest(method, url, null,
        Response.Listener { response ->

            val gson = GsonBuilder()
                    .registerTypeAdapter(responseType, GsonDataDeserializer<T>("data"))
                    .create()
            val result = gson.fromJson(response.toString(), responseType)
            subscriber.onSuccess(result)

        },
        Response.ErrorListener { error ->
            Log.e("Error", error.toString())
            subscriber.onError(error)
        }) {

    private val body: String = Gson().toJson(jsonRequest)

    //Update statusCode if CPStatusResponse object was passed in
    override fun parseNetworkResponse(response: NetworkResponse?): Response<JSONObject> {
        if (statusResponse != null) statusResponse.statusCode = response!!.statusCode
        return super.parseNetworkResponse(response)
    }

    //Update statusCode if CPStatusResponse object was passed in (separate path for errors)
    override fun parseNetworkError(volleyError: VolleyError?): VolleyError {
        if (statusResponse != null) statusResponse.statusCode = volleyError?.networkResponse?.statusCode ?: 0
        return super.parseNetworkError(volleyError)
    }

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
