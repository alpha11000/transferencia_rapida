package com.example.transferencia_rapida

import android.content.Context
import com.example.transferencia_rapida.utils.JsonUtil

object UserAccount: Account(){

    fun initialize(context: Context){
        val account = JsonUtil.convertJsonAssetToObject(context, "data.json", Account::class.java)
        this.name = account.name
        this.currentBalanceV = account.currentBalanceValue
        this.myContactsL = account.myContacts

        notifyBalanceChange()
    }
}