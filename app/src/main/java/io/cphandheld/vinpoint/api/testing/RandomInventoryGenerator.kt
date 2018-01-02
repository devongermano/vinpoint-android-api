package io.cphandheld.vinpoint.api.testing

import io.cphandheld.vinpoint.api.models.CPInventory
import java.util.*

/**
 * Created by Devon on 1/2/18.
 */

class RandomInventoryGenerator {

    var inventory: CPInventory? = null

    private val colors = arrayOf("RED", "GREEN", "BLUE", "ORANGE", "VIOLET", "WHITE", "GREY", "BLACK")
    private val makes = arrayOf("PONTIAC", "FORD", "CHEVROLET")
    private val models = arrayOf("SHITSTER", "LEMONWEDGE", "DOILEY")


    init {

        val inventory = CPInventory()

        inventory.VIN = generateRandomAlphaNumeric(17)
        inventory.Stock = generateRandomAlphaNumeric(8)
        inventory.Color = generateRandomColor()
        inventory.Make = generateRandomMake()
        inventory.Model = generateRandomModel()

        this.inventory = inventory
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

}