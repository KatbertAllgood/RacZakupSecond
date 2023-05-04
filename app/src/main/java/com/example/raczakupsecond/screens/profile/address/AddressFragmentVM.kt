package com.example.raczakupsecond.screens.profile.address

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.models.addresses.AddressParamsRequestDomain
import com.example.domain.models.geo.RequestCoordinatesDomain
import com.example.domain.models.geo.RequestQueryDomain
import com.example.domain.models.geo.ResponseGeoCoordinatesDomain
import com.example.domain.models.geo.ResponseGeoDomain
import com.example.domain.usecase.address.CreateAddressUseCase
import com.example.domain.usecase.address.GetAddressUseCase
import com.example.domain.usecase.address.UpdateAddressUseCase
import com.example.domain.usecase.geo.ResolveCoordinatesUseCase
import com.example.domain.usecase.geo.ResolveQueryUseCase
import com.example.raczakupsecond.app.App
import com.yandex.mapkit.geometry.Point
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AddressFragmentVM : ViewModel() {

    private val TAG = AddressFragmentVM::class.simpleName

    private val networkRepository = App.getNetworkRepository()

    private val createAddressUseCase = CreateAddressUseCase(networkRepository)
    private val updateAddressUseCase = UpdateAddressUseCase(networkRepository)
    private val getAddressUseCase = GetAddressUseCase(networkRepository)
    private val resolveQueryUseCase = ResolveQueryUseCase(networkRepository)
    private val resolveCoordinatesUseCase = ResolveCoordinatesUseCase(networkRepository)

    var editingAddressId: String = ""

    private val editingPointLiveData = MutableLiveData<Point>()
    fun getEditingPointLiveData() : LiveData<Point> = editingPointLiveData


    //region UI

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

    //endregion

    //region not UI

    private var geoResponse : ResponseGeoDomain = ResponseGeoDomain()
    fun getGeoResponse() = geoResponse

    //endregion

    private val queryResponseResultLiveData = MutableLiveData<String>()
    fun getQueryResponseResultLiveData() : LiveData<String> = queryResponseResultLiveData


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
                    setBuildingLiveData(t.house.toString())
                    setEntranceLiveData(t.entrance.toString())
                    setFloorLiveData(t.floor.toString())
                    setFlatLiveData(t.flat.toString())
                    setTitleLiveData(t.name.toString())
                    setCommentLiveData(t.comment.toString())

                    editingPointLiveData.value = Point(
                        t.location.coordinates[1],
                        t.location.coordinates[0]
                    )
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "GET_ADDRESS_ERROR: ${ e.message.toString() }")
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
                    Log.d(TAG, "CREATE_ADDRESS_SUCCESS: ${t.id}")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "CREATE_ADDRESS_ERROR: ${ e.message.toString() }")
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
                    Log.d(TAG, "UPDATE_ADDRESS_SUCCESS: ${ t.id.toString() }")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "UPDATE_ADDRESS_ERROR: ${ e.message.toString() }")
                }

            })
    }

    fun resolveCoordinates(
        coordinates: RequestCoordinatesDomain
    ) {
        resolveCoordinatesUseCase
            .invoke(coordinates)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<ResponseGeoCoordinatesDomain>>() {
                override fun onSuccess(t: List<ResponseGeoCoordinatesDomain>) {
                    Log.d(TAG, "RESOLVE_COORDINATES: SUCCESS")

                    if (t[0].data.city == null
                        && t[0].data.region_type_full.toString() == "город") {
                        setCityLiveData(t[0].data.region.toString())
                    } else {
                        setCityLiveData(t[0].data.city.toString())
                    }

                    if (t[0].data.street == null
                        && t[0].data.settlement != null) {
                        setStreetLiveData(t[0].data.settlement.toString())
                    } else {
                        setStreetLiveData(t[0].data.street.toString())
                    }

                    if (t[0].data.block != null) {
                        val building =
                            "${t[0].data.house.toString()}к${t[0].data.block.toString()}"

                        setBuildingLiveData(building)
                    } else if (t[0].data.house != null) {
                        setBuildingLiveData(t[0].data.house.toString())
                    }

                    geoResponse = t[0].data

                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "RESOLVE_COORDINATES_ERROR: ${ e.message.toString() }")
                }

            })
    }

    fun resolveQuery(
        query: RequestQueryDomain
    ) {
        resolveQueryUseCase
            .invoke(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ResponseGeoDomain>() {
                override fun onSuccess(t: ResponseGeoDomain) {
                    Log.d(TAG, "RESOLVE_QUERY: SUCCESS")

                    if (t.city == null
                        && t.region_type_full.toString() == "город") {
                        setCityLiveData(t.region.toString())
                    } else {
                        setCityLiveData(t.city.toString())
                    }

                    if (t.street == null
                        && t.settlement != null) {
                        setStreetLiveData(t.settlement.toString())
                    } else {
                        setStreetLiveData(t.street.toString())
                    }

                    if (t.block != null) {
                        val building =
                            "${t.house.toString()}к${t.block.toString()}"

                        setBuildingLiveData(building)
                    } else {
                        setBuildingLiveData(t.house.toString())
                    }

                    editingPointLiveData.value = Point(
                        t.lat,
                        t.lon
                    )

                    if(t.result.toString() != null && t.result.toString() != "") {
                        queryResponseResultLiveData.value = t.result.toString()
                    }

                    geoResponse = t
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "RESOLVE_QUERY_ERROR: ${ e.message.toString() }")
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
