package com.example.conectividadmovil.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.conectividadmovil.data.remote.dto.ProductRequest
import com.example.conectividadmovil.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState

    init { loadAll() }

    fun loadAll() = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, message = null) }
        kotlin.runCatching { repository.listAll() }
            .onSuccess { products ->
                _uiState.update { it.copy(loading = false, items = products) }
            }
            .onFailure { e ->
                _uiState.update { it.copy(loading = false, message = e.localizedMessage ?: "Error") }
            }
    }

    fun searchByName(name: String) = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, message = null) }
        kotlin.runCatching { repository.search(name) }
            .onSuccess { products -> _uiState.update { it.copy(loading = false, items = products) } }
            .onFailure { e -> _uiState.update { it.copy(loading = false, message = e.localizedMessage) } }
    }

    fun create(request: ProductRequest) = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, message = null) }
        kotlin.runCatching { repository.create(request); repository.listAll() }
            .onSuccess { products -> _uiState.update { it.copy(loading = false, items = products) } }
            .onFailure { e -> _uiState.update { it.copy(loading = false, message = e.localizedMessage) } }
    }

    fun update(id: Long, request: ProductRequest) = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, message = null) }
        kotlin.runCatching { repository.update(id, request); repository.listAll() }
            .onSuccess { products -> _uiState.update { it.copy(loading = false, items = products) } }
            .onFailure { e -> _uiState.update { it.copy(loading = false, message = e.localizedMessage) } }
    }

    fun delete(id: Long) = viewModelScope.launch {
        _uiState.update { it.copy(loading = true, message = null) }
        kotlin.runCatching { repository.delete(id); repository.listAll() }
            .onSuccess { products -> _uiState.update { it.copy(loading = false, items = products) } }
            .onFailure { e -> _uiState.update { it.copy(loading = false, message = e.localizedMessage) } }
    }

    }