package de.markt.share.dto

import java.util.*

data class PublicityDetail(
        val uuid: String,
        val image: ByteArray,
        val lat: Double,
        val long: Double,
        val validFrom: Date,
        val validTo: Date,
        val title: String,
        val description: String,
        val tags: List<String>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PublicityDetail

        if (uuid != other.uuid) return false
        if (!Arrays.equals(image, other.image)) return false
        if (lat != other.lat) return false
        if (long != other.long) return false
        if (validFrom != other.validFrom) return false
        if (validTo != other.validTo) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (tags != other.tags) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + Arrays.hashCode(image)
        result = 31 * result + lat.hashCode()
        result = 31 * result + long.hashCode()
        result = 31 * result + validFrom.hashCode()
        result = 31 * result + validTo.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + tags.hashCode()
        return result
    }
}