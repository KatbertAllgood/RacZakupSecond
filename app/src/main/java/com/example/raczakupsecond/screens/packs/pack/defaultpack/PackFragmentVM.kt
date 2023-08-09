package com.example.raczakupsecond.screens.packs.pack.defaultpack

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.packs.HealthySetParamsAddProductResponseDomain
import com.example.domain.models.packs.HealthySetParamsRefreshProductResponseDomain
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.models.packs.HealthySetParamsResponseDomain
import com.example.domain.models.shop.ProductParamsDomain
import com.example.domain.usecase.packs.AddProductToHealthySetUseCase
import com.example.domain.usecase.packs.CreateHealthySetParamsUseCase
import com.example.domain.usecase.packs.RefreshProductInHealthySetUseCase
import com.example.raczakupsecond.R
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
import java.util.Collections

class PackFragmentVM : ViewModel() {
    private val TAG = PackFragment::class.simpleName

    private lateinit var pieChart: PieChart
    fun setPieChart(t: PieChart) {
        pieChart = t
    }

    private val networkRepository = App.getNetworkRepository()
    private val createHealthySetParamsUseCase = CreateHealthySetParamsUseCase(networkRepository)
    private val addProductToHealthySetUseCase = AddProductToHealthySetUseCase(networkRepository)

    lateinit var healthySetId: String

    private val healthySetParamsLiveData = MutableLiveData<HealthySetParamsResponseDomain>()
    fun getHealthySetParamsLiveData() : LiveData<HealthySetParamsResponseDomain> =
        healthySetParamsLiveData

    private val energyProductsLiveData = MutableLiveData<List<ProductParamsDomain>>()
    fun getEnergyProductsLiveData() : LiveData<List<ProductParamsDomain>> = energyProductsLiveData

    private val powerProductsLiveData = MutableLiveData<List<ProductParamsDomain>>()
    fun getPowerProductsLiveData() : LiveData<List<ProductParamsDomain>> = powerProductsLiveData

    private val oilProductsLiveData = MutableLiveData<List<ProductParamsDomain>>()
    fun getOilProductsLiveData() : LiveData<List<ProductParamsDomain>> = oilProductsLiveData

    private val allProteins = MutableLiveData<Int>()
    private val allFats = MutableLiveData<Int>()
    private val allCarbs = MutableLiveData<Int>()
    private val allPrice = MutableLiveData<Double>()

    fun getAllProteins() : LiveData<Int> = allProteins
    fun getAllFats() : LiveData<Int> = allFats
    fun getAllCarbs() : LiveData<Int> = allCarbs
    fun getAllPrice() : LiveData<Double> = allPrice

    fun createHealthySet(
        healthySetParams: HealthySetParamsRequestDomain,
        context: Context
    ) {
        createHealthySetParamsUseCase
            .invoke(healthySetParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<HealthySetParamsResponseDomain>() {
                override fun onSuccess(t: HealthySetParamsResponseDomain) {
                    healthySetParamsLiveData.value = t

                    healthySetId = t.data.healthySetId.toString()

                    allProteins.value = t.data.healthySet.racParams.proteins
                    allFats.value = t.data.healthySet.racParams.fats
                    allCarbs.value = t.data.healthySet.racParams.carbohydrates

                    var allPriceCount = 0.0

                    listOf(
                        t.data.healthySet.racEnergy,
                        t.data.healthySet.racPower,
                        t.data.healthySet.racOil,
                    ).forEach{
                        for (i in it){
                            allPriceCount += i.price * i.amount
                        }
                    }

                    allPrice.value = allPriceCount

                    initPieChart(
                        context.resources.getColor(R.color.proteins),
                        context.resources.getColor(R.color.fats),
                        context.resources.getColor(R.color.carbohydrates)
                    )

                    energyProductsLiveData.value = t.data.healthySet.racEnergy
                    powerProductsLiveData.value = t.data.healthySet.racPower
                    oilProductsLiveData.value = t.data.healthySet.racOil
                }

                override fun onError(e: Throwable) {
                    Log.d("CREATE_HS_ERROR", e.message.toString())
                }

            })
    }

    fun addProduct(
        healthySetId: String,
        productType: String,
        context: Context
    ) {
        addProductToHealthySetUseCase.invoke(healthySetId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<HealthySetParamsAddProductResponseDomain>() {
                override fun onSuccess(t: HealthySetParamsAddProductResponseDomain) {
                    Log.d(TAG, "SUCCESS: ADDED_PRODUCT: ${t.data.addProduct}")

                    when (productType) {
                        "energy" -> {

                            val productsList : MutableList<ProductParamsDomain> =
                                energyProductsLiveData.value!!.toMutableList()

                            productsList.add(t.data.addProduct)

                            energyProductsLiveData.value = productsList
                        }
                        "power" -> {

                            val productsList : MutableList<ProductParamsDomain> =
                                powerProductsLiveData.value!!.toMutableList()

                            productsList.add(t.data.addProduct)

                            powerProductsLiveData.value = productsList
                        }
                        "oil" -> {

                            val productsList : MutableList<ProductParamsDomain> =
                                oilProductsLiveData.value!!.toMutableList()

                            productsList.add(t.data.addProduct)

                            oilProductsLiveData.value = productsList
                        }
                    }

                    val newAmountedPrice = t.data.addProduct.price * t.data.addProduct.amount
                    allPrice.value = allPrice.value!! + newAmountedPrice

                    allProteins.value = t.data.racParams.proteins
                    allFats.value = t.data.racParams.fats
                    allCarbs.value = t.data.racParams.carbohydrates

                    initPieChart(
                        context.resources.getColor(R.color.proteins),
                        context.resources.getColor(R.color.fats),
                        context.resources.getColor(R.color.carbohydrates)
                    )
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "ADD_PRODUCT_ERROR: ${e.message}")
                }

            })
    }

    fun initPieChart(
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
        entries.add(PieEntry(allFats.value!!.toFloat()))
        entries.add(PieEntry(allCarbs.value!!.toFloat()))
        entries.add(PieEntry(allProteins.value!!.toFloat()))

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