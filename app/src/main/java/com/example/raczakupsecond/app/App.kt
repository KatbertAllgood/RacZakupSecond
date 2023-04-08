package com.example.raczakupsecond.app

import android.app.Application
import com.example.data.repository.*
import com.example.domain.repository.*
import com.example.domain.utils.Constants
import com.yandex.mapkit.MapKitFactory

class App : Application() {

    companion object {
        private lateinit var app: App
        private lateinit var networkRepository: NetworkRepository
        private lateinit var sharedPreferencesRepository: SharedPreferencesRepository

        fun getNetworkRepository(): NetworkRepository {
            return networkRepository
        }

        fun getSharedPreferencesRepository(): SharedPreferencesRepository {
            return sharedPreferencesRepository
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        val applicationContext = app.applicationContext
        networkRepository = NetworkRepositoryImpl()
        sharedPreferencesRepository = SharedPreferencesRepositoryImpl(applicationContext)
        MapKitFactory.setApiKey(Constants.API_MAP_KEY)
    }
}