package io.cphandheld.vinpoint.api.testing

import android.content.Context
import com.android.volley.Request
import io.cphandheld.vinpoint.api.models.CPInventory
import io.cphandheld.vinpoint.api.models.CPOrganization
import io.cphandheld.vinpoint.api.models.CPStatusResponse
import io.cphandheld.vinpoint.api.models.CPTestVIN
import io.cphandheld.vinpoint.api.utility.RequestFactory
import io.cphandheld.vinpoint.api.utility.VolleySingleton
import io.reactivex.Single
import java.util.*


class RandomInventoryGenerator(context: Context) {

    private val queue: VolleySingleton = VolleySingleton.getInstance(context)


    private val colors = arrayOf("RED", "GREEN", "BLUE", "ORANGE", "VIOLET", "WHITE", "GREY", "BLACK")
    private val makes = arrayOf("PONTIAC", "FORD", "CHEVROLET")
    private val models = arrayOf("SHITSTER", "LEMONWEDGE", "DOILEY")


    fun generateRandomVehicle(): Single<CPInventory> {

        val inventory = CPInventory()

        inventory.Stock = generateRandomAlphaNumeric(8)
        inventory.Color = generateRandomColor()
        inventory.Make = generateRandomMake()
        inventory.Model = generateRandomModel()

        return generateRandomVin().map({ x: CPTestVIN ->
            inventory.VIN = x.vin
            return@map inventory // WTF
        })

    }

    fun generateRandomColor(): String {
        val colorRnd = Random().nextInt(colors.count())
        return colors[colorRnd]
    }

    fun generateRandomMake(): String {
        val makeRnd = Random().nextInt(makes.count())
        return makes[makeRnd]
    }

    fun generateRandomModel(): String {
        val modelRnd = Random().nextInt(models.count())
        return models[modelRnd]
    }

    fun generateRandomAlphaNumeric(length: Int): String {

        val candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"

        val sb = StringBuilder()
        val random = Random()
        for (i in 0 until length) {
            sb.append(candidateChars[random.nextInt(candidateChars.length)])
        }
        return sb.toString()
    }

    fun generateRandomVin(): Single<CPTestVIN> {
        val url = "http://randomvin.com/getvin.php?type=real"
        return RequestFactory.getSingle(queue, Request.Method.GET, url, null, CPTestVIN::class.java)
    }

}