package io.cphandheld.vinpoint.api

import android.content.Context
import android.util.Log
import java.util.Calendar
import com.android.volley.Request.Method.GET
import com.android.volley.Request.Method.POST
import com.google.gson.Gson
import io.cphandheld.vinpoint.api.exceptions.InvalidCredentialsException
import io.cphandheld.vinpoint.api.models.CPCredentials
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single


class Scan(val context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)
    private val vinpointEndpoint: String? = VinpointAPI.Environment.APIEndpoint
    private val scannerEndpoint: String? = VinpointAPI.Environment.ScannerAPIEndpoint

    fun verifyVehicle(credentials: CPCredentials, vin: String, dealershipId: Int, orgId: Int, statusResponse: CPStatusResponse? = null): Single<CPInventory> {
        val url = "$vinpointEndpoint/v1/Scanner/VerifyVehicle/$vin/DealershipId/$dealershipId/OrganizationId/$orgId"
        return RequestFactory.getSecureSingle(credentials, queue, GET, url, null, CPInventory::class.java, statusResponse)
    }

    fun updateLatLong(credentials: CPCredentials, inventory: CPInventory, lat: Double, long: Double, statusResponse: CPStatusResponse? = null): Single<Unit> {
        val url = "$scannerEndpoint/queue/updateLatLng"
        val authUserId = credentials.getUserId() ?: throw InvalidCredentialsException("Could not get User ID from CPCredentials")

        val req: HashMap<String, List<HashMap<String, Any>>> = hashMapOf(
                "data" to listOf(
                        hashMapOf(
                                "AuthUserId" to authUserId.toString(),
                                "AppId" to 1 as Any,
                                "ScannerId" to (-1) as Any,
                                "Key" to inventory.VIN!!,
                                "Longitude" to long as Any,
                                "Latitude" to lat as Any,
                                "InventoryId" to inventory.InventoryId as Any,
                                "LocationId" to inventory.LocationId as Any,
                                "DealershipId" to inventory.DealershipId as Any,
                                "DateInUnixMillis" to Calendar.getInstance().timeInMillis as Any
                        )
                )
        )

        return RequestFactory.getSecureSingle(credentials, queue, POST, url, req, Unit::class.java, statusResponse)
    }
}