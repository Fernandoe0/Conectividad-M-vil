package com.example.conectividadmovil.domain.repository

import com.example.conectividadmovil.data.remote.dto.ProductRequest
import com.example.conectividadmovil.domain.model.Product
interface ProductRepository {
    suspend fun listAll(): List<Product>
    suspend fun search(name: String): List<Product>
    suspend fun create(request: ProductRequest): Product
    suspend fun update(id: Long, request: ProductRequest): Product
    suspend fun delete(id: Long)
}