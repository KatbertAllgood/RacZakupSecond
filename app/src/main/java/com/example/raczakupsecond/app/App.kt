package com.example.raczakupsecond.app

import android.app.Application
import com.example.data.repository.*
import com.example.domain.repository.*

class App : Application() {

    companion object {
        private lateinit var app: App
        private lateinit var networkRepository: NetworkRepository

        fun getNetworkRepository(): NetworkRepository = networkRepository
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        networkRepository = NetworkRepositoryImpl()
    }
}