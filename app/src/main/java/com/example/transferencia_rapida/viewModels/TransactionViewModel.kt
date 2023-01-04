package com.example.transferencia_rapida.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.transferencia_rapida.Contact

class TransactionViewModel : ViewModel() {
    val contact = MutableLiveData<Contact>()

    var transactionValue : Int? = null
    var scheduleTransfer : Boolean? = null
    var scheduleDate : Long? = null
}