package io.cphandheld.vinpoint.api

import android.util.Log
import com.android.volley.VolleyError
import io.reactivex.Single
import java.util.concurrent.CountDownLatch
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by ericrudisill on 11/21/17.
 */

fun <T: Any> Single<T>.waitForTest(): T? {

    var result: T? = null
    val latch = CountDownLatch(1)

    this.subscribeBy(
            onSuccess = { result = it; latch.countDown() },
            onError = {

                val ve = it as VolleyError
                Log.e("TESTS", "Error in network call. Status Code " + ve.networkResponse?.statusCode, ve)
                latch.countDown()
            }
    )
    latch.await()
    return result
}


