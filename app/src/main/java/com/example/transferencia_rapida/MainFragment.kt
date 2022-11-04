package com.example.transferencia_rapida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
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
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        var transactionValue = 0.0

        binding.transferValueEditText.doOnTextChanged { text, _, _, _ ->
            if(!text?.isEmpty()!!){
                transactionValue = text.toString().toDouble()

                if(transactionValue > UserAccount.currentBalanceValue) {
                    binding.textFieldErrorTv.visibility = View.VISIBLE
                    binding.valueErrorMessageTv.visibility = View.VISIBLE
                }else {
                    binding.textFieldErrorTv.visibility = View.GONE
                    binding.valueErrorMessageTv.visibility = View.GONE
                }
            }
        }

        binding.currentBalanceTv.text = getString(R.string.balance, UserAccount.currentBalanceText)

        UserAccount.addOnBalanceChangeCallback { _ , valueString ->
            run {
                binding.currentBalanceTv.text = getString(R.string.balance, valueString)
            }
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

        return binding.root
    }

    private fun showDatePickerDialog(callback : (Long) -> Unit, setMinDate : Boolean = true){
        val builder = MaterialDatePicker.Builder.datePicker()

        if(setMinDate){
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, 0)

            builder.setCalendarConstraints(
                CalendarConstraints.Builder().setValidator(DateValidatorPointForward.from(calendar.timeInMillis)).build()
            )
        }

        val datePicker = builder.build()
        datePicker.addOnPositiveButtonClickListener { callback.invoke(it) }
        datePicker.showNow(parentFragmentManager, "DatePicker")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}