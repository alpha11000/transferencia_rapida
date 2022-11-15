package com.example.transferencia_rapida.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.transferencia_rapida.Contact
import com.example.transferencia_rapida.MyContacts
import com.example.transferencia_rapida.R
import com.example.transferencia_rapida.UserAccount
import com.example.transferencia_rapida.databinding.ContactAdapterBinding
import com.example.transferencia_rapida.extensions.getInitialsFromFirstAndLast
import com.example.transferencia_rapida.extensions.inflate

class ContactRecycleAdapter(
    private val contacts: Array<Contact>,
    private val onItemSelectedListener : ((Contact) -> Unit)? = null
) : RecyclerView.Adapter<ContactRecycleAdapter.ContactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflatedView = parent.inflate(R.layout.contact_adapter, false)
        contacts.sortBy { c1 -> c1.complete_name }
        return ContactHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = UserAccount.myContacts.list[position]
        holder.bindContact(contact, onItemSelectedListener)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    class ContactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindContact(contact : Contact, onItemSelectedListener : ((Contact) -> Unit)? = null){
            val binding = ContactAdapterBinding.bind(itemView)
            binding.contactName.text = contact.complete_name
            binding.contactInitials.text = contact.complete_name.getInitialsFromFirstAndLast()

            itemView.setOnClickListener {
                onItemSelectedListener?.invoke(contact)
            }
        }
    }
}