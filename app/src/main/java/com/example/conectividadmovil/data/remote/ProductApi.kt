package com.example.conectividadmovil.data.remote

import com.example.conectividadmovil.data.remote.dto.ProductDto
import com.example.conectividadmovil.data.remote.dto.ProductRequest
import retrofit2.http.*

interface ProductApi {

    // Lista todos
    @GET("products")
    suspend fun listAll(): List<ProductDto>

    // Obtiene por id
    @GET("products/{id}")
    suspend fun getById(@Path("id") id: Long): ProductDto

    // Crea
    @POST("products")
    suspend fun create(@Body request: ProductRequest): ProductDto

    // Actualiza
    @PUT("products/{id}")
    suspend fun update(@Path("id") id: Long, @Body request: ProductRequest): ProductDto

    // Elimina
    @DELETE("products/{id}")
    suspend fun delete(@Path("id") id: Long)

    // Buscar
    @GET("products/search")
    suspend fun search(@Query("name") name: String): List<ProductDto>

}