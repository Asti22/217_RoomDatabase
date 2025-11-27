package com.example.myroomsatu.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myroomsatu.AplikasiSiswa // Pastikan import ini menunjuk ke package yang benar

/**
 * Menyediakan Factory untuk kelas ViewModel apa pun yang membutuhkan RepositoriSiswa.
 */
object PenyediaViewModel {
    val Factory = viewModelFactory {
        // Factory untuk EntryViewModel
        initializer {
            EntryViewModel(
                // Memanggil repositoriSiswa yang disediakan oleh AppContainer
                repositoriSiswa = aplikasiSiswa().container.repositoriSiswa
            )
        }

        // Factory untuk HomeViewModel
        initializer {
            HomeViewModel(
                repositoriSiswa = aplikasiSiswa().container.repositoriSiswa
            )
        }
    }
}

/**
 * Fungsi ekstensi untuk mendapatkan instance Application Class dari CreationExtras.
 * Ini adalah cara yang direkomendasikan untuk mengakses Application context di ViewModelFactory.
 */
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[APPLICATION_KEY] as AplikasiSiswa)