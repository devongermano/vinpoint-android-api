package io.cphandheld.vinpoint.api.utility

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import io.cphandheld.vinpoint.api.VinpointAPI


class VolleySingleton private constructor(context: Context) {

    private var currentRequestQueue: RequestQueue? = null
    private var baseURL: String? = VinpointAPI.Environment.APIEndpoint
    //private var baseURL: String? = "https://orion.cpht.io/unison-api"

    /* Protected Request Queue allows functions, ie addToRequestQueue, to be safe from
    null pointer errors from unwrapping the optional currentRequestQueue */
    private val protectedRequestQueue: RequestQueue
        get() {
            if (currentRequestQueue == null) {
                currentRequestQueue = Volley.newRequestQueue(currentContext!!.applicationContext)
            }
            return currentRequestQueue!!
        }

    init {
        currentContext = context  //Specify the application context
        currentRequestQueue = protectedRequestQueue  //Get the request queue
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        protectedRequestQueue.add(request)  //Add the specified request to the request queue
    }

    companion object {

        private var currentInstance: VolleySingleton? = null
        private var currentContext: Context? = null

        @Synchronized
        fun getInstance(context: Context): VolleySingleton {
            if (currentInstance == null) {  // If currentInstance is null then initialize new instance of self
                currentInstance = VolleySingleton(context)
            }
            return currentInstance!!  //Return VolleySingleton current instance
        }
    }
}
