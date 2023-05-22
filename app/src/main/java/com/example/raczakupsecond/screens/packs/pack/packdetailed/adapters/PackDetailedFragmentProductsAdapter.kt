package com.example.raczakupsecond.screens.packs.pack.packdetailed.adapters

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.packs.HealthySetParamsRefreshProductResponseDomain
import com.example.domain.models.shop.ProductParamsDomain
import com.example.domain.usecase.networkloader.DownloadAndSetImageUseCase
import com.example.domain.usecase.packs.RefreshProductInHealthySetUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import com.example.raczakupsecond.databinding.ItemProductHealthySetDetailedBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PackDetailedFragmentProductsAdapter(
    private val productsList: List<ProductParamsDomain>
) : RecyclerView.Adapter<PackDetailedFragmentProductsAdapter.ProductHolder>() {

    private val TAG = PackDetailedFragmentProductsAdapter::class.simpleName

    var onItemClick : ((ProductParamsDomain) -> Unit)? = null
    var onRefreshClick : ((ProductParamsDomain) -> Unit)? = null

    private lateinit var view : View

    inner class ProductHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemProductHealthySetDetailedBinding.bind(item)

        private val networkLoaderRepository = App.getNetworkLoaderRepository()
        private val downloadAndSetImageUseCase = DownloadAndSetImageUseCase(networkLoaderRepository)

        fun initialize(product: ProductParamsDomain)  = with(binding){

            listOf(
                itemProductHealthySetDetailedCarbsProgress,
                itemProductHealthySetDetailedFatsProgress,
                itemProductHealthySetDetailedProteinsProgress
            ).forEach {
                var max = 0

                for (i in listOf(product.carbohydrates, product.fats, product.proteins)) {
                    if (i > max) {
                        max = i.toInt()
                    }
                }

                it.max = max
            }

            itemProductHealthySetDetailedTvTitle.text = product.title

            downloadAndSetImageUseCase.invoke(product.image, binding.itemProductHealthySetDetailedImage)

            itemProductHealthySetDetailedCarbsProgress.progress = product.carbohydrates.toInt()
            itemProductHealthySetDetailedFatsProgress.progress = product.fats.toInt()
            itemProductHealthySetDetailedProteinsProgress.progress = product.proteins.toInt()

            itemProductHealthySetDetailedTvWeigh.text = product.weigh + " г"

            itemProductHealthySetDetailedFatsValue.text = "${product.fats.toInt()} г"
            itemProductHealthySetDetailedCarbsValue.text = "${product.carbohydrates.toInt()} г"
            itemProductHealthySetDetailedProteinsValue.text = "${product.proteins.toInt()} г"

            itemProductHealthySetDetailedTvPrice.text = "${product.price} ₽"

            itemProductHealthySetDetailedKcalValue.text = "${product.energyValue.toInt()} ккал"

            itemProductHealthySetDetailedTvAmount.text = product.amount.toString()

            val amountedPrice = product.price * product.amount
            itemProductHealthySetDetailedTvAmountedPrice.text = "${amountedPrice} ₽"

        }

        fun bind(product: ProductParamsDomain) = with(binding) {

            initialize(product)

            itemProductHealthySetDetailedReroll.setOnClickListener {
                onRefreshClick?.invoke(product)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_product_healthy_set_detailed,
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