package com.example.raczakupsecond.screens.profile.address

import android.Manifest
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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.models.addresses.AddressParamsRequestDomain
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
    CameraListener,
    Session.SearchListener
{
    private lateinit var binding : FragmentAddressBinding
    private val viewModel : AddressFragmentVM by viewModels()

    private lateinit var mode: String

    private var autoFillAddressEnable: Boolean = false

    private lateinit var currentPosition: Point
    private var currentZoom: Float = 0F

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var userLocationLayer: UserLocationLayer

    private lateinit var searchManager: SearchManager
    private lateinit var searchSession: Session

    private fun searchPoint(point: Point) {
        searchSession = searchManager.submit(
            point,
            16,
            SearchOptions(),
            this
        )
    }

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

        binding.buttonAddressfragmentNext.setOnClickListener {

            binding.apply {

                listOf(
                    etAddressfragmentCity,
                    etAddressfragmentStreet,
                    etAddressfragmentBuilding,
                    etAddressfragmentFloor,
                    etAddressfragmentFlat,
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
                    buttonAddressfragmentSave
                ).forEach {
                    it.visibility = View.VISIBLE
                }

                mapview.map.isScrollGesturesEnabled = false
                mapview.map.isRotateGesturesEnabled = false
                mapview.map.isTiltGesturesEnabled = false
                mapview.map.isZoomGesturesEnabled = false

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

        //region Mapkit

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        binding.mapview.map.addCameraListener(this)

        val traffic = MapKitFactory.getInstance().createTrafficLayer(binding.mapview.mapWindow)
        traffic.isTrafficVisible = false

        userLocationLayer = MapKitFactory.getInstance().createUserLocationLayer(binding.mapview.mapWindow)
        userLocationLayer.isVisible = true
        userLocationLayer.isHeadingEnabled = false
        userLocationLayer.setObjectListener(this)

        SearchFactory.initialize(requireContext())
        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)

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

                if (mode == Constants.EDIT_MODE) {

                    getEditingPointLiveData().observe(viewLifecycleOwner) {

                        currentPosition = it
                        currentZoom = 17.0F
//                        Log.d("CURRENT_LOCATION", "${location.latitude}, ${location.longitude}")
                        binding.mapview.map.move(
                            CameraPosition(currentPosition, currentZoom, 0.0f, 0.0f),
                            Animation(Animation.Type.SMOOTH, 2F),
                            null
                        )

                    }
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

            searchPoint(currentPosition)
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

    override fun onSearchResponse(response: Response) {
        val mapObject: MapObjectCollection = binding.mapview.map.mapObjects
        mapObject.clear()

        if (mode == Constants.EDIT_MODE && !autoFillAddressEnable) {
            autoFillAddressEnable = true
        } else {

            val city = response.collection.children.firstOrNull()?.obj
                ?.metadataContainer
                ?.getItem(ToponymObjectMetadata::class.java)
                ?.address
                ?.components
                ?.firstOrNull { it.kinds.contains(Address.Component.Kind.LOCALITY) }
                ?.name

            if (city != null) {
                viewModel.setCityLiveData(city)
            } else {
                viewModel.setCityLiveData("")
            }

            val district = response.collection.children.firstOrNull()?.obj
                ?.metadataContainer
                ?.getItem(ToponymObjectMetadata::class.java)
                ?.address
                ?.components
                ?.firstOrNull { it.kinds.contains(Address.Component.Kind.DISTRICT) }
                ?.name

            val province = response.collection.children.firstOrNull()?.obj
                ?.metadataContainer
                ?.getItem(ToponymObjectMetadata::class.java)
                ?.address
                ?.components
                ?.firstOrNull { it.kinds.contains(Address.Component.Kind.PROVINCE) }
                ?.name

            Log.d("CITY", city ?: "null")

            Log.d("PROVINCE", province ?: "null")

            Log.d("DISTRICT", district ?: "null")

            val street = response.collection.children.firstOrNull()?.obj
                ?.metadataContainer
                ?.getItem(ToponymObjectMetadata::class.java)
                ?.address
                ?.components
                ?.firstOrNull { it.kinds.contains(Address.Component.Kind.STREET) }
                ?.name

            if (street != null) {
                viewModel.setStreetLiveData(street)
            } else {
                viewModel.setStreetLiveData("")
            }

            val building = response.collection.children.firstOrNull()?.obj
                ?.metadataContainer
                ?.getItem(ToponymObjectMetadata::class.java)
                ?.address
                ?.components
                ?.firstOrNull { it.kinds.contains(Address.Component.Kind.HOUSE) }
                ?.name

            if (building != null) {
                viewModel.setBuildingLiveData(building)
            } else {
                viewModel.setBuildingLiveData("")
            }

            val entrance = response.collection.children.firstOrNull()?.obj
                ?.metadataContainer
                ?.getItem(ToponymObjectMetadata::class.java)
                ?.address
                ?.components
                ?.firstOrNull { it.kinds.contains(Address.Component.Kind.ENTRANCE) }
                ?.name

            if (entrance != null) {
                viewModel.setEntranceLiveData(entrance)
            } else {
                viewModel.setEntranceLiveData("")
            }

//        val other = response.collection.children.firstOrNull()?.obj
//            ?.metadataContainer
//            ?.getItem(ToponymObjectMetadata::class.java)
//            ?.address
//            ?.components
//            ?.firstOrNull { it.kinds.contains(Address.Component.Kind.OTHER) }
//            ?.name
//
//        Log.d("OTHER", other ?: "null")

        }

    }

    override fun onSearchError(error: Error) {
        var errorMessage = "Неизвестная ошибка"
        if (error is RemoteError) {
            errorMessage = "Ошибка сети"
        } else if (error is NetworkError) {
            errorMessage = "Проблемы с интернетом"
        }
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val ADDRESS_ID = "address_id"
    }

}
