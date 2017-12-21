package io.cphandheld.vinpoint.api.singleton

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class VolleySingleton private constructor(context: Context) {

    private var currentRequestQueue: RequestQueue? = null
    private var baseURL: String? = "https://orion.cpht.io/unison-api"

    //Protected Request Queue allows functions, ie addToRequestQueue, to be safe from
    //null pointer errors from unwrapping the optional currentRequestQueue
    private val protectedRequestQueue: RequestQueue
        get() {
            if (currentRequestQueue == null) {
                currentRequestQueue = Volley.newRequestQueue(currentContext!!.applicationContext)
            }
            return currentRequestQueue!!
        }

    init {
        // Specify the application context
        currentContext = context
        // Get the request queue
        currentRequestQueue = protectedRequestQueue
    }

    fun <T> addToRequestQueue(request: Request<T>) {
        // Add the specified request to the request queue
        protectedRequestQueue.add(request)
    }

    fun buildURL(fragment: String): String {
        return baseURL + fragment
    }

    fun setURL(url: String) {
        baseURL = url
    }

    //Singleton Instance
    companion object {

        private var currentInstance: VolleySingleton? = null
        private var currentContext: Context? = null

        @Synchronized
        fun getInstance(context: Context): VolleySingleton {
            // If currentInstance is null then initialize new instance of self
            if (currentInstance == null) {
                currentInstance = VolleySingleton(context)
            }
            // Return VolleySingleton current instance
            return currentInstance!!
        }
    }
}
