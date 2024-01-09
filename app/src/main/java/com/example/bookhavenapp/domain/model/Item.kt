package com.example.bookhavenapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity()
data class Item(
    val accessInfo: AccessInfo,
    val etag: String,
    @PrimaryKey val id: String,
    val kind: String,
    val saleInfo: SaleInfo,
    val searchInfo: SearchInfo?,
    val selfLink: String,
    val volumeInfo: VolumeInfo
): Parcelable
