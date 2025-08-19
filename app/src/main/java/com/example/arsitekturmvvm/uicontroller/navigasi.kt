package com.example.arsitekturmvvm

import androidx.annotation.experimental.Experimental
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.arsitekturmvvm.view.TampilData
import com.example.arsitekturmvvm.view.FormIsian
import com.example.arsitekturmvvm.viewmodel.SiswaViewModel
import com.example.arsitekturmvvm.model.dataJK.JenisK

enum class Navigasi {
    Formulir,
    Detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataApp(
    viewModel: SiswaViewModel= viewModel (),
    navController: NavHostController = rememberNavController()
) {
    Scaffold { isiRuang ->
        val uiState = viewModel.statusUI.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,
            modifier = Modifier.padding(isiRuang)
        ) {
            composable(route = Navigasi.Formulir.name) {
                val konteks = LocalContext.current
                FormIsian(
                    pilihanJK = JenisK.map { id -> konteks.resources. getString (id) },
                    onSubmitButtonClicked = {
                        viewModel .setSiswa(it)
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }
            composable(route = Navigasi.Detail.name) {
                TampilData(
                    statusUISiswa= uiState.value,
                    onBackBtnClick = { cancelAndBackToFormulir(navController) } )
            }
        }
    }
}

private fun cancelAndBackToFormulir(navController: NavHostController) {
    navController.popBackStack(Navigasi.Formulir.name, inclusive = false)
}