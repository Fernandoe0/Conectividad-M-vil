package com.example.conectividadmovil.domain.model

data class Product(
    val id: Long?=null,
    val name: String,
    val price: Double,
    val category: String?,
    val createdAt: String?=null
)
