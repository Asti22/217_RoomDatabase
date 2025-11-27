package com.example.myroomsatu // PACKAGE UTAMA!

import android.app.Application
// Import container dari package repositori
import com.example.myroomsatu.repositori.ContainerApp
import com.example.myroomsatu.repositori.ContainerDataApp

class AplikasiSiswa : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        // Panggil ContainerDataApp yang sekarang berada di package repositori
        container = ContainerDataApp(this)
    }
}