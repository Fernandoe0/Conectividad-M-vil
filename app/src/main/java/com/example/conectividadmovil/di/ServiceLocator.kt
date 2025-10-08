package com.example.conectividadmovil.di

import com.example.conectividadmovil.data.remote.ProductApi
import com.example.conectividadmovil.data.repository.ProductRepositoryImpl
import com.example.conectividadmovil.domain.repository.ProductRepository
import com.example.conectividadmovil.data.remote.RetrofitInstance

object ServiceLocator {
    val api: ProductApi by lazy { RetrofitInstance.api }
    val repository: ProductRepository by lazy { ProductRepositoryImpl(api) }
}