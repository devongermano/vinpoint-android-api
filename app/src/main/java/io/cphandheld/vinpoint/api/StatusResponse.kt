package io.cphandheld.vinpoint.api

import com.android.volley.toolbox.HttpResponse
import com.google.gson.internal.Primitives

/**
 * Created by christian on 11/2/17.
 */

fun <T> getStatus(response: HttpResponse?, statusResponseClass: Class<T>): T {
    return Primitives.wrap(statusResponseClass).cast(response!!.statusCode)
}