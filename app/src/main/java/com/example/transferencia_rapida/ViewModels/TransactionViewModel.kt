package com.example.transferencia_rapida.ViewModels

import androidx.lifecycle.ViewModel
import com.example.transferencia_rapida.Contact

class TransactionViewModel : ViewModel() {
    var transactionValue : Int? = null
    var selectedContact : Contact? = null
    var scheduleTransfer : Boolean? = null
    var scheduleDate : Long? = null
}