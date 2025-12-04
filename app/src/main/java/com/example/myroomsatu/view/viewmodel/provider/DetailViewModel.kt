package com.example.myroomsatu.view.viewmodel.provider

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroomsatu.model.DetailSiswa
import com.example.myroomsatu.model.toDetailSiswa
import com.example.myroomsatu.model.toSiswa
import com.example.myroomsatu.repositori.RepositoriSiswa
import com.example.myroomsatu.view.route.DestinasiDetailSiswa
import kotlinx.coroutines.flow.*

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdArg])

    val uiDetailState: StateFlow<DetailSiswaUiState> =
        repositoriSiswa.getSiswaStream(idSiswa)
            .filterNotNull()
            .map { siswa ->
                DetailSiswaUiState(detailSiswa = siswa.toDetailSiswa())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailSiswaUiState()
            )

    suspend fun deleteSiswa() {
        repositoriSiswa.deleteSiswa(
            uiDetailState.value.detailSiswa.toSiswa()
        )
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class DetailSiswaUiState(
    val detailSiswa: DetailSiswa = DetailSiswa()
)
