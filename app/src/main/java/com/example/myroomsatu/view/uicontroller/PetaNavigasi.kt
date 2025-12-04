package com.example.myroomsatu.view.uicontroller

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// Import yang sudah ada
import com.example.myroomsatu.view.route.DestinasiHome

// --- IMPORT YANG DITAMBAHKAN UNTUK MEMPERBAIKI ERROR ---
import com.example.myroomsatu.view.route.DestinasiEntry // 1. DestinasiEntry
import com.example.myroomsatu.view.HomeScreen           // 2. HomeScreen
import com.example.myroomsatu.view.EntrySiswaScreen     // 3. EntrySiswaScreen

@Composable
fun SiswaApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        composable(route = DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(route = DestinasiEntry.route) },
                navigateToItemDetail = {
                    navController.navigate(route = "${DetailDataSiswa.route}/${it}")
                }
            )
        }

        composable(route = DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}