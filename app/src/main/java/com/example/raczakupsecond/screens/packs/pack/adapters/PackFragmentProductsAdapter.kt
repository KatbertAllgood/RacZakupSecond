package com.example.raczakupsecond.screens.packs.pack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.shop.ProductParamsDomain
import com.example.domain.usecase.networkloader.DownloadAndSetImageUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import com.example.raczakupsecond.databinding.ItemProductHealthySetBinding

class PackFragmentProductsAdapter(
    private val productsList: List<ProductParamsDomain>
) : RecyclerView.Adapter<PackFragmentProductsAdapter.ProductHolder>() {

    private val networkLoaderRepository = App.getNetworkLoaderRepository()
    private val downloadAndSetImageUseCase = DownloadAndSetImageUseCase(networkLoaderRepository)

    var onItemClick : ((ProductParamsDomain) -> Unit)? = null

    inner class ProductHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemProductHealthySetBinding.bind(item)

        fun bind(product: ProductParamsDomain) = with(binding) {

            listOf(
                itemProductHealthySetCarbsProgress,
                itemProductHealthySetFatsProgress,
                itemProductHealthySetProteinsProgress
            ).forEach {
                var max = 0

                for (i in listOf(product.carbohydrates, product.fats, product.proteins)) {
                    if (i > max) {
                        max = i.toInt()
                    }
                }

                it.max = max
            }

            binding.itemProductHealthySetTitle.text = product.title

            downloadAndSetImageUseCase.invoke(product.image, binding.itemProductHealthySetImage)

            itemProductHealthySetCarbsProgress.progress = product.carbohydrates.toInt()
            itemProductHealthySetFatsProgress.progress = product.fats.toInt()
            itemProductHealthySetProteinsProgress.progress = product.proteins.toInt()

            itemProductHealthySetWeighValue.text = product.weigh + " г"

            itemProductHealthySetFatsValue.text = "${product.fats.toInt()} г"
            itemProductHealthySetCarbsValue.text = "${product.carbohydrates.toInt()} г"
            itemProductHealthySetProteinsValue.text = "${product.proteins.toInt()} г"

            itemProductHealthySetPrice.text = "${product.price.toString()} ₽"

            itemProductHealthySetKcalValue.text = "${product.energyValue.toInt()} ккал"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_product_healthy_set,
                parent,
                false
            )
        return ProductHolder(view)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productsList[position])

        val product = productsList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product)
            notifyDataSetChanged()
        }

    }

}