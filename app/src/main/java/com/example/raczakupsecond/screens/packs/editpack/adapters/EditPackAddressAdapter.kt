package com.example.raczakupsecond.screens.packs.editpack.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.ItemAddressBinding

class EditPackAddressAdapter(
    private val context: Context,
    private val addressesList: List<AddressParamsDomain>
) : RecyclerView.Adapter<EditPackAddressAdapter.AddressHolder>() {

    var onItemClick : ((AddressParamsDomain) -> Unit)? = null
    var selectedItemPosition = -1

    inner class AddressHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemAddressBinding.bind(item)

        val root = binding.root

        fun bind(address: AddressParamsDomain) = with(binding) {

            root.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_10dp)

            addressItemTitle.text = address.name
            addressItemAddress.text = context.getString(R.string.address_in_item,
                address.city,
                address.street,
                address.house_number,
                address.entrance,
                address.floor,
                address.apartment
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_address,
                parent,
                false
            )
        return AddressHolder(view)
    }

    override fun getItemCount(): Int {
        return addressesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: AddressHolder, position: Int) {
        holder.bind(addressesList[position])

        val address = addressesList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(address)
            selectedItemPosition = position
            notifyDataSetChanged()
        }

        if(selectedItemPosition == position) {
            holder.root.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_10dp_selected)
        } else {
            holder.root.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_10dp)
        }

    }

}