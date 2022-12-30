package com.example.transferencia_rapida.utils

import android.graphics.Color
import android.provider.CalendarContract.Colors
import kotlin.random.Random

object ColorUtil {

    private val random = Random(0)

    fun generateRandomColor(
        inferiorLimit: Int = 0,
        superiorLimit: Int = 255
    ): Int {

        val r = random.nextInt(inferiorLimit, superiorLimit)
        val g = random.nextInt(inferiorLimit, superiorLimit)
        val b = random.nextInt(inferiorLimit, superiorLimit)

        return Color.rgb(r, g, b)
    }
}