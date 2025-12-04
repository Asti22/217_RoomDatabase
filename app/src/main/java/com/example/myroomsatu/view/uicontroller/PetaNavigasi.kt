package com.example.myroomsatu.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myroomsatu.view.DetailSiswaScreen
import com.example.myroomsatu.view.EditSiswaScreen
import com.example.myroomsatu.view.EntrySiswaScreen
import com.example.myroomsatu.view.HomeScreen
import com.example.myroomsatu.view.route.DestinasiEntry
import com.example.myroomsatu.view.route.DestinasiDetailSiswa
import com.example.myroomsatu.view.route.DestinasiHome
import com.example.myroomsatu.view.route.DestinasiEditSiswa

@Composable
fun SiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {

        /* =======================
           HOME
        ======================== */
        composable(route = DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemDetail = { id ->
                    navController.navigate("${DestinasiDetailSiswa.route}/$id")
                }
            )

        }

        /* =======================
           ENTRY SISWA
        ======================== */
        composable(route = DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        /* =======================
           DETAIL SISWA
        ======================== */
        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailSiswa.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailSiswaScreen(
                navigateBack = { navController.popBackStack() },
                navigateToEditItem = { id ->
                    navController.navigate("${DestinasiEditSiswa.route}/$id")
                }
            )
        }

        /* =======================
           EDIT SISWA
        ======================== */
        composable(
            route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiEditSiswa.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )

        }
    }
}
