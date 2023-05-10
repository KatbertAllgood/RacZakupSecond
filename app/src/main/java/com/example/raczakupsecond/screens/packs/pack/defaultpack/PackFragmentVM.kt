package com.example.raczakupsecond.screens.packs.pack.defaultpack

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.models.packs.HealthySetParamsResponseDomain
import com.example.domain.usecase.packs.CreateHealthySetParamsUseCase
import com.example.raczakupsecond.app.App
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PackFragmentVM : ViewModel() {

    private val networkRepository = App.getNetworkRepository()
    private val createHealthySetParamsUseCase = CreateHealthySetParamsUseCase(networkRepository)

    private val healthySetParamsLiveData = MutableLiveData<HealthySetParamsResponseDomain>()
    fun getHealthySetParamsLiveData() : LiveData<HealthySetParamsResponseDomain> =
        healthySetParamsLiveData

    fun createHealthySet(
        healthySetParams: HealthySetParamsRequestDomain
    ) {
        createHealthySetParamsUseCase
            .invoke(healthySetParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<HealthySetParamsResponseDomain>() {
                override fun onSuccess(t: HealthySetParamsResponseDomain) {
                    healthySetParamsLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("CREATE_HS_ERROR", e.message.toString())
                }

            })
    }

    fun initPieChart(
        pieChart: PieChart,
        proteins: Float,
        fats: Float,
        carbs: Float,
        proteinsColor: Int,
        fatsColor: Int,
        carbsColor: Int
    ) {

        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart

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
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // on below line we are creating array list and
        // adding data to it to display in pie chart
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(fats))
        entries.add(PieEntry(carbs))
        entries.add(PieEntry(proteins))

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
        colors.add(fatsColor)
        colors.add(carbsColor)
        colors.add(proteinsColor)

        // on below line we are setting colors.
        dataSet.colors = colors

        // on below line we are setting pie data set
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)
        pieChart.data = data

        pieChart.setUsePercentValues(true)

        // undo all highlights
        pieChart.highlightValues(null)

        // loading chart
        pieChart.invalidate()

    }
}