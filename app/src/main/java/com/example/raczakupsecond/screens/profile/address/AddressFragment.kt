package com.example.raczakupsecond.screens.profile.address

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentAddressBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider

class AddressFragment : Fragment(R.layout.fragment_address),
    UserLocationObjectListener,
    CameraListener
{
    private lateinit var binding : FragmentAddressBinding
    private val viewModel : AddressFragmentVM by viewModels()

    private lateinit var currentPosition: Point
    private var currentZoom: Float = 0F

    private lateinit var userLocationLayer: UserLocationLayer

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isShowed = true
//        val clHeight = binding.clAddressFragmentMapBottom.layoutParams.height

        binding.ivAddressfragmentButtonShowOrHide.setOnClickListener {

            if (!isShowed) {

                binding.ivAddressfragmentButtonShowOrHide.setImageResource(R.drawable.ic_button_down_addressfragment)

                binding.clAddressFragmentMapBottom.visibility = View.VISIBLE

                //region анимация

//                val params = binding.clAddressFragmentMapBottom.layoutParams
////                params.width = ViewGroup.LayoutParams.MATCH_PARENT
//                params.height = clHeight
//
//                val animate = TranslateAnimation(
//                    0F,
//                    0F,
//                    clHeight.toFloat(),
//                    0F
//                )
//                animate.duration = 300
//                animate.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
//                    override fun onAnimationStart(p0: android.view.animation.Animation?) {}
//                    override fun onAnimationEnd(p0: android.view.animation.Animation?) {
//                        binding.clAddressFragmentMapBottom.layoutParams = params
//                    }
//                    override fun onAnimationRepeat(p0: android.view.animation.Animation?) {}
//
//                })
//                binding.clAddressFragmentMapBottom.visibility = View.VISIBLE
//                animate.fillAfter = true
//
//                binding.clAddressFragmentMapBottom.startAnimation(animate)
//
                //endregion

                isShowed = true

            } else if (isShowed) {

                binding.ivAddressfragmentButtonShowOrHide.setImageResource(R.drawable.ic_button_button_show_cl_addressfragment)

                binding.clAddressFragmentMapBottom.visibility = View.GONE

                //region анимация

//                val params = binding.clAddressFragmentMapBottom.layoutParams
////                params.width = ViewGroup.LayoutParams.MATCH_PARENT
//                params.height = 0
//
//                val animate = TranslateAnimation(
//                    0F,
//                    0F,
//                    0F,
//                    clHeight.toFloat()
//                )
//                animate.duration = 300
//                animate.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
//                    override fun onAnimationStart(p0: android.view.animation.Animation?) {}
//                    override fun onAnimationEnd(p0: android.view.animation.Animation?) {
//
//                        binding.clAddressFragmentMapBottom.layoutParams = params
//
//                        binding.apply {
//                            listOf(
//                                etAddressfragmentAddressTitle,
//                                etAddressfragmentEntrance,
//                                etAddressfragmentFlat,
//                                etAddressfragmentFloor,
//                                etAddressfragmentStreet,
//                                buttonAddressfragmentSave
//                            ).forEach {
//                                it.layoutParams = params
//                            }
//                        }
//                    }
//
//                    override fun onAnimationRepeat(p0: android.view.animation.Animation?) {}
//
//                })
//                animate.fillAfter = true
//
//                binding.clAddressFragmentMapBottom.startAnimation(animate)

                //endregion

                isShowed = false

            }
        }

//        binding.clAddressFragmentMapBottom.setOnClickListener {
//            Log.d("CL_CLICK", "TRUE")
//        }

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

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        getCurrentUserLocation()

        binding.mapview.map.addCameraListener(this)

        val traffic = MapKitFactory.getInstance().createTrafficLayer(binding.mapview.mapWindow)
        traffic.isTrafficVisible = false

        userLocationLayer = MapKitFactory.getInstance().createUserLocationLayer(binding.mapview.mapWindow)
        userLocationLayer.isVisible = true
        userLocationLayer.isHeadingEnabled = false
        userLocationLayer.setObjectListener(this)
//        Log.d("USER_LOCATION", userLocationLayer.cameraPosition()!!.target.toString())


    }

    override fun onStart() {
        super.onStart()
        activity?.let { viewModel.requestLocationPermission(requireContext(), it) }
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onObjectAdded(userLocationView: UserLocationView) {
//        userLocationLayer.setAnchor(
//            PointF(
//                (binding.mapview.width() * 0.5).toFloat(),
//                (binding.mapview.height() * 0.5).toFloat()
//            ),
//            PointF(
//                (binding.mapview.width() * 0.5).toFloat(),
//                (binding.mapview.height() * 0.83).toFloat()
//            )
//        )

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

//        picIcon.setIcon(
//            "pin",
//            ImageProvider.fromResource(
//                requireContext(),
//
//            )
//        )
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

            Log.d("CAMERA_POSITION_latitude", p1.target.latitude.toString())
            Log.d("CAMERA_POSITION_longitude", p1.target.longitude.toString())
            Log.d("CAMERA_POSITION_zoom", p1.zoom.toString())
            currentPosition = Point(p1.target.latitude, p1.target.longitude)

            Log.d("CAMERA_UPDATE_REASON", p2.toString())
        } else {
            Log.d("CAMERA_UPDATING", "YES")

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

}