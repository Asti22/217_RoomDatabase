package com.example.myroomsatu.model

import com.example.myroomsatu.data.Siswa

/** Data untuk form & detail */
data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

/** State untuk EntryViewModel */
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

/** Convert DetailSiswa -> Siswa (Room entity) */
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

/** Convert Siswa -> DetailSiswa */
fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

/** Convert Siswa -> UIStateSiswa */
fun Siswa.toUiStateSiswa(isEntryValid: Boolean): UIStateSiswa =
    UIStateSiswa(
        detailSiswa = DetailSiswa(
            id = id,
            nama = nama,
            alamat = alamat,
            telpon = telpon
        ),
        isEntryValid = isEntryValid
    )
