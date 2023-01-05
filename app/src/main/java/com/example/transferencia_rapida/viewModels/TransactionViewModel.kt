package com.example.transferencia_rapida.viewModels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.transferencia_rapida.Contact
import com.example.transferencia_rapida.UserAccount

class TransactionViewModel : ViewModel() {

    val validationMediatorLiveData = MediatorLiveData<Boolean>()

    val contact = MutableLiveData<Contact>()
    var transactionValue = MutableLiveData<Double>()
    var scheduleTransfer = MutableLiveData<Boolean>(false)
    var scheduleDate = MutableLiveData<Long>()

    init {
        validationMediatorLiveData.addSource(contact){validationMediatorLiveData.value = isValid()}
        validationMediatorLiveData.addSource(transactionValue){validationMediatorLiveData.value = isValid()}
        validationMediatorLiveData.addSource(scheduleTransfer){validationMediatorLiveData.value = isValid()}
        validationMediatorLiveData.addSource(scheduleDate){validationMediatorLiveData.value = isValid()}
    }

    private fun isValid() : Boolean{
        if(contact.value != null && transactionValue.value != null && scheduleTransfer.value != null){
            if(transactionValue.value!! <= UserAccount.currentBalanceValue && transactionValue.value!! > 0 &&
                (!scheduleTransfer.value!! || scheduleDate.value != null)){
                return true
            }
        }
        return false
    }
}