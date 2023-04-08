package com.example.raczakupsecond.screens.profile.address

import android.Manifest
import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.example.domain.utils.Constants
import com.example.raczakupsecond.R
import com.example.raczakupsecond.databinding.FragmentAddressBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapEvent
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManager
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import kotlinx.coroutines.*
import java.util.*

class AddressFragment : Fragment(R.layout.fragment_address),
    UserLocationObjectListener,
    CameraListener
{
    private lateinit var binding : FragmentAddressBinding
    private val viewModel : AddressFragmentVM by viewModels()

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
                isShowed = true

            } else if (isShowed) {

                binding.ivAddressfragmentButtonShowOrHide.setImageResource(R.drawable.ic_button_button_show_cl_addressfragment)

                binding.clAddressFragmentMapBottom.visibility = View.GONE

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

                isShowed = false

            }
        }

        binding.clAddressFragmentMapBottom.setOnClickListener {
            Log.d("CL_CLICK", "TRUE")
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

    override fun onObjectAdded(p0: UserLocationView) {

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
            Log.d("CAMERA_POSITION_latitude", p1.target.latitude.toString())
            Log.d("CAMERA_POSITION_longitude", p1.target.longitude.toString())
            Log.d("CAMERA_UPDATE_REASON", p2.toString())
        }
    }

//    private fun cameraUserPosition() {
//        if (userLocationLayer.cameraPosition() != null) {
//            routeStartLocation = userLocationLayer.cameraPosition()!!.target
//            binding.mapview.map.move(
//                CameraPosition(routeStartLocation, 17.0f, 0.0f, 0.0f),
//                Animation(Animation.Type.SMOOTH, 2F),
//                null
//            )
//        } else {
//            binding.mapview.map.move(
//                CameraPosition(Point(55.742651, 37.612730), 17.0f, 0.0f, 0.0f),
//                Animation(Animation.Type.SMOOTH, 2F),
//                null
//            )
//        }
//    }

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
                    val currentLocation = Point(location.latitude, location.longitude)
                    Log.d("CURRENT_LOCATION", "${location.latitude}, ${location.longitude}")
                    binding.mapview.map.move(
                        CameraPosition(currentLocation, 17.0f, 0.0f, 0.0f),
                        Animation(Animation.Type.SMOOTH, 2F),
                        null
                    )
                }
            }
    }

}
