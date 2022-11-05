package com.example.transferencia_rapida.utils

import java.text.DecimalFormat

object NumberFormatUtil {
    fun getFormattedNumber(number : Double, decimalFormat: DecimalFormat = DecimalFormat("#,##0.00")) : String{
        return decimalFormat.format(number)
    }

    fun getCurrencyValue(original : String) : Double {
        val cleared = original.replace("[^0-9]".toRegex(), "")

        return try {
            cleared.toDouble() / 100
        } catch (e: Exception) {
            0.0
        }
    }
}