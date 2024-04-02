package com.example.alica_app.ui.alumnis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ViewModelAlumnis : ViewModel(){
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val alumnis = arrayListOf(
        "Julien Martin",
        "Martin Julien",
        "3",
    )

    private val _alumnisList = MutableStateFlow(alumnis)
    val alumnisList = searchText
        .combine(_alumnisList) { text, alumnis ->
            alumnis.filter { alumni ->
                alumni.uppercase().contains(text.trim().uppercase())
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _alumnisList.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.tryEmit(text)
    }
}