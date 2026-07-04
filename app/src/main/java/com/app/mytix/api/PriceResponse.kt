package com.app.mytix.api

data class PriceResponse(
    val concerts: List<ConcertPrice>
)

data class ConcertPrice(
    val name: String,
    val price: String
)