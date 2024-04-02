package com.example.alica_app.ui.alumnis

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun Alumnis(navController: NavController) {
    val viewModel = ViewModelAlumnis()
    val alumnisList by viewModel.alumnisList.collectAsState()

    Column{
        SearchBarSample()
        LazyColumn {
            items(alumnisList) { alumni ->
                AlumniCard(alumni)
            }
        }
    }
}

@Composable
fun AlumniCard(alumnis: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Absolute.Left) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()) {
            Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center ){
                Column {
                    Icon(Icons.Outlined.AccountCircle, contentDescription = "Profil icon")
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column {
                    Text(text = alumnis)
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column (horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()){
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Voir le profil")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchBarSample() {
    val viewModel: ViewModelAlumnis = viewModel()
    val searchText by viewModel.searchText.collectAsState()
    var isSearching by remember {
        mutableStateOf(false)
    }
    val alumnisList by viewModel.alumnisList.collectAsState()

    SearchBar(
        query = searchText,
        onQueryChange = viewModel::onSearchTextChange,
        onSearch = viewModel::onSearchTextChange,
        active = isSearching,
        onActiveChange = { isSearching = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(alumnisList) { alumni ->
                AlumniCard(alumni)
            }
        }
    }
}