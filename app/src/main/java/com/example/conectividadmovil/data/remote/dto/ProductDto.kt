package com.example.conectividadmovil.data.remote.dto

data class ProductDto(
    val id: Long?=null,
    val name: String="",
    val price: Double=0.0,
    val category: String="",
    val createdAt: String?=null
)
