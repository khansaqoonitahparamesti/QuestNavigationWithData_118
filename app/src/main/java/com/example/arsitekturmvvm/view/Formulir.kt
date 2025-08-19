package com.example.arsitekturmvvm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.arsitekturmvvm.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormIsian(pilihanJK: List<String>,
              onSubmitButtonClicked : (MutableList<String>) -> Unit,
              modifier: Modifier = Modifier
) {
    var txtNama by rememberSaveable { mutableStateOf(("")) }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.nama_lengkap), color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = colorResource(id = R.color.teal_700))
            )
        }
    ) { paddingValues ->
        IsiRuang(paddingValues, jenisK, selected) { newSelected ->
            selected = newSelected
        }
    }
}

@Composable
fun IsiRuang(
    paddingValues: PaddingValues,
    jenisK: List<String>,
    selected: String,
    onItemSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { },
            singleLine = true,
            modifier = Modifier
                .padding(top = 20.dp)
                .width(250.dp),
            label = { Text(text = "Nama Lengkap") }
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(20.dp)
                .width(250.dp),
            thickness = 1.dp,
            color = Color.Red
        )

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(250.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            jenisK.forEach { item ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selected == item,
                        onClick = { onItemSelected(item) }
                    )
                    Text(text = item)
                }
            }
        }

        OutlinedTextField(
            value = "",
            onValueChange = { },
            singleLine = true,
            modifier = Modifier
                .padding(top = 20.dp)
                .width(250.dp),
            label = { Text(text = "Alamat") }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Text(text = stringResource(id = R.string.submit))
        }
    }
}
