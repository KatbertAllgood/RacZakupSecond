package com.example.raczakupsecond.app

import android.app.Application
import com.example.data.repository.*
import com.example.domain.repository.*
import com.example.domain.utils.Constants
import com.yandex.mapkit.MapKitFactory
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins

class App : Application() {

    companion object {
        private lateinit var app: App
        private lateinit var networkRepository: NetworkRepository
        private lateinit var sharedPreferencesRepository: SharedPreferencesRepository
        private lateinit var networkLoaderRepository: NetworkLoaderRepository

        fun getNetworkRepository(): NetworkRepository {
            return networkRepository
        }

        fun getSharedPreferencesRepository(): SharedPreferencesRepository {
            return sharedPreferencesRepository
        }

        fun getNetworkLoaderRepository(): NetworkLoaderRepository {
            return networkLoaderRepository
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        val applicationContext = app.applicationContext
        networkRepository = NetworkRepositoryImpl()
        networkLoaderRepository = NetworkLoaderRepositoryImpl(applicationContext)
        sharedPreferencesRepository = SharedPreferencesRepositoryImpl(applicationContext)


        MapKitFactory.setApiKey(Constants.API_MAP_KEY)

        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
//                Log.d("RxJavaPlugins_ERROR_HANDLER", e.message.toString())
            } else {
                Thread.currentThread().also { thread ->
                    thread.uncaughtExceptionHandler?.uncaughtException(thread, e)
                }
            }
        }
    }
}