package com.example.raczakupsecond.screens.profile.address

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.models.addresses.AddressParamsRequestDomain
import com.example.domain.usecase.address.CreateAddressUseCase
import com.example.domain.usecase.address.GetAddressUseCase
import com.example.domain.usecase.address.UpdateAddressUseCase
import com.example.raczakupsecond.app.App
import com.yandex.mapkit.geometry.Point
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AddressFragmentVM : ViewModel() {

    private val networkRepository = App.getNetworkRepository()

    private val createAddressUseCase = CreateAddressUseCase(networkRepository)
    private val updateAddressUseCase = UpdateAddressUseCase(networkRepository)
    private val getAddressUseCase = GetAddressUseCase(networkRepository)

    var editingAddressId: String = ""

    private val editingPointLiveData = MutableLiveData<Point>()
    fun getEditingPointLiveData() : LiveData<Point> = editingPointLiveData

//    private val addressParamsToUpdateLiveData = MutableLiveData<AddressParamsDomain>()
//    fun getAddressParamsToUpdateLiveData(): LiveData<AddressParamsDomain> = addressParamsToUpdateLiveData

    private val cityLiveData = MutableLiveData<String>()
    fun getCityLiveData() : LiveData<String> = cityLiveData
    fun setCityLiveData(city: String) {
        cityLiveData.value = city
    }

    private val streetLiveData = MutableLiveData<String>()
    fun getStreetLiveData() : LiveData<String> = streetLiveData
    fun setStreetLiveData(street: String) {
        streetLiveData.value = street
    }

    private val buildingLiveData = MutableLiveData<String>()
    fun getBuildingLiveData() : LiveData<String> = buildingLiveData
    fun setBuildingLiveData(building: String) {
        buildingLiveData.value = building
    }

    private val entranceLiveData = MutableLiveData<String>()
    fun getEntranceLiveData() : LiveData<String> = entranceLiveData
    fun setEntranceLiveData(entrance: String) {
        entranceLiveData.value = entrance
    }

    private val floorLiveData = MutableLiveData<String>()
    fun getFloorLiveData() : LiveData<String> = floorLiveData
    fun setFloorLiveData(floor: String) {
        floorLiveData.value = floor
    }

    private val flatLiveData = MutableLiveData<String>()
    fun getFlatLiveData() : LiveData<String> = flatLiveData
    fun setFlatLiveData(flat: String) {
        flatLiveData.value = flat
    }

    private val titleLiveData = MutableLiveData<String>()
    fun getTitleLiveData() : LiveData<String> = titleLiveData
    fun setTitleLiveData(title: String) {
        titleLiveData.value = title
    }

    private val commentLiveData = MutableLiveData<String>()
    fun getCommentLiveData() : LiveData<String> = commentLiveData
    fun setCommentLiveData(comment: String) {
        commentLiveData.value = comment
    }

    fun getAddress() {
        getAddressUseCase.invoke(editingAddressId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<AddressParamsDomain>() {
                override fun onSuccess(t: AddressParamsDomain) {
//                    addressParamsToUpdateLiveData.value = t

//                    editingAddressId = t.id

                    setCityLiveData(t.city.toString())
                    setStreetLiveData(t.street.toString())
                    setBuildingLiveData(t.house_number.toString())
                    setEntranceLiveData(t.entrance.toString())
                    setFloorLiveData(t.floor.toString())
                    setFlatLiveData(t.apartment.toString())
                    setTitleLiveData(t.name.toString())
                    setCommentLiveData(t.comment.toString())

                    editingPointLiveData.value = Point(
                        t.location.coordinates[1],
                        t.location.coordinates[0]
                    )
                }

                override fun onError(e: Throwable) {
                    Log.d("GET_ADDRESS_ERROR", e.message.toString())
                }

            })
    }

    fun createAddress(
        address: AddressParamsRequestDomain
    ) {
        createAddressUseCase.invoke(address)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<AddressParamsDomain>() {
                override fun onSuccess(t: AddressParamsDomain) {
                    Log.d("CREATE_ADDRESS", t.id.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("CREATE_ADDRESS_ERROR", e.message.toString())
                }

            })
    }

    fun updateAddress(
        addressId: String,
        address: AddressParamsRequestDomain
    ) {
        updateAddressUseCase.invoke(
            addressId,
            address
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<AddressParamsDomain>() {
                override fun onSuccess(t: AddressParamsDomain) {
                    Log.d("UPDATE_ADDRESS", t.id.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("UPDATE_ADDRESS_ERROR", e.message.toString())
                }

            })
    }

    fun requestLocationPermission(
        context: Context,
        activity: Activity
    ) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 0
            )
        }
        return
    }

}
