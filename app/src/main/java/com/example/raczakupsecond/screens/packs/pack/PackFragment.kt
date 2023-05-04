package com.example.raczakupsecond.screens.packs.pack

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
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
import com.example.raczakupsecond.screens.packs.pack.adapters.PackFragmentProductsAdapter
import com.example.raczakupsecond.screens.packs.pack.adapters.PackFragmentProductsDetailedAdapter
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class PackFragment : Fragment(R.layout.fragment_pack) {
    lateinit var binding : FragmentPackBinding
    private val viewModel : PackFragmentVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentPackBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            // on below line we are setting user percent value,
            // setting description as enabled and offset for pie chart
            pieChart.setUsePercentValues(true)
            pieChart.description.isEnabled = false
            pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

            // это скорость кручения круга после прекращения взаимодействия с экраном
            pieChart.dragDecelerationFrictionCoef = 0.95f

            // on below line we are setting hole
            // and hole color for pie chart
            pieChart.isDrawHoleEnabled = true
            pieChart.setHoleColor(Color.WHITE)

            // on below line we are setting circle color and alpha
            pieChart.setTransparentCircleColor(Color.WHITE)
            pieChart.setTransparentCircleAlpha(110)

            // on  below line we are setting hole radius
            pieChart.holeRadius = 58f
            pieChart.transparentCircleRadius = 61f

            // on below line we are setting center text
            pieChart.setDrawCenterText(true)

            // on below line we are setting
            // rotation for our pie chart
            pieChart.rotationAngle = 0f

            // enable rotation of the pieChart by touch
            pieChart.isRotationEnabled = true
            pieChart.isHighlightPerTapEnabled = true

            // on below line we are setting animation for our pie chart
//            pieChart.animateY(1400, Easing.EaseInOutQuad)

            // on below line we are disabling our legend for pie chart
            pieChart.legend.isEnabled = true
            pieChart.setEntryLabelColor(Color.WHITE)
            pieChart.setEntryLabelTextSize(12f)

            // on below line we are creating array list and
            // adding data to it to display in pie chart
            val entries: ArrayList<PieEntry> = ArrayList()
            entries.add(PieEntry(70f))
            entries.add(PieEntry(20f))
            entries.add(PieEntry(10f))

            // on below line we are setting pie data set
            val dataSet = PieDataSet(entries, "Mobile OS")

            // on below line we are setting icons.
            dataSet.setDrawIcons(false)

            // on below line we are setting slice for pie
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0f, 40f)
            dataSet.selectionShift = 5f

            // add a lot of colors to list
            val colors: ArrayList<Int> = ArrayList()
            colors.add(resources.getColor(R.color.fats))
            colors.add(resources.getColor(R.color.carbohydrates))
            colors.add(resources.getColor(R.color.proteins))

            // on below line we are setting colors.
            dataSet.colors = colors

            // on below line we are setting pie data set
            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(15f)
            data.setValueTypeface(Typeface.DEFAULT_BOLD)
            data.setValueTextColor(Color.WHITE)
            pieChart.data = data

            // undo all highlights
            pieChart.highlightValues(null)

            // loading chart
            pieChart.invalidate()

        }

    }

    override fun onResume() {
        super.onResume()

        viewModel.createHealthySet(HealthySetParamsRequestDomain(
            requireArguments().getString("familyId")!!.toInt(),
            requireArguments().getInt("days"),
            requireArguments().getString("budget").toString(),
            requireArguments().getString("addressId")!!.toInt()
        ))

        viewModel.getHealthySetParamsLiveData().observe(viewLifecycleOwner) {

            var allProteins = 0
            var allFats = 0
            var allCarbs = 0

            val allProducts: MutableList<ProductParamsDomain> = mutableListOf()

            for (i in it.data[0].racEnergy) {
                allProducts.add(i)
            }
            for (i in it.data[1].racPower) {
                allProducts.add(i)
            }
            for (i in it.data[2].racOil) {
                allProducts.add(i)
            }
            for (i in it.data[3].racOther) {
                allProducts.add(i)
            }

            for (i in allProducts) {
                allProteins += i.proteins.toInt()
                allFats += i.fats.toInt()
                allCarbs += i.carbohydrates.toInt()
            }

            binding.apply {

                packFragmentTvProteins.text = getString(R.string.proteins_value,
                    allProteins.toString())
                packFragmentTvFats.text = getString(R.string.fats_value, allFats.toString())
                packFragmentTvCarbohydrates.text = getString(R.string.carbs_value,
                    allCarbs.toString())

            }

            val energyProductsAdapter = PackFragmentProductsAdapter(it.data[0].racEnergy)
//            val energyProductsDetailedAdapter = PackFragmentProductsDetailedAdapter(it.data[0].racEnergy)
            val powerProductsAdapter = PackFragmentProductsAdapter(it.data[1].racPower)
//            val powerProductsDetailedAdapter = PackFragmentProductsDetailedAdapter(it.data[1].racPower)
            val oilProductsAdapter = PackFragmentProductsAdapter(it.data[2].racOil)
//            val oilProductsDetailedAdapter = PackFragmentProductsDetailedAdapter(it.data[2].racOil)
            val otherProductsAdapter = PackFragmentProductsAdapter(it.data[3].racOther)
//            val otherProductsDetailedAdapter = PackFragmentProductsDetailedAdapter(it.data[3].racOther)

            binding.apply {

                listOf(
                    packFragmentRecyclerEnergy,
                    packFragmentRecyclerPower,
                    packFragmentRecyclerOil,
                    packFragmentRecyclerOther
                ).forEach {
                    it.layoutManager = GridLayoutManager(requireContext(), 2)
                    it.isNestedScrollingEnabled = false
                }

//                listOf(
//                    packFragmentRecyclerEnergyDetailed,
//                    packFragmentRecyclerPowerDetailed,
//                    packFragmentRecyclerOilDetailed,
//                    packFragmentRecyclerOtherDetailed
//                ).forEach {
//                    it.layoutManager = LinearLayoutManager(requireContext())
//                    it.isNestedScrollingEnabled = false
//                }

//                energyProductsAdapter.onItemClick = { item ->
//                    packFragmentRecyclerEnergy.visibility = View.GONE
//                    packFragmentRecyclerEnergyDetailed.visibility = View.VISIBLE
//                }
//
//                energyProductsDetailedAdapter.onItemClick = { item ->
//                    packFragmentRecyclerEnergyDetailed.visibility = View.GONE
//                    packFragmentRecyclerEnergy.visibility = View.VISIBLE
//                }
//
//                powerProductsAdapter.onItemClick = { item ->
//                    packFragmentRecyclerPower.visibility = View.GONE
//                    packFragmentRecyclerPowerDetailed.visibility = View.VISIBLE
//                }
//
//                powerProductsDetailedAdapter.onItemClick = { item ->
//                    packFragmentRecyclerPowerDetailed.visibility = View.GONE
//                    packFragmentRecyclerPower.visibility = View.VISIBLE
//                }
//
//                oilProductsAdapter.onItemClick = { item ->
//                    packFragmentRecyclerOil.visibility = View.GONE
//                    packFragmentRecyclerOilDetailed.visibility = View.VISIBLE
//                }
//
//                oilProductsDetailedAdapter.onItemClick = { item ->
//                    packFragmentRecyclerOilDetailed.visibility = View.GONE
//                    packFragmentRecyclerOil.visibility = View.VISIBLE
//                }
//
//                otherProductsAdapter.onItemClick = { item ->
//                    packFragmentRecyclerOther.visibility = View.GONE
//                    packFragmentRecyclerOtherDetailed.visibility = View.VISIBLE
//                }
//
//                otherProductsDetailedAdapter.onItemClick = { item ->
//                    findNavController().navigate(
//                        R.id.action_packFragment_to_productCardFragment,
//                        bundleOf(
//                            "title" to item.title,
//                            "price" to item.price,
//                            "categoryId" to item.categoryId,
//                            "manufacturer" to item.manufacturer,
//                            "brand" to item.brand,
//                            "country" to item.country,
//                            "fraction" to item.fraction,
//                            "weigh" to item.weigh,
//                            "pricePerKgMl" to item.pricePerKgMl,
//                            "composition" to item.composition,
//                            "energyValue" to item.energyValue,
//                            "fats" to item.fats,
//                            "proteins" to item.proteins,
//                            "carbohydrates" to item.carbohydrates,
//                            "storageConditions" to item.storageConditions,
//                            "storageTemperatureMin" to item.storageTemperatureMin,
//                            "storageTemperatureMax" to item.storageTemperatureMax,
//                            "storageLife" to item.storageLife,
//                            "description" to item.description,
//                            "image" to item.image,
//                            "racCoefficientBenefit" to item.racCoefficientBenefit,
//                            "racCategoryBenefit" to item.racCategoryBenefit,
//                            "shopId" to item.shopId
//                        )
//                    )
//                }

//                listOf(
//                    powerProductsAdapter,
//                    oilProductsAdapter,
//                    otherProductsAdapter
//                ).forEach {
//                    it.onItemClick = { item ->
//                        findNavController().navigate(
//                            R.id.action_packFragment_to_productCardFragment,
//                            bundleOf(
//                                "title" to item.title,
//                                "price" to item.price,
//                                "categoryId" to item.categoryId,
//                                "manufacturer" to item.manufacturer,
//                                "brand" to item.brand,
//                                "country" to item.country,
//                                "fraction" to item.fraction,
//                                "weigh" to item.weigh,
//                                "pricePerKgMl" to item.pricePerKgMl,
//                                "composition" to item.composition,
//                                "energyValue" to item.energyValue,
//                                "fats" to item.fats,
//                                "proteins" to item.proteins,
//                                "carbohydrates" to item.carbohydrates,
//                                "storageConditions" to item.storageConditions,
//                                "storageTemperatureMin" to item.storageTemperatureMin,
//                                "storageTemperatureMax" to item.storageTemperatureMax,
//                                "storageLife" to item.storageLife,
//                                "description" to item.description,
//                                "image" to item.image,
//                                "racCoefficientBenefit" to item.racCoefficientBenefit,
//                                "racCategoryBenefit" to item.racCategoryBenefit,
//                                "shopId" to item.shopId
//                            )
//                        )
//                    }
//                }

                packFragmentRecyclerEnergy.adapter = energyProductsAdapter
//                packFragmentRecyclerEnergyDetailed.adapter = energyProductsDetailedAdapter
                packFragmentRecyclerPower.adapter = powerProductsAdapter
//                packFragmentRecyclerPowerDetailed.adapter = powerProductsDetailedAdapter
                packFragmentRecyclerOil.adapter = oilProductsAdapter
//                packFragmentRecyclerOilDetailed.adapter = oilProductsDetailedAdapter
                packFragmentRecyclerOther.adapter = otherProductsAdapter
//                packFragmentRecyclerOtherDetailed.adapter = otherProductsDetailedAdapter

            }
        }

    }

}