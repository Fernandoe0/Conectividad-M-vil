package com.example.conectividadmovil.ui.product

import android.os.Message
import com.example.conectividadmovil.domain.model.Product

data class ProductUiState(
    val loading: Boolean = false,
    val items: List<Product> = emptyList(),
    val message: String? = null
)
