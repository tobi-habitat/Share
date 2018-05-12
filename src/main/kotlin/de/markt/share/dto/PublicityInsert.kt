package de.markt.share.dto

import java.text.SimpleDateFormat
import java.util.*

data class PublicityInsert(
        val image: ByteArray,
        val lat: Double,
        val long: Double,
        val validFrom: Date,
        val validTo: Date,
        val title: String,
        val description: String,
        val tags: List<String>) {

    init {
        validLatitudeAndLongitude(lat, long)
        validDates(validFrom, validTo)
        validTitle(title)
        validDescription(description)
        validTags(tags)
    }

    companion object {

        @JvmStatic
        fun validLatitudeAndLongitude(latitude: Double, longitude: Double) {

            check(latitude.isFinite()) {
                "INVALID_LATITUDE"
            }

            check(longitude.isFinite()) {
                "INVALID_LONGITUDE"
            }

            check(latitude <= 90 && latitude >= -90) {
                "INVALID_LATITUDE"
            }

            check(longitude <= 180 && longitude >= -180) {
                "INVALID_LONGITUDE"
            }
        }

        @JvmStatic
        fun validDates(validFrom: Date, validTo: Date) {

            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val nowWithoutTime = sdf.parse(sdf.format(Date()))

            val nowDiff = validFrom.time - nowWithoutTime.time
            check(nowDiff >= 0) {
                "VALID_FROM_LESS_CURRENT_TIME"
            }

            val diff = validTo.time - validFrom.time
            check (diff >= 0) {
                "VALID_TO_SMALLER_THAN_VALID_FROM"
            }

            val days = diff / (1000 * 60 * 60 * 24)
            check(days in 0..30) {
                "VALID_FROM_VALID_TO_DIFFERENCE_TOO_BIG"
            }
        }

        @JvmStatic
        fun validTitle(title: String) {
            val min = 4
            check(title.length >= min) {
                "TITLE_TOO_SHORT"
            }

            val max = 100
            check(title.length <= max) {
                "TITLE_TOO_LONG"
            }
        }

        @JvmStatic
        fun validDescription(description: String) {
            val min = 20
            check(description.length >= min) {
                "DESCRIPTION_TOO_SHORT"
            }

            val max = 1000
            check(description.length <= max) {
                "DESCRIPTION_TOO_LONG"
            }
        }

        @JvmStatic
        fun validTags(tags: List<String>) {
            check(!tags.isEmpty()) {
                "NO_TAGS"
            }

            check(tags.size <= 5) {
                "TOO_MANY_TAGS"
            }

            tags.forEach({validTag(it)})
        }

        @JvmStatic
        fun validTag(tag: String) {

            val min = 3
            check(tag.length >= min) {
                "TAG_TOO_SHORT"
            }

            val max = 20
            check(tag.length <= max) {
                "TAG_TOO_LONG"
            }


            val regex = "^[\\w_-]+".toRegex()
            check(regex.matches(tag)) {
                "INVALID_TAG"
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PublicityInsert

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
        var result = Arrays.hashCode(image)
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