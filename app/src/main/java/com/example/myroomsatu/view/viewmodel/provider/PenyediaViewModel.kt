package com.example.myroomsatu.viewmodel
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myroomsatu.AplikasiSiswa
import com.example.myroomsatu.view.viewmodel.provider.HomeViewModel
import com.example.myroomsatu.view.viewmodel.provider.DetailViewModel
import com.example.myroomsatu.view.viewmodel.provider.EditViewModel
import com.example.myroomsatu.view.viewmodel.provider.EntryViewModel


object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            EntryViewModel(aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            DetailViewModel(this.createSavedStateHandle(), aplikasiSiswa().container.repositoriSiswa)
        }

        initializer {
            EditViewModel(this.createSavedStateHandle(), aplikasiSiswa().container.repositoriSiswa)
        }
    }

    /**
     * Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah instance dari
     * [AplikasiSiswa].
     */
    fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)
}