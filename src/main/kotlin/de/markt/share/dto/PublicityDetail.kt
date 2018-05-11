package de.markt.share.dto

import java.util.*

data class PublicityDetail(
        val uuid: String,
        val image: ByteArray,
        val thumbnail: ByteArray,
        val lat: Double,
        val long: Double,
        val validFrom: Date,
        val validTo: Date,
        val title: String,
        val description: String,
        val tags: List<String>)