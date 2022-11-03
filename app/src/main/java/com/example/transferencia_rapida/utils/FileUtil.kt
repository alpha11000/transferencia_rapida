package com.example.transferencia_rapida.utils

import android.content.Context
import java.io.IOException

object FileUtil{
    fun readFromAsset(context : Context, path : String) : String ? {
        val content : String

        try {
            content = context.assets.open(path).bufferedReader().use { it.readText() }
        }catch (e : IOException){
            e.printStackTrace()
            return null
        }
        return content
    }
}