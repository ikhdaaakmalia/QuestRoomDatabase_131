package com.ikhdaamel.p7.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikhdaamel.p7.data.entity.Mahasiswa
import com.ikhdaamel.p7.repository.RepositoryMhs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
//import kotlinx.coroutines.flow.internal.NopCollector.emit
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn


class HomeMhsViewModel (
    private val repositoryMhs: RepositoryMhs
): ViewModel() {
    val homeUiState: StateFlow<HomeUiState> = repositoryMhs.getAllMahasiswa()
    .filterNotNull()
        .map{
            HomeUiState(
                listMhs = it.toList(),
                isLoading = false,
            )
        }
        .onStart{
            emit(HomeUiState(isLoading = true))
            delay(900)
        }
        .catch{
            emit(
                HomeUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(
                isLoading = true,
            )
        )
}

data class HomeUiState (
    val listMhs: List<Mahasiswa> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)