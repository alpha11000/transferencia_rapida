package com.example.transferencia_rapida

import com.google.gson.annotations.SerializedName

data class MyContacts (
    @SerializedName("lista") val list : Array<Contact> = Array(1){Contact()}
)