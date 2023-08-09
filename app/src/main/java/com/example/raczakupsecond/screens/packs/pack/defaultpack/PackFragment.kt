package com.example.raczakupsecond.screens.packs.pack.defaultpack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.models.shop.ProductParamsDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentPackBinding
import com.example.raczakupsecond.screens.packs.pack.defaultpack.adapters.PackFragmentProductsAdapter
import com.example.raczakupsecond.screens.packs.pack.packdetailed.adapters.PackDetailedFragmentProductsAdapter

class PackFragment : Fragment(R.layout.fragment_pack) {
    lateinit var binding : FragmentPackBinding
    private val viewModel : PackFragmentVM by viewModels()

    private val TAG = PackFragment::class.simpleName

    private lateinit var energyProductsAdapter : PackFragmentProductsAdapter
    private lateinit var powerProductsAdapter : PackFragmentProductsAdapter
    private lateinit var oilProductsAdapter : PackFragmentProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentPackBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setPieChart(binding.pieChart)

        binding.packFragmentButtonOfferDelivery.setOnClickListener {
            findNavController().navigate(R.id.action_packFragment_to_kartFragment)
        }

        binding.packFragmentButtonAddEnergy.setOnClickListener {
            viewModel.addProduct(
                viewModel.healthySetId,
                "energy",
                requireContext()
            )
        }

        binding.packFragmentButtonAddPower.setOnClickListener {

            viewModel.addProduct(
                viewModel.healthySetId,
                "power",
                requireContext()
            )
        }

        binding.packFragmentButtonAddOil.setOnClickListener {

            viewModel.addProduct(
                viewModel.healthySetId,
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

            binding.packFragmentTvProteins.text = getString(R.string.proteins_value,
                it.toString())
        }

        viewModel.getAllFats().observe(viewLifecycleOwner) {
            binding.packFragmentTvFats.text = getString(R.string.fats_value,
                it.toString())
        }

        viewModel.getAllCarbs().observe(viewLifecycleOwner) {
            binding.packFragmentTvCarbohydrates.text = getString(R.string.carbs_value,
                it.toString())

        }

        viewModel.getAllPrice().observe(viewLifecycleOwner) {

            binding.packFragmentTvAllPrice.text = getString(R.string.price_two_dots,
                it.toString())

        }

        viewModel.getEnergyProductsLiveData().observe(viewLifecycleOwner) {

            energyProductsAdapter = PackFragmentProductsAdapter(it)

            energyProductsAdapter.onItemClick = { item ->
                findNavController().navigate(
                    R.id.action_packFragment_to_packDetailedFragment,
                    bundleOf(
                        "familyId" to requireArguments().getInt("familyId").toString(),
                        "days" to requireArguments().getInt("days"),
                        "budget" to requireArguments().getString("budget"),
                        "addressId" to requireArguments().getInt("addressId").toString()
                    )
                )
            }

            binding.apply {
                packFragmentRecyclerEnergy.layoutManager =
                    GridLayoutManager(requireContext(), 3)
                packFragmentRecyclerEnergy.isNestedScrollingEnabled = false
                packFragmentRecyclerEnergy.adapter = energyProductsAdapter
            }
        }

        viewModel.getPowerProductsLiveData().observe(viewLifecycleOwner) {

            powerProductsAdapter = PackFragmentProductsAdapter(it)

            powerProductsAdapter.onItemClick = { item ->
                findNavController().navigate(
                    R.id.action_packFragment_to_packDetailedFragment,
                    bundleOf(
                        "familyId" to requireArguments().getInt("familyId").toString(),
                        "days" to requireArguments().getInt("days"),
                        "budget" to requireArguments().getString("budget"),
                        "addressId" to requireArguments().getInt("addressId").toString()
                    )
                )
            }

            binding.apply {
                packFragmentRecyclerPower.layoutManager =
                    GridLayoutManager(requireContext(), 3)
                packFragmentRecyclerPower.isNestedScrollingEnabled = false
                packFragmentRecyclerPower.adapter = powerProductsAdapter
            }
        }

        viewModel.getOilProductsLiveData().observe(viewLifecycleOwner) {

            oilProductsAdapter = PackFragmentProductsAdapter(it)

            oilProductsAdapter.onItemClick = { item ->
                findNavController().navigate(
                    R.id.action_packFragment_to_packDetailedFragment,
                    bundleOf(
                        "familyId" to requireArguments().getInt("familyId").toString(),
                        "days" to requireArguments().getInt("days"),
                        "budget" to requireArguments().getString("budget"),
                        "addressId" to requireArguments().getInt("addressId").toString()
                    )
                )
            }

            binding.apply {
                packFragmentRecyclerOil.layoutManager =
                    GridLayoutManager(requireContext(), 3)
                packFragmentRecyclerOil.isNestedScrollingEnabled = false
                packFragmentRecyclerOil.adapter = oilProductsAdapter
            }
        }

//        viewModel.getHealthySetParamsLiveData().observe(viewLifecycleOwner) {
//
//            var allProteins = 0
//            var allFats = 0
//            var allCarbs = 0
//            var allPrice = 0.0
//
//            val allProducts: MutableList<ProductParamsDomain> = mutableListOf()
//
//
//            for (i in it.data.healthySet.racEnergy) {
//                allProducts.add(i)
//            }
//            for (i in it.data.healthySet.racPower) {
//                allProducts.add(i)
//            }
//            for (i in it.data.healthySet.racOil) {
//                allProducts.add(i)
//            }
//            for (i in it.data.healthySet.racOther) {
//                allProducts.add(i)
//            }
//
//            for (i in allProducts) {
//                allProteins += i.proteins.toInt()
//                allFats += i.fats.toInt()
//                allCarbs += i.carbohydrates.toInt()
//                allPrice += i.price
//            }
//
//            binding.apply {
//
//                packFragmentTvProteins.text = getString(R.string.proteins_value,
//                    allProteins.toString())
//                packFragmentTvFats.text = getString(R.string.fats_value, allFats.toString())
//                packFragmentTvCarbohydrates.text = getString(R.string.carbs_value,
//                    allCarbs.toString())
//                packFragmentTvAllPrice.text = getString(R.string.price_two_dots,
//                    "$allPrice ₽"
//                )
//
//            }
//
//            val energyProductsAdapter = PackFragmentProductsAdapter(it.data.healthySet.racEnergy)
//            val powerProductsAdapter = PackFragmentProductsAdapter(it.data.healthySet.racPower)
//            val oilProductsAdapter = PackFragmentProductsAdapter(it.data.healthySet.racOil)
//            val otherProductsAdapter = PackFragmentProductsAdapter(it.data.healthySet.racOther)
//
//            binding.apply {
//
//                listOf(
//                    packFragmentRecyclerEnergy,
//                    packFragmentRecyclerPower,
//                    packFragmentRecyclerOil,
//                    packFragmentRecyclerOther
//                ).forEach {
//                    it.layoutManager = GridLayoutManager(requireContext(), 3)
//                    it.isNestedScrollingEnabled = false
//                }
//
//                listOf(
//                    energyProductsAdapter,
//                    powerProductsAdapter,
//                    oilProductsAdapter,
//                    otherProductsAdapter,
//                ).forEach {
//                    it.onItemClick = { item ->
//                        findNavController().navigate(
//                            R.id.action_packFragment_to_packDetailedFragment,
//                            bundleOf(
//                                "familyId" to requireArguments().getInt("familyId").toString(),
//                                "days" to requireArguments().getInt("days"),
//                                "budget" to requireArguments().getString("budget"),
//                                "addressId" to requireArguments().getInt("addressId").toString()
//                            )
//                        )
//                    }
//                }
//
//
//                packFragmentRecyclerEnergy.adapter = energyProductsAdapter
//                packFragmentRecyclerPower.adapter = powerProductsAdapter
//                packFragmentRecyclerOil.adapter = oilProductsAdapter
//                packFragmentRecyclerOther.adapter = otherProductsAdapter
//
//            }
//        }

    }

}