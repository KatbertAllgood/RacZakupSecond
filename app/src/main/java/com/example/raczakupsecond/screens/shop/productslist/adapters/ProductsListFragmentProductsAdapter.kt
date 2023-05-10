package com.example.raczakupsecond.screens.shop.productslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.shop.ProductParamsDomain
import com.example.domain.usecase.networkloader.DownloadAndSetImageUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import com.example.raczakupsecond.databinding.ItemProductBinding

class ProductsListFragmentProductsAdapter(
    private val productList: List<ProductParamsDomain>
) : RecyclerView.Adapter<ProductsListFragmentProductsAdapter.ProductHolder>() {

    private val networkLoaderRepository = App.getNetworkLoaderRepository()
    private val downloadAndSetImageUseCase = DownloadAndSetImageUseCase(networkLoaderRepository)

    var onItemClick : ((ProductParamsDomain) -> Unit)? = null

    inner class ProductHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemProductBinding.bind(item)

        fun bind(product: ProductParamsDomain) = with(binding) {

            itemProductTvTitle.text = product.title

            downloadAndSetImageUseCase.invoke(product.image, binding.itemProductImage)

            itemProductTvPrice.text = "${product.price.toString()} ₽"

            itemProductTvProteins.text = "${product.proteins.toInt()} г"
            itemProductTvFats.text = "${product.fats.toInt()} г"
            itemProductTvCarbs.text = "${product.carbohydrates.toInt()} г"

            itemProductTvWeigh.text = product.weigh + " г"

            itemProductTvKcalValue.text = product.energyValue.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_product,
                parent,
                false
            )
        return ProductHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productList[position])

        val product = productList[position]
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(product)
            notifyDataSetChanged()
        }
    }

}