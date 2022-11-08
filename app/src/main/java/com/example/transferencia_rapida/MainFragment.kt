package com.example.transferencia_rapida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.transferencia_rapida.databinding.FragmentMainBinding
import com.example.transferencia_rapida.utils.DateUtil
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMainBinding.inflate(inflater, container, false)

        var transactionValue = 0.0

        binding.currentBalanceTv.text = getString(R.string.balance, UserAccount.currentBalanceText)
        UserAccount.addOnBalanceChangeCallback { _ , valueString ->
            binding.currentBalanceTv.text = getString(R.string.balance, valueString)
        }

        val errorMessages = arrayOf(binding.textFieldErrorTv, binding.valueErrorMessageTv)
        val moneyTextWatcher = MoneyTextWatcher(binding.transferValueEditText, errorMessages)

        binding.consultationDate.text = getString(
            R.string.consultation_date,
            DateUtil.getFormattedDate(DateUtil.getCurrentDate())
        )

        binding.transferValueEditText.setOnClickListener{binding.transferValueEditText.setSelection(binding.transferValueEditText.length())}
        binding.transferValueEditText.addTextChangedListener(moneyTextWatcher)
        binding.transferValueEditText.addTextChangedListener{
            transactionValue = moneyTextWatcher.transactionValue
        }

        binding.selectDate.setOnClickListener{
            showDatePickerDialog({
                binding.selectDate.setText(DateUtil.getFormattedDate(it))
            })
        }

        binding.confirmButton.setOnClickListener {
            try {
                UserAccount.withdrawValue(transactionValue)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }

        binding.selectContact.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_contactListFragment)
        }

        return binding.root
    }

    private fun showDatePickerDialog(callback : (Long) -> Unit, setMinDate : Boolean = true){
        val builder = MaterialDatePicker.Builder.datePicker()

        if(setMinDate){
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, 1)

            builder.setCalendarConstraints(
                CalendarConstraints.Builder().setValidator(DateValidatorPointForward.from(calendar.timeInMillis)).build()
            )
        }

        val datePicker = builder.build()
        datePicker.addOnPositiveButtonClickListener { callback.invoke(it) }
        datePicker.showNow(parentFragmentManager, "DatePicker")
    }
}