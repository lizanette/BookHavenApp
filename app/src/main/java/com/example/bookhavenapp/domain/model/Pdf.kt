package com.example.bookhavenapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pdf(
    val acsTokenLink: String,
    val isAvailable: Boolean
): Parcelable
