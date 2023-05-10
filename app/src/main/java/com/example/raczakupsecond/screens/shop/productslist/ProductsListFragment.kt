package com.example.raczakupsecond.screens.shop.productslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.models.shop.ProductParamsDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentProductsListBinding
import com.example.raczakupsecond.screens.shop.productslist.adapters.ProductsListFragmentProductsAdapter

class ProductsListFragment : Fragment(R.layout.fragment_products_list) {
    lateinit var binding : FragmentProductsListBinding
    private val viewModel : ProductsListFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()

        viewModel.createHealthySet(
            HealthySetParamsRequestDomain(1, 1, "22", 2)
        )

        viewModel.getHealthySetParamsLiveData().observe(viewLifecycleOwner) {

            val allProducts: MutableList<ProductParamsDomain> = mutableListOf()

            for (i in it.data.healthySet.racEnergy) {
                allProducts.add(i)
            }
            for (i in it.data.healthySet.racPower) {
                allProducts.add(i)
            }
            for (i in it.data.healthySet.racOil) {
                allProducts.add(i)
            }
            for (i in it.data.healthySet.racOther) {
                allProducts.add(i)
            }

            val productsAdapter = ProductsListFragmentProductsAdapter(allProducts)

            binding.apply {
                productsListProductsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                productsListProductsRecyclerView.isNestedScrollingEnabled = false
                productsListProductsRecyclerView.adapter = productsAdapter
            }

        }
    }

}