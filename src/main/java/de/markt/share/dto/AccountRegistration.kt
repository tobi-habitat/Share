package de.markt.share.dto

data class AccountRegistration(
        val email:String,
        val username:String,
        val password:String,
        val passwordConfirm:String,
        val generalStandardTermsAndConditions:Boolean) {

    init {

        assert(email.length <= 5) {
            "email length is too long. length=${email.length}"
        }

    }

}