package com.example.raczakupsecond.screens.packs.pack

import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentPackBinding
import com.example.raczakupsecond.screens.packs.pack.adapters.PackFragmentProductsAdapter
import com.github.mikephil.charting.animation.Easing
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
            requireArguments().getInt("familyId"),
            requireArguments().getInt("days"),
            requireArguments().getInt("budget").toString(),
            requireArguments().getInt("addressId")
        ))

        viewModel.getHealthySetParamsLiveData().observe(viewLifecycleOwner) {

            val productsAdapter = PackFragmentProductsAdapter(it.data)

            binding.apply {
                packFragmentRecyclerProtein.layoutManager = GridLayoutManager(requireContext(), 3)
                packFragmentRecyclerProtein.isNestedScrollingEnabled = false

                productsAdapter.onItemClick = { item ->
                    val deeplinkProductCard = Uri.parse("raczakup://internal_navigation_productCardFragment")
                    findNavController().navigate(
                        deeplinkProductCard,
                    )
                }

                packFragmentRecyclerProtein.adapter = productsAdapter

            }
        }

    }

}