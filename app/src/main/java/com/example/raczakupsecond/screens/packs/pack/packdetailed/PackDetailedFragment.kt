package com.example.raczakupsecond.screens.packs.pack.packdetailed

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.models.shop.ProductParamsDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentPackDetailedBinding
import com.example.raczakupsecond.screens.packs.pack.defaultpack.adapters.PackFragmentProductsAdapter
import com.example.raczakupsecond.screens.packs.pack.packdetailed.adapters.PackDetailedFragmentProductsAdapter
import kotlinx.coroutines.NonDisposableHandle.parent

class PackDetailedFragment : Fragment(R.layout.fragment_pack_detailed) {
    lateinit var binding : FragmentPackDetailedBinding
    private val viewModel : PackDetailedFragmentVM by viewModels()

    private val TAG = PackDetailedFragment::class.simpleName

    private lateinit var energyProductsAdapter : PackDetailedFragmentProductsAdapter
    private lateinit var powerProductsAdapter : PackDetailedFragmentProductsAdapter
    private lateinit var oilProductsAdapter : PackDetailedFragmentProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPackDetailedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.packDetailedFragmentButtonAddEnergy.setOnClickListener {

            viewModel.addProduct(
                viewModel.getHealthySetId().toString(),
                "energy"
            )
        }

        binding.packDetailedFragmentButtonAddPower.setOnClickListener {

            viewModel.addProduct(
                viewModel.getHealthySetId().toString(),
                "power"
            )
        }

        binding.packDetailedFragmentButtonAddOil.setOnClickListener {

            viewModel.addProduct(
                viewModel.getHealthySetId().toString(),
                "oil"
            )
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.createHealthySet(
            HealthySetParamsRequestDomain(
            requireArguments().getString("familyId")!!.toInt(),
            requireArguments().getInt("days"),
            requireArguments().getString("budget").toString(),
            requireArguments().getString("addressId")!!.toInt()
        )
        )

        viewModel.getHealthySetParamsLiveData().observe(viewLifecycleOwner) {

            var allProteins = 0
            var allFats = 0
            var allCarbs = 0
            var allPrice = 0.0

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

            for (i in allProducts) {
                allProteins += i.proteins.toInt()
                allFats += i.fats.toInt()
                allCarbs += i.carbohydrates.toInt()
                allPrice += i.price
            }

            binding.apply {

                packDetailedFragmentTvProteins.text = getString(R.string.proteins_value,
                    allProteins.toString())
                packDetailedFragmentTvFats.text = getString(R.string.fats_value, allFats.toString())
                packDetailedFragmentTvCarbohydrates.text = getString(R.string.carbs_value,
                    allCarbs.toString())
                packDetailedFragmentTvAllPrice.text = getString(R.string.price_two_dots,
                    "$allPrice ₽"
                )

                viewModel.initPieChart(
                    pieChart,
                    allProteins.toFloat(),
                    allFats.toFloat(),
                    allCarbs.toFloat(),
                    resources.getColor(R.color.proteins),
                    resources.getColor(R.color.fats),
                    resources.getColor(R.color.carbohydrates)
                )
            }

        }

        viewModel.getEnergyProductsLiveData().observe(viewLifecycleOwner) {

            energyProductsAdapter = PackDetailedFragmentProductsAdapter(it)

            energyProductsAdapter.onRefreshClick = { item ->
                Log.d(TAG, "REFRESH CLICKED: $item")

                viewModel.refreshProduct(
                    viewModel.getHealthySetId().toString(),
                    item.id.toString(),
                    "energy"
                )
//                energyProductsAdapter.notifyDataSetChanged() TODO()
            }

            energyProductsAdapter.onAmountChanged = { productId, amount ->
                viewModel.changeAmountOfProduct(
                    viewModel.getHealthySetId().toString(),
                    productId,
                    amount.toInt()
                )
            }

            binding.apply {
                packDetailedFragmentRecyclerEnergy.layoutManager =
                    LinearLayoutManager(requireContext())
                packDetailedFragmentRecyclerEnergy.isNestedScrollingEnabled = false
                packDetailedFragmentRecyclerEnergy.adapter = energyProductsAdapter
            }
        }

        viewModel.getPowerProductsLiveData().observe(viewLifecycleOwner) {

            powerProductsAdapter = PackDetailedFragmentProductsAdapter(it)

            powerProductsAdapter.onRefreshClick = { item ->
                Log.d(TAG, "REFRESH CLICKED: $item")

                viewModel.refreshProduct(
                    viewModel.getHealthySetId().toString(),
                    item.id.toString(),
                    "power"
                )
//                energyProductsAdapter.notifyDataSetChanged() TODO()
            }

            binding.apply {
                packDetailedFragmentRecyclerPower.layoutManager =
                    LinearLayoutManager(requireContext())
                packDetailedFragmentRecyclerPower.isNestedScrollingEnabled = false
                packDetailedFragmentRecyclerPower.adapter = powerProductsAdapter
            }
        }

        viewModel.getOilProductsLiveData().observe(viewLifecycleOwner) {

            oilProductsAdapter = PackDetailedFragmentProductsAdapter(it)

            oilProductsAdapter.onRefreshClick = { item ->
                Log.d(TAG, "REFRESH CLICKED: $item")

                viewModel.refreshProduct(
                    viewModel.getHealthySetId().toString(),
                    item.id.toString(),
                    "oil"
                )
//                energyProductsAdapter.notifyDataSetChanged() TODO()
            }

            binding.apply {
                packDetailedFragmentRecyclerOil.layoutManager =
                    LinearLayoutManager(requireContext())
                packDetailedFragmentRecyclerOil.isNestedScrollingEnabled = false
                packDetailedFragmentRecyclerOil.adapter = oilProductsAdapter
            }
        }

    }

}