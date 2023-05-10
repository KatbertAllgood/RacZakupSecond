package com.example.raczakupsecond.screens.shop.productcard

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.domain.models.shop.ProductParamsDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentProductCardBinding

class ProductCardFragment : Fragment(R.layout.fragment_product_card) {
    lateinit var binding : FragmentProductCardBinding
    private val viewModel : ProductCardFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.setProductParamsLiveData(ProductParamsDomain(
//            requireArguments().getString("title").toString(),
//            requireArguments().getDouble("price"),
//            requireArguments().getInt("categoryId"),
//            requireArguments().getString("manufacturer").toString(),
//            requireArguments().getString("brand").toString(),
//            requireArguments().getString("country").toString(),
//            requireArguments().getString("fraction").toString(),
//            requireArguments().getString("weigh").toString(),
//            requireArguments().getDouble("pricePerKgMl"),
//            requireArguments().getString("composition").toString(),
//            requireArguments().getDouble("energyValue"),
//            requireArguments().getDouble("fats"),
//            requireArguments().getDouble("proteins"),
//            requireArguments().getDouble("carbohydrates"),
//            requireArguments().getString("storageConditions").toString(),
//            requireArguments().getInt("storageTemperatureMin"),
//            requireArguments().getInt("storageTemperatureMax"),
//            requireArguments().getInt("storageLife"),
//            requireArguments().getString("description").toString(),
//            requireArguments().getString("image").toString(),
//            requireArguments().getDouble("racCoefficientBenefit"),
//            requireArguments().getString("racCategoryBenefit").toString(),
//            requireArguments().getInt("shopId"),
//
//        ))

    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        viewModel.getProductParamsLiveData().observe(viewLifecycleOwner) {

            binding.apply {

                viewModel.downloadAndSetImage(it.image, productFragmentImage)
                productFragmentTvComposition.text = it.composition
                productFragmentTvTitle.text = it.title
                productFragmentTvWeigh.text = it.weigh
                productFragmentTvPrice.text = "${it.price}₽"
                productFragmentTvDescription.text = it.description

            }

        }
    }

}