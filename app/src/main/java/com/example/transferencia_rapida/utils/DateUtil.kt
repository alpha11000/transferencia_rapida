package com.example.transferencia_rapida.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun getFormattedDate(timeStamp : Long, format : String = "dd/MM/yyyy") : String?{
        return try{
            val sdf = SimpleDateFormat(format)
            val date = Date(timeStamp)
            sdf.timeZone = TimeZone.getTimeZone("Brazil/Brasilia")

            sdf.format(date)
        }catch (e : Exception){
            null
        }
    }
}