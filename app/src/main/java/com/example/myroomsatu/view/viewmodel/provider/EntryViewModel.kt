package com.example.myroomsatu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myroomsatu.repositori.RepositoriSiswa
import com.example.myroomsatu.model.DetailSiswa
import com.example.myroomsatu.model.UIStateSiswa
import com.example.myroomsatu.model.toSiswa


// Asumsi: DetailSiswa, UIStateSiswa, dan fungsi ekstensi didefinisikan di file lain
// yang berada di package com.example.myroomsatu.viewmodel

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa) : ViewModel() {

    var uiStateSiswa by mutableStateOf(value = UIStateSiswa())
        private set

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUIState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(uiState = detailSiswa))
    }

    suspend fun saveSiswa() {
        if (validasiInput()) {
            // Memanggil fungsi ekstensi toSiswa() yang didefinisikan di luar ViewModel
            repositoriSiswa.insertSiswa(siswa = uiStateSiswa.detailSiswa.toSiswa())
        }
    }
}