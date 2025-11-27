package com.example.myroomsatu.view.uicontroller

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myroomsatu.R
import com.example.myroomsatu.navigation.DestinasiHome
import com.example.myroomsatu.room.Siswa
import com.example.myroomsatu.view.viewmodel.HomeViewModel
import com.example.myroomsatu.view.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.TambahSiswa)
                )
            }
        }
    ) { innerPadding ->

        val uiStateSiswa by viewModel.homeUiState.collectAsState()

        BodyHome(
            itemSiswa = uiStateSiswa.listSiswa,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
fun BodyHome(
    itemSiswa: List<Siswa>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        if (itemSiswa.isEmpty()) {
            Text(
                text = stringResource(R.string.TidakAdaDataSiswa),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Tap tombol + untuk menambah data",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        } else {
            ListSiswa(itemSiswa = itemSiswa)
        }
    }
}

@Composable
fun ListSiswa(
    itemSiswa: List<Siswa>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = itemSiswa,
            key = { it.id }
        ) { siswa ->
            DataSiswa(
                siswa = siswa,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun DataSiswa(
    siswa: Siswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // Row: Nama dan Telepon
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = siswa.nama,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = siswa.telpon,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // Alamat
            Text(
                text = siswa.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
