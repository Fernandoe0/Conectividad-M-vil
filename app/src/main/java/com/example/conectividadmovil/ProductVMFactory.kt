package com.example.conectividadmovil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conectividadmovil.domain.repository.ProductRepository
import com.example.conectividadmovil.ui.product.ProductViewModel

class ProductVMFactory (
    private val repository: ProductRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                return ProductViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
}
