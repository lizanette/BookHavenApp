package com.example.bookhavenapp.domain.model

data class Offer(
    val finskyOfferType: Int,
    val giftable: Boolean,
    val listPrice: ListPriceX,
    val retailPrice: RetailPrice
)
