package com.example.raczakupsecond.screens.profile.address

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel

class AddressFragmentVM : ViewModel() {

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
