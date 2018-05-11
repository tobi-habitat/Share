package de.markt.share.dto

import java.util.*

data class PublicityPreview(val thumbnail: ByteArray,
                            val title: String,
                            val text: String,
                            val tags: List<String>,
                            val date: Date,
                            val lat: Double,
                            val long: Double)