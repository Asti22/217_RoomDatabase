package com.example.myroomsatu.view.viewmodel.provider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroomsatu.repositori.RepositoriSiswa
import com.example.myroomsatu.model.DetailSiswa
import com.example.myroomsatu.model.UIStateSiswa
import com.example.myroomsatu.model.toSiswa
import com.example.myroomsatu.model.toUiStateSiswa
import com.example.myroomsatu.view.route.DestinasiDetailSiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa,
) : ViewModel() {

    var uiState by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int = checkNotNull(
        savedStateHandle[DestinasiDetailSiswa.itemIdArg]
    )

    init {
        viewModelScope.launch {
            uiState = repositoriSiswa.getSiswaStream(idSiswa)
                .filterNotNull()
                .first()
                .toUiStateSiswa(isEntryValid = true)
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiState = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    private fun validasiInput(
        detail: DetailSiswa = uiState.detailSiswa
    ): Boolean {
        return with(detail) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun updateSiswa() {
        if (validasiInput(uiState.detailSiswa)) {
            repositoriSiswa.updateSiswa(uiState.detailSiswa.toSiswa())
        }
    }
}
