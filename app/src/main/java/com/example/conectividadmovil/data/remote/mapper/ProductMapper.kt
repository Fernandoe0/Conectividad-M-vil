package com.example.conectividadmovil.data.remote.mapper

import com.example.conectividadmovil.data.remote.dto.ProductDto
import com.example.conectividadmovil.domain.model.Product

// DTO -> Dominio
fun ProductDto.toDomain() = Product(
    id = id,
    name = name,
    price = price,
    category = category
)