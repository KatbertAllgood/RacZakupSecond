package com.example.raczakupsecond.screens.profile.address.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.ItemAddressBinding

class AddressFragmentAddressesAdapter(
    private val addressesList: List<AddressParamsDomain>,
    private val context: Context
) : RecyclerView.Adapter<AddressFragmentAddressesAdapter.AddressHolder>() {


    var onItemClick : ((AddressParamsDomain) -> Unit)? = null
    var onDeleteClick : ((Int) -> Unit)? = null
    var onEditClick : ((Int) -> Unit)? = null
    var selectedItemPosition = -1

    inner class AddressHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemAddressBinding.bind(item)

        val deleteButton = binding.addressItemDelete
        val updateButton = binding.addressItemUpdate
        val hiddenAddress = binding.addressItemHiddenAddress
        val baseAddress = binding.addressItemAddress
        val title = binding.addressItemTitle
        val hiddenTitle = binding.addressItemTitleHidden
        val closeHidden = binding.addressItemCloseHidden

        fun bind(address: AddressParamsDomain) = with(binding) {

            addressItemTitle.text = address.name
            addressItemTitleHidden.text = address.name
            addressItemAddress.text = context.resources.getString(R.string.address_in_item,
                address.city,
                address.street,
                address.house_number,
                address.entrance,
                address.floor,
                address.apartment
            )
            addressItemHiddenAddress.text = context.resources.getString(R.string.address_in_item,
                address.city,
                address.street,
                address.house_number,
                address.entrance,
                address.floor,
                address.apartment
            )

            closeHidden.setOnClickListener {

                listOf(
                    addressItemTitle,
                    addressItemAddress
                ).forEach {
                    it.visibility = View.VISIBLE
                }

                listOf(
                    addressItemCloseHidden,
                    addressItemDelete,
                    addressItemUpdate,
                    addressItemTitleHidden,
                    addressItemHiddenAddress,
                ).forEach {
                    it.visibility = View.GONE
                }
            }

            listOf(
                updateButton,
                hiddenAddress
            ).forEach {
                it.setOnClickListener {

                    onEditClick?.invoke(address.id)

                }
            }

            deleteButton.setOnClickListener {

                onDeleteClick?.invoke(address.id)

            }
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

            listOf(
                holder.deleteButton,
                holder.updateButton,
                holder.hiddenAddress,
                holder.closeHidden,
                holder.hiddenTitle
            ).forEach {
                it.visibility = View.VISIBLE
            }

            holder.baseAddress.visibility = View.INVISIBLE
            holder.title.visibility = View.INVISIBLE

        } else {
            listOf(
                holder.deleteButton,
                holder.updateButton,
                holder.hiddenAddress,
                holder.closeHidden,
                holder.hiddenTitle
            ).forEach {
                it.visibility = View.GONE
            }

            holder.baseAddress.visibility = View.VISIBLE
            holder.title.visibility = View.VISIBLE
        }
    }
}