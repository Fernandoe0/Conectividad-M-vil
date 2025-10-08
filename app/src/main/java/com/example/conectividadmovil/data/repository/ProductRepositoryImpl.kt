package com.example.conectividadmovil.data.repository


import com.example.conectividadmovil.data.remote.ProductApi
import com.example.conectividadmovil.data.remote.dto.ProductRequest
import com.example.conectividadmovil.data.remote.mapper.toDomain
import com.example.conectividadmovil.domain.model.Product
import com.example.conectividadmovil.domain.repository.ProductRepository

class ProductRepositoryImpl(private val api: ProductApi) : ProductRepository {
    override suspend fun listAll() = api.listAll().map { it.toDomain() }
    override suspend fun search(name: String) = api.search(name).map { it.toDomain() }
    override suspend fun create(request: ProductRequest) = api.create(request).toDomain()
    override suspend fun update(id: Long, request: ProductRequest) = api.update(id, request).toDomain()
    override suspend fun delete(id: Long) { api.delete(id) }
    suspend fun getById(id: Long) = api.getById(id).toDomain() // si lo usas
}