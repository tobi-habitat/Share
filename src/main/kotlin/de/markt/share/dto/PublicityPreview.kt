package de.markt.share.dto

import java.util.*

data class PublicityPreview(val uuid: String,
                            val image: ByteArray,
                            val title: String,
                            val text: String,
                            val tags: List<String>,
                            val date: Date,
                            val lat: Double,
                            val long: Double) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PublicityPreview

        if (uuid != other.uuid) return false
        if (!Arrays.equals(image, other.image)) return false
        if (title != other.title) return false
        if (text != other.text) return false
        if (tags != other.tags) return false
        if (date != other.date) return false
        if (lat != other.lat) return false
        if (long != other.long) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + Arrays.hashCode(image)
        result = 31 * result + title.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + tags.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + lat.hashCode()
        result = 31 * result + long.hashCode()
        return result
    }
}