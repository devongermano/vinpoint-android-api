package io.cphandheld.vinpoint.api

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


/**
 * Created by gencarnacion on 12/5/17.
 */

    val LOG_TAG = "Util"

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun hasActiveInternetConnection(context: Context): Boolean {
        if (isNetworkAvailable(context)) {
            try {
                val urlc = URL("http://www.google.com").openConnection() as HttpURLConnection
                urlc.setRequestProperty("User-Agent", "Test")
                urlc.setRequestProperty("Connection", "close")
                urlc.connectTimeout = 1500
                urlc.connect()
                return (urlc.responseCode == 200)
            } catch (e: IOException) {

                Log.e(LOG_TAG, "Error checking internet connection", e)
            }

        } else {
            Log.d(LOG_TAG, "No network available!")
        }
        return false
    }

    val YEAR_CHARS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T", "V", "W", "X", "Y", "1", "2", "3", "4", "5", "6", "7", "8", "9")

    fun getYearByVIN(vin: String): Int {
        var year: Int

        val tenthDigit = vin.substring(9, 10)

        val yearVal = Arrays.asList(*YEAR_CHARS).indexOf(tenthDigit)
        year = 1980 + yearVal

        var invalidYear = false
        while (!invalidYear) {
            val yearToCheck = Calendar.getInstance().get(Calendar.YEAR) + 1

            if (year + 30 <= yearToCheck) {
                year += 30
            } else {
                invalidYear = true
            }
        }

        return year
    }