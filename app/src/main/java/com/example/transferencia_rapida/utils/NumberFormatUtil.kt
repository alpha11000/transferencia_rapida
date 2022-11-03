package com.example.transferencia_rapida.utils

import java.text.DecimalFormat

object NumberFormatUtil {
    fun getFormattedNumber(number : Double, decimalFormat: DecimalFormat = DecimalFormat("#,##0.00")) : String{
        return decimalFormat.format(number)
    }
}