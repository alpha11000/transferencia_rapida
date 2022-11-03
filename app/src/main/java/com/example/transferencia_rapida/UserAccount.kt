package com.example.transferencia_rapida

import com.example.transferencia_rapida.utils.NumberFormatUtil

object UserAccount {
    val name : String
    var currentBalanceValue : Double

    val currentBalanceText : String
        get() {
            return NumberFormatUtil.getFormattedNumber(currentBalanceValue)
        }

    init {
        println("INICIALIZANDO ACOUNT")
        name = "sla"
        currentBalanceValue = 80000.05
    }
}