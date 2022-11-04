package com.example.transferencia_rapida

import com.example.transferencia_rapida.utils.NumberFormatUtil
import com.google.gson.annotations.SerializedName

open class Account(
    @SerializedName("nome") var name : String = "",
    @SerializedName("saldo_atual") protected var currentBalanceV : Double = 0.0,
    @SerializedName("meus_contatos") protected var myContactsL: MyContacts = MyContacts()
){
    val currentBalanceValue : Double get() {return currentBalanceV}
    val myContacts : MyContacts get() {return myContactsL}

    val currentBalanceText : String
        get() { return NumberFormatUtil.getFormattedNumber(currentBalanceV) }

    val onBalanceChangeCallbacks : ArrayList<(Double, String) -> Unit> = ArrayList()

    fun addOnBalanceChangeCallback(callback : (Double, String) -> Unit){
        UserAccount.onBalanceChangeCallbacks.add(callback)
    }

    fun withdrawValue(value : Double){
        if(value <= currentBalanceV)
            currentBalanceV -= value
        else
            throw java.lang.Exception("withdrawal value grater than current balance.")

        notifyBalanceChange()
    }

    protected fun notifyBalanceChange(){
        for(callback in onBalanceChangeCallbacks){
            callback.invoke(currentBalanceV, currentBalanceText)
        }
    }
}