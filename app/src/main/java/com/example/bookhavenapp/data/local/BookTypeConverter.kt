package com.example.bookhavenapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.bookhavenapp.domain.model.AccessInfo
import com.example.bookhavenapp.domain.model.Epub
import com.example.bookhavenapp.domain.model.ImageLinks
import com.example.bookhavenapp.domain.model.IndustryIdentifier
import com.example.bookhavenapp.domain.model.ListPrice
import com.example.bookhavenapp.domain.model.ListPriceX
import com.example.bookhavenapp.domain.model.Offer
import com.example.bookhavenapp.domain.model.PanelizationSummary
import com.example.bookhavenapp.domain.model.Pdf
import com.example.bookhavenapp.domain.model.ReadingModes
import com.example.bookhavenapp.domain.model.RetailPrice
import com.example.bookhavenapp.domain.model.RetailPriceX
import com.example.bookhavenapp.domain.model.SaleInfo
import com.example.bookhavenapp.domain.model.SearchInfo
import com.example.bookhavenapp.domain.model.VolumeInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class BookTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromAccessInfoToString(accessInfo: AccessInfo): String {
        println("Converting AccessInfo to String:")
        val jsonString = gson.toJson(accessInfo)
        println("Result: $jsonString")
        return jsonString
    }

    @TypeConverter
    fun fromStringToAccessInfo(json: String): AccessInfo {
        val type = object : TypeToken<AccessInfo>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromEpubToString(epub: Epub): String {
        return gson.toJson(epub)
    }

    @TypeConverter
    fun fromStringToEpub(json: String): Epub {
        val type = object : TypeToken<Epub>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromPdfToString(pdf: Pdf): String {
        return gson.toJson(pdf)
    }

    @TypeConverter
    fun fromStringToPdf(json: String): Pdf {
        val type = object : TypeToken<Pdf>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromImageLinksToString(imageLinks: ImageLinks): String {
        return gson.toJson(imageLinks)
    }

    @TypeConverter
    fun fromStringToImageLinks(json: String): ImageLinks {
        val type = object : TypeToken<ImageLinks>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromSaleInfoToString(saleInfo: SaleInfo): String {
        return gson.toJson(saleInfo)
    }

    @TypeConverter
    fun fromStringToSaleInfo(json: String): SaleInfo {
        val type = object : TypeToken<SaleInfo>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPriceToString(listPrice: ListPrice): String {
        return gson.toJson(listPrice)
    }

    @TypeConverter
    fun fromStringToListPrice(json: String): ListPrice {
        val type = object : TypeToken<ListPrice>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPriceXToString(listPriceX: ListPriceX): String {
        return gson.toJson(listPriceX)
    }

    @TypeConverter
    fun fromStringToListPriceX(json: String): ListPriceX {
        val type = object : TypeToken<ListPriceX>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromOfferToString(offer: Offer): String {
        return gson.toJson(offer)
    }

    @TypeConverter
    fun fromStringToOffer(json: String): Offer {
        val type = object : TypeToken<Offer>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromIndustryIdentifierToString(industryIdentifier: IndustryIdentifier): String {
        return gson.toJson(industryIdentifier)
    }

    @TypeConverter
    fun fromStringToIndustryIdentifier(json: String): IndustryIdentifier {
        val type = object : TypeToken<IndustryIdentifier>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromPanelizationSummaryToString(panelizationSummary: PanelizationSummary): String {
        return gson.toJson(panelizationSummary)
    }

    @TypeConverter
    fun fromStringToPanelizationSummary(json: String): PanelizationSummary {
        val type = object : TypeToken<PanelizationSummary>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromReadingModesToString(readingModes: ReadingModes): String {
        return gson.toJson(readingModes)
    }

    @TypeConverter
    fun fromStringToReadingModes(json: String): ReadingModes {
        val type = object : TypeToken<ReadingModes>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromRetailPriceToString(retailPrice: RetailPrice): String {
        return gson.toJson(retailPrice)
    }

    @TypeConverter
    fun fromStringToRetailPrice(json: String): RetailPrice {
        val type = object : TypeToken<RetailPrice>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromRetailPriceXToString(retailPriceX: RetailPriceX): String {
        return gson.toJson(retailPriceX)
    }

    @TypeConverter
    fun fromStringToRetailPriceX(json: String): RetailPriceX {
        val type = object : TypeToken<RetailPriceX>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromSearchInfoToString(searchInfo: SearchInfo): String {
        return gson.toJson(searchInfo)
    }

    @TypeConverter
    fun fromStringToSearchInfo(json: String): SearchInfo {
        val type = object : TypeToken<SearchInfo>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromVolumeInfoToString(volumeInfo: VolumeInfo): String {
        return gson.toJson(volumeInfo)
    }

    @TypeConverter
    fun fromStringToVolumeInfo(json: String): VolumeInfo {
        val type = object : TypeToken<VolumeInfo>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListOfStringsToString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToListOfStrings(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}
