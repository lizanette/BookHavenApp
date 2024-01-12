package com.example.bookhavenapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListPriceX(
    val amountInMicros: Double,
    val currencyCode: String
): Parcelable
