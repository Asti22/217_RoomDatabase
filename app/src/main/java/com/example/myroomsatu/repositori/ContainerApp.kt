package com.example.myroomsatu.repositori

import android.content.Context
import com.example.myroomsatu.room.DatabaseSiswa // Asumsi DatabaseSiswa ada di package room

interface ContainerApp {
    val repositoriSiswa: RepositoriSiswa
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa( // Pastikan class ini sudah Anda buat
            siswaDao = DatabaseSiswa.getDatabase(context).siswaDao()
        )
    }
}