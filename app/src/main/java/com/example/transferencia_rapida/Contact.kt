package com.example.transferencia_rapida

import com.google.gson.annotations.SerializedName

data class Contact(
    val id : Int = 0,
    @SerializedName("nome_completo") val complete_name : String = ""
)
