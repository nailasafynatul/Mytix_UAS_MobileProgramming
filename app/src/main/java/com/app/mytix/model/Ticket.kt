package com.app.mytix

data class Ticket(
    val id: Int = 0,
    val image: Int,
    val concert: String,
    val category: String,
    val date: String,
    val price: String,
    val paymentMethod: String,
    val status: String,
    val checkoutTime: Long
)