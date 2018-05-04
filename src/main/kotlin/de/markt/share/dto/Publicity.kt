package de.markt.share.dto

import java.text.SimpleDateFormat
import java.util.*

class PublicityPreview(val thumbnail: Byte, val title: String, val text: String, val tags: List<String>, val date: Date, val lat: Float, val long: Float)

class PublicityInsert(
        val image: ByteArray,
        val thumbnail: ByteArray,
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

            check(nowWithoutTime.before(validFrom)) {
                "VALID_FROM_LESS_CURRENT_TIME"
            }

            val diff = validFrom.time - validTo.time
            check (diff >= 0) {
                "VALID_TO_SMALLER_THAN_VALID_FROM"
            }

            val days = diff / (1000 * 60 * 60 * 24)
            check(days >= 0 && days <= 30) {
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

            tags.forEach({
                val regex = "^[\\w_-]+".toRegex()
                check(regex.matches(it)) {
                    "INVALID_TAG"
                }
            })
        }
    }
}