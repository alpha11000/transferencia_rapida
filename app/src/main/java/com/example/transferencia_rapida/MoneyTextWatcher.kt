package com.example.transferencia_rapida

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.transferencia_rapida.utils.NumberFormatUtil

class MoneyTextWatcher(
    private val editText: EditText,
    private val onErrorMessages : TextView? = null) : TextWatcher {

    private var transactionV = 0.0

    val transactionValue : Double
        get() {return transactionV}

    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(charSequence : CharSequence?, p1: Int, p2: Int, p3: Int) {
        val text = charSequence.toString()

        if (text.isEmpty()) return

        editText.removeTextChangedListener(this)
        transactionV = NumberFormatUtil.getCurrencyValue(text)
        editText.setText(NumberFormatUtil.getFormattedNumber(transactionV))
        editText.setSelection(editText.length())
        editText.addTextChangedListener(this)
    }

    override fun afterTextChanged(editable: Editable?) {
        val visibility = if (transactionV < UserAccount.currentBalanceValue) View.GONE else View.VISIBLE

        if (onErrorMessages != null) {
            onErrorMessages.visibility = visibility
        }
    }
}