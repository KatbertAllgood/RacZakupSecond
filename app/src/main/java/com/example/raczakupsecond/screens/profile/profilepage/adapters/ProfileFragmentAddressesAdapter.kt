package com.example.raczakupsecond.screens.profile.profilepage.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.ItemAddressBinding

class ProfileFragmentAddressesAdapter(
    private val addressesList: List<AddressParamsDomain>,
    private val context: Context
) : RecyclerView.Adapter<ProfileFragmentAddressesAdapter.AddressHolder>() {


    var onItemClick : ((AddressParamsDomain) -> Unit)? = null
    var onDeleteClick : ((Int) -> Unit)? = null
    var onEditClick : ((AddressParamsDomain) -> Unit)? = null
    var selectedItemPosition = -1

    inner class AddressHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemAddressBinding.bind(item)

        val root = binding.root
        val deleteButton = binding.addressItemDelete
        val updateButton = binding.addressItemUpdate
        val hiddenAddress = binding.addressItemHiddenAddress
        val baseAddress = binding.addressItemAddress
        val title = binding.addressItemTitle
        val hiddenTitle = binding.addressItemTitleHidden
        val closeHidden = binding.addressItemCloseHidden
        val confirmDelete = binding.addressItemConfirmDelete
        val cancelDelete = binding.addressItemCancelDelete

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(address: AddressParamsDomain) = with(binding) {

            addressItemTitle.text = address.name
            addressItemTitleHidden.text = address.name
            addressItemAddress.text = context.resources.getString(R.string.address_in_item,
                address.city,
                address.street,
                address.house,
                address.entrance,
                address.floor,
                address.flat
            )
            addressItemHiddenAddress.text = context.resources.getString(R.string.address_in_item,
                address.city,
                address.street,
                address.house,
                address.entrance,
                address.floor,
                address.flat
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

                    onEditClick?.invoke(address)

                }
            }

            addressItemCancelDelete.setOnClickListener {

                listOf(
                    deleteButton,
                    updateButton,
                    hiddenAddress,
                    closeHidden,
                    hiddenTitle
                ).forEach {
                    it.visibility = View.VISIBLE
                }

                root.background = context.getDrawable(R.drawable.shape_profile_background_for_data_holders)
                addressItemConfirmDelete.visibility = View.GONE
                addressItemCancelDelete.visibility = View.GONE

            }

            addressItemConfirmDelete.setOnClickListener {

                onDeleteClick?.invoke(address.id)
            }

            deleteButton.setOnClickListener {

                listOf(
                    deleteButton,
                    updateButton,
                    hiddenAddress,
                    closeHidden,
                    hiddenTitle
                ).forEach {
                    it.visibility = View.GONE
                }

                root.background = context.getDrawable(R.drawable.shape_red_on_red_rectangle_rounded_10dp)
                addressItemConfirmDelete.visibility = View.VISIBLE
                addressItemCancelDelete.visibility = View.VISIBLE


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

    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
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
                holder.hiddenTitle,
                holder.confirmDelete,
                holder.cancelDelete
            ).forEach {
                it.visibility = View.GONE
            }

            holder.root.background = context.getDrawable(R.drawable.shape_profile_background_for_data_holders)
            holder.baseAddress.visibility = View.VISIBLE
            holder.title.visibility = View.VISIBLE
        }
    }
}