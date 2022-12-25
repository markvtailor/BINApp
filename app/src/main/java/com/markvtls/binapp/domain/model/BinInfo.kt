package com.markvtls.binapp.domain.model

import com.markvtls.binapp.data.dto.BinResponseDto
import java.util.*

data class BinInfo (
    val length: String,
    val luhn: String,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: String,
    val numeric: String,
    val alpha2: String,
    val countryName: String,
    val emoji: String,
    val currency: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val url: String,
    val phone: String,
    val city: String )

fun BinResponseDto.toDomain(): BinInfo {
    val length: String = if (this.number == null) "?" else this.number.length.toString()
    val luhn = if (this.number == null) "?" else if (this.number.luhn == true) "Yes" else "No"
    val scheme = this.scheme ?: "?"
    val type = this.type ?: "?"
    val brand = this.brand ?: "?"
    val prepaid = if (this.prepaid == null) "?" else if (this.prepaid) "Yes" else "No"
    val numeric = this.country?.numeric ?: "?"
    val alpha2 = this.country?.alpha2 ?: "?"
    val countryName = this.country?.name ?: "?"
    val emoji = this.country?.emoji ?: "?"
    val currency = this.country?.currency ?: "?"
    val latitude = if (this.country == null) "?" else this.country.latitude.toString()
    val longitude = if (this.country == null) "?" else this.country.longitude.toString()
    val name = this.bank?.name ?: "?"
    val url = this.bank?.url ?: "?"
    val phone = this.bank?.phone ?: "?"
    val city = this.bank?.city ?: "?"

    return BinInfo(
        length,
        luhn,
        scheme,
        type,
        brand,
        prepaid,
        numeric,
        alpha2,
        countryName,
        emoji,
        currency,
        latitude,
        longitude,
        name,
        url,
        phone,
        city
    )
}