package com.app.mytix.model

data class Concert(
    val id: Int,
    val name: String,
    val description: String,
    val location: String,
    val date: String,
    val imageResId: Int
)