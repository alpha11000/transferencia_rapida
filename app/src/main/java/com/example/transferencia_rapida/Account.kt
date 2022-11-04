package com.example.transferencia_rapida

import com.example.transferencia_rapida.utils.NumberFormatUtil
import com.google.gson.annotations.SerializedName

open class Account(
    @SerializedName("nome") var name : String = "",
    @SerializedName("saldo_atual") var currentBalanceValue : Double = 0.0,
    @SerializedName("meus_contatos") var myContacts: MyContacts = MyContacts()
){
    val currentBalanceText : String
        get() { return NumberFormatUtil.getFormattedNumber(currentBalanceValue) }
}