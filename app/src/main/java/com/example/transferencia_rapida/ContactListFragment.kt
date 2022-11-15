package com.example.transferencia_rapida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.transferencia_rapida.adapters.ContactRecycleAdapter
import com.example.transferencia_rapida.databinding.FragmentContactListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ContactList : Fragment() {
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
        // Inflate the layout for this fragment
        val binding = FragmentContactListBinding.inflate(inflater, container, false)

        val adapter = ContactRecycleAdapter(UserAccount.myContacts.list) {
            Toast.makeText(this.context, it.complete_name, Toast.LENGTH_SHORT).show()
        }

        binding.contactsRv.layoutManager = LinearLayoutManager(this.context)
        binding.contactsRv.adapter = adapter

        return binding.root
    }
}