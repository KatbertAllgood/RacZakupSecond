package com.example.raczakupsecond.screens.profile.address

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.PointF
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.models.addresses.AddressParamsRequestDomain
import com.example.domain.models.geo.RequestCoordinatesDomain
import com.example.domain.models.geo.RequestQueryDomain
import com.example.domain.utils.Constants
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentAddressBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.search.*
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.network.NetworkError
import com.yandex.runtime.network.RemoteError

class AddressFragment : Fragment(R.layout.fragment_address),
    UserLocationObjectListener,
    CameraListener
{
    private lateinit var binding : FragmentAddressBinding
    private val viewModel : AddressFragmentVM by viewModels()

    private lateinit var mode: String

    private lateinit var currentPosition: Point
    private var currentZoom: Float = 0F

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var userLocationLayer: UserLocationLayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mode = requireArguments().getString(Constants.MODE).toString()

        if (mode == Constants.EDIT_MODE) {

            viewModel.editingAddressId = requireArguments().getString(ADDRESS_ID).toString()
            viewModel.getAddress()
        }

        var isShowed = true
        var isEmpty = false

        binding.ivAddressfragmentButtonShowOrHide.setOnClickListener {

            if (!isShowed) {

                binding.ivAddressfragmentButtonShowOrHide.setImageResource(R.drawable.ic_button_down_addressfragment)

                binding.clAddressFragmentMapBottom.visibility = View.VISIBLE

                isShowed = true

            } else if (isShowed) {

                binding.ivAddressfragmentButtonShowOrHide.setImageResource(R.drawable.ic_button_button_show_cl_addressfragment)

                binding.clAddressFragmentMapBottom.visibility = View.GONE

                isShowed = false

            }
        }

        binding.mapviewButtonZoomPlus.setOnClickListener {
            if ((currentZoom + 1.0F) <= 20.0F) {
                currentZoom += 1.0F
                binding.mapview.map.move(
                    CameraPosition(currentPosition, currentZoom, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 1F),
                    null
                )
            }
        }

        binding.mapviewButtonZoomMinus.setOnClickListener {

            if ((currentZoom - 1.0F) >= 5.0F) {
                currentZoom -= 1.0F
                binding.mapview.map.move(
                    CameraPosition(currentPosition, currentZoom, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 1F),
                    null
                )
            }
        }

        binding.mapviewButtonShowCurrentLocation.setOnClickListener {
            getCurrentUserLocation()
        }

        binding.clAddressFragmentMapBottom.setOnClickListener {

        }

        binding.editTextAddressFragmentSearch.setOnEditorActionListener {
                editText, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.resolveQuery(
                    RequestQueryDomain(binding.editTextAddressFragmentSearch.text.toString())
                )
                editText.clearFocus()
                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(editText.windowToken, 0)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        binding.buttonAddressfragmentNext.setOnClickListener {

            // TODO(ПРОВЕРКА ВВЕДЕНИЯ ДАННЫХ. НЕ ЗНАЮ, ПОЧЕМУ НЕ ПОЛУЧАЕТСЯ)

            binding.apply {

                val listOfEt = listOf(
                    etAddressfragmentCity,
                    etAddressfragmentStreet,
                    etAddressfragmentBuilding,
                    etAddressfragmentFloor,
                    etAddressfragmentFlat,
                    etAddressfragmentEntrance
                )

                for (i in listOfEt) {
                    if (i.text.toString().trim().isNullOrBlank()) {
                        i.error = "Необходимо заполнить"
                        isEmpty = true
                    } else {
                        isEmpty = false
                    }
                }

                listOf(
                    etAddressfragmentCity,
                    etAddressfragmentStreet,
                    etAddressfragmentBuilding,
                    etAddressfragmentFloor,
                    etAddressfragmentFlat,
                    etAddressfragmentEntrance
                ).forEach {
                    Log.d("CHECK_EDIT_TEXT", it.text.toString())
//                    if (it.text == null || it.text.toString() == "" || it.text.isEmpty()) {
//                        it.error = "Необходимо заполнить"
//                        isEmpty = true
//                    } else isEmpty = false
                }

                Log.d("IS_EMPTY", isEmpty.toString())

                if (!isEmpty) {

                    listOf(
                        etAddressfragmentCity,
                        etAddressfragmentStreet,
                        etAddressfragmentBuilding,
                        addressFragmentLl3,
                        buttonAddressfragmentNext,
                        mapviewButtonZoomPlus,
                        mapviewButtonZoomMinus,
                        mapviewButtonShowCurrentLocation
                    ).forEach {
                        it.visibility = View.GONE
                    }

                    listOf(
                        etAddressfragmentAddressTitle,
                        etAddressfragmentAddressComment,
                        buttonAddressfragmentSave,
                        buttonAddressfragmentBack
                    ).forEach {
                        it.visibility = View.VISIBLE
                    }

                    mapview.map.isScrollGesturesEnabled = false
                    mapview.map.isRotateGesturesEnabled = false
                    mapview.map.isTiltGesturesEnabled = false
                    mapview.map.isZoomGesturesEnabled = false

                }

            }
        }

        binding.buttonAddressfragmentBack.setOnClickListener {

            binding.apply {

                listOf(
                    etAddressfragmentCity,
                    etAddressfragmentStreet,
                    etAddressfragmentBuilding,
                    addressFragmentLl3,
                    buttonAddressfragmentNext,
                    mapviewButtonZoomPlus,
                    mapviewButtonZoomMinus,
                    mapviewButtonShowCurrentLocation
                ).forEach {
                    it.visibility = View.VISIBLE
                }

                listOf(
                    etAddressfragmentAddressTitle,
                    etAddressfragmentAddressComment,
                    buttonAddressfragmentSave,
                    buttonAddressfragmentBack
                ).forEach {
                    it.visibility = View.GONE
                }

                mapview.map.isScrollGesturesEnabled = true
                mapview.map.isRotateGesturesEnabled = true
                mapview.map.isTiltGesturesEnabled = true
                mapview.map.isZoomGesturesEnabled = true

            }

        }

        binding.buttonAddressfragmentSave.setOnClickListener {

            binding.apply {


                val addressParams = AddressParamsRequestDomain(
                    name = etAddressfragmentAddressTitle.text.toString(),
                    country = "",
                    region = "",
                    district = "",
                    city = etAddressfragmentCity.text.toString(),
                    locality = "",
                    street = etAddressfragmentStreet.text.toString(),
                    house_number = etAddressfragmentBuilding.text.toString(),
                    corpus = "",
                    apartment = etAddressfragmentFlat.text.toString(),
                    entrance = etAddressfragmentEntrance.text.toString(),
                    floor = etAddressfragmentFloor.text.toString(),
                    comment = etAddressfragmentAddressComment.text.toString(),
                    postal_code = "1111",
                    lat = currentPosition.latitude,
                    lon = currentPosition.longitude
                )

                Log.d("ADDRESS_PARAMS", addressParams.toString())

                if (mode == Constants.CREATE_MODE) {

                    viewModel.createAddress(addressParams)

                } else if (mode == Constants.EDIT_MODE) {

                    viewModel.updateAddress(
                        viewModel.editingAddressId,
                        addressParams
                    )
                }

                findNavController().popBackStack()
            }
        }

        binding.apply {

            etAddressfragmentCity.setOnClickListener {
                etAddressfragmentCity.error = null
            }

        }

        //region Mapkit

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        binding.mapview.map.addCameraListener(this)

        val traffic = MapKitFactory.getInstance().createTrafficLayer(binding.mapview.mapWindow)
        traffic.isTrafficVisible = false

        userLocationLayer = MapKitFactory.getInstance().createUserLocationLayer(binding.mapview.mapWindow)
        userLocationLayer.isVisible = true
        userLocationLayer.isHeadingEnabled = false
        userLocationLayer.setObjectListener(this)

        //endregion

        if (mode == Constants.CREATE_MODE) {
            getCurrentUserLocation()
        }

    }

    override fun onStart() {
        super.onStart()
        activity?.let { viewModel.requestLocationPermission(requireContext(), it) }
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    override fun onResume() {
        super.onResume()

        binding.apply {

            viewModel.apply {

                getCityLiveData().observe(viewLifecycleOwner) {
                    etAddressfragmentCity.setText(it)
                }

                getStreetLiveData().observe(viewLifecycleOwner) {
                    etAddressfragmentStreet.setText(it)
                }

                getBuildingLiveData().observe(viewLifecycleOwner) {
                    etAddressfragmentBuilding.setText(it)
                }

                getEntranceLiveData().observe(viewLifecycleOwner) {
                    etAddressfragmentEntrance.setText(it)
                }

                getFloorLiveData().observe(viewLifecycleOwner) {
                    etAddressfragmentFloor.setText(it)
                }

                getFlatLiveData().observe(viewLifecycleOwner) {
                    etAddressfragmentFlat.setText(it)
                }

                getTitleLiveData().observe(viewLifecycleOwner) {
                    etAddressfragmentAddressTitle.setText(it)
                }

                getCommentLiveData().observe(viewLifecycleOwner) {
                    etAddressfragmentAddressComment.setText(it)
                }

                getQueryResponseResultLiveData().observe(viewLifecycleOwner) {
                    editTextAddressFragmentSearch.setText(it)
                }

//                if (mode == Constants.EDIT_MODE) {

                    getEditingPointLiveData().observe(viewLifecycleOwner) {

                        currentPosition = it
                        currentZoom = 17.0F
//                        Log.d("CURRENT_LOCATION", "${location.latitude}, ${location.longitude}")
                        binding.mapview.map.move(
                            CameraPosition(currentPosition, currentZoom, 0.0f, 0.0f),
                            Animation(Animation.Type.SMOOTH, 2F),
                            null
                        )

//                    }
                }
            }

        }

    }

    override fun onStop() {
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onObjectAdded(userLocationView: UserLocationView) {

        userLocationView.arrow.setIcon(
            ImageProvider.fromResource(
                requireContext(),
                R.drawable.ic_mapkit_user_location
            )
        )

        val picIcon = userLocationView.pin.useCompositeIcon()

        picIcon.setIcon(
            "icon",
            ImageProvider.fromResource(
                requireContext(),
                R.drawable.ic_mapkit_pin
            ),
            IconStyle().setAnchor(PointF(0.5F, 0.5F))
                .setRotationType(RotationType.ROTATE).setZIndex(1F).setScale(0.5F)
        )

    }

    override fun onObjectRemoved(p0: UserLocationView) {

    }

    override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {

    }

    override fun onCameraPositionChanged(
        p0: Map,
        p1: CameraPosition,
        p2: CameraUpdateReason,
        p3: Boolean
    ) {
        if (p3) {
            val param = binding.mapviewAimPin.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0, 0, 0, 0)
            binding.mapviewAimPin.layoutParams = param

            currentPosition = Point(p1.target.latitude, p1.target.longitude)

            viewModel.resolveCoordinates(RequestCoordinatesDomain(
                currentPosition.latitude,
                currentPosition.longitude
            ))
        } else {

            val param = binding.mapviewAimPin.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0, 0, 0, 120)
            binding.mapviewAimPin.layoutParams = param
        }
    }

    private fun getCurrentUserLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 0
            )
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    currentPosition = Point(location.latitude, location.longitude)
                    currentZoom = 17.0F
                    Log.d("CURRENT_LOCATION", "${location.latitude}, ${location.longitude}")
                    binding.mapview.map.move(
                        CameraPosition(currentPosition, currentZoom, 0.0f, 0.0f),
                        Animation(Animation.Type.SMOOTH, 2F),
                        null
                    )
                }
            }
    }

    companion object {
        const val ADDRESS_ID = "address_id"
    }

}
