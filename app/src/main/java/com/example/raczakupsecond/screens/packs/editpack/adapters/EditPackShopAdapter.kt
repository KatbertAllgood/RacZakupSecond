package com.example.raczakupsecond.screens.packs.editpack.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.models.shop.ShopParamsDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.ItemShopBinding

class EditPackShopAdapter(
    private val context: Context,
    private val shopsList: List<ShopParamsDomain>
//    private val shopsList: List<Pair<Int, Int>>
) : RecyclerView.Adapter<EditPackShopAdapter.ShopHolder>() {

    var onItemClick : ((ShopParamsDomain) -> Unit)? = null
    var selectedItemPosition = -1

    inner class ShopHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemShopBinding.bind(item)

        val root = binding.root

        fun bind(shop: ShopParamsDomain) = with(binding) {

            root.setBackgroundResource(R.drawable.shape_rectangle_fafafa_rounded_10dp)

            itemShopImage.setImageResource(shop.image)
            itemShopTitle.text = context.getString(shop.title)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_shop,
                parent,
                false
            )
        return ShopHolder(view)
    }

    override fun getItemCount(): Int {
        return shopsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ShopHolder, position: Int) {
        holder.bind(shopsList[position])

        val shop = shopsList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(shop)
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