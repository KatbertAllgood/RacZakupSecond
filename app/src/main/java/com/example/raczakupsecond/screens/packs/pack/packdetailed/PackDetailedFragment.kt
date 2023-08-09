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
import androidx.navigation.fragment.findNavController
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

        viewModel.setPieChart(binding.pieChart)

        binding.packDetailedFragmentButtonOfferDelivery.setOnClickListener {
            findNavController().navigate(R.id.action_packDetailedFragment_to_kartFragment)
        }

        binding.packDetailedFragmentButtonAddEnergy.setOnClickListener {

            viewModel.addProduct(
                viewModel.getHealthySetId().toString(),
                "energy",
                requireContext()
            )
        }

        binding.packDetailedFragmentButtonAddPower.setOnClickListener {

            viewModel.addProduct(
                viewModel.getHealthySetId().toString(),
                "power",
                requireContext()
            )
        }

        binding.packDetailedFragmentButtonAddOil.setOnClickListener {

            viewModel.addProduct(
                viewModel.getHealthySetId().toString(),
                "oil",
                requireContext()
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
            ),
            requireContext()
        )

        viewModel.getAllProteins().observe(viewLifecycleOwner) {
            binding.packDetailedFragmentTvProteins.text =
                getString(R.string.proteins_value,
            it.toString())
        }

        viewModel.getAllFats().observe(viewLifecycleOwner) {
            binding.packDetailedFragmentTvFats.text = getString(R.string.fats_value,
            it.toString())
        }

        viewModel.getAllCarbs().observe(viewLifecycleOwner) {
            binding.packDetailedFragmentTvCarbohydrates.text = getString(R.string.carbs_value,
            it.toString())

        }

        viewModel.getAllPrice().observe(viewLifecycleOwner) {

            binding.packDetailedFragmentTvAllPrice.text = getString(R.string.price_two_dots,
                it.toString())

        }

        viewModel.getEnergyProductsLiveData().observe(viewLifecycleOwner) {

            energyProductsAdapter = PackDetailedFragmentProductsAdapter(it)

            energyProductsAdapter.onRefreshClick = { item ->
                Log.d(TAG, "REFRESH CLICKED: $item")

                viewModel.refreshProduct(
                    viewModel.getHealthySetId().toString(),
                    item.id.toString(),
                    "energy",
                    requireContext()
                )
//                energyProductsAdapter.notifyDataSetChanged() TODO()
            }

            energyProductsAdapter.onAmountChanged = { productId, amount, oldAmount ->
                viewModel.changeAmountOfProduct(
                    viewModel.getHealthySetId().toString(),
                    productId,
                    amount.toInt(),
                    oldAmount,
                    requireContext()
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
                    "power",
                    requireContext()
                )
//                energyProductsAdapter.notifyDataSetChanged() TODO()
            }

            powerProductsAdapter.onAmountChanged = { productId, amount, oldAmount ->
                viewModel.changeAmountOfProduct(
                    viewModel.getHealthySetId().toString(),
                    productId,
                    amount.toInt(),
                    oldAmount,
                    requireContext()
                )
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
                    "oil",
                    requireContext()
                )
//                energyProductsAdapter.notifyDataSetChanged() TODO()
            }

            oilProductsAdapter.onAmountChanged = { productId, amount, oldAmount ->
                viewModel.changeAmountOfProduct(
                    viewModel.getHealthySetId().toString(),
                    productId,
                    amount.toInt(),
                    oldAmount,
                    requireContext()
                )
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