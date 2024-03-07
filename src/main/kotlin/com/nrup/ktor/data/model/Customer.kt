package com.nrup.ktor.data.model

import kotlinx.serialization.Serializable

// Customer Modle
@Serializable
data class Customer(
    val id:String,
    val firstName:String,
    val lastName:String,
    val email:String
)

// in-memory storage for customers
val customerStorage = mutableListOf<Customer>()