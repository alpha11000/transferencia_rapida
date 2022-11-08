package com.example.transferencia_rapida.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun String.getInitialsFromFirstAndLast(onlyFirstAndLast : Boolean = true) : String{
    val initials : StringBuilder = StringBuilder()
    val words = this.split(" ")

    if (words.isNotEmpty()){
        val first = words.first()
        val last = words.last()

        initials.append(if(first.isNotEmpty()) first.first() else "")

        if(words.size > 1)
            initials.append(if(last.isNotEmpty()) last.first() else "")
    }

    return initials.toString()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
