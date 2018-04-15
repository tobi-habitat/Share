package de.markt.share.dto

data class AccountRegistration(
        val email:String,
        val username:String,
        val password:String,
        val passwordConfirm:String,
        val generalStandardTermsAndConditions:Boolean) {

    init {
        validateEmail(email)
        validateUsername(username)
        validatePassword(password, passwordConfirm)
        validateTerms(generalStandardTermsAndConditions)
    }

    companion object {
        @JvmStatic
        fun validateTerms(generalStandardTermsAndConditions:Boolean) {
            check(generalStandardTermsAndConditions) {
                "TERMS_NOT_ACCEPTED"
            }
        }

        @JvmStatic
        fun validatePassword(password: String, passwordConfirm: String) {

            val min = 6
            check(password.length >= min) {
                "PASSWORD_TOO_SHORT"
            }

            val max = 150
            check(password.length <= max) {
                "PASSWORD_TOO_LONG"
            }

            check(password == passwordConfirm) {
                "PASSWORD_NOT_EQUALS"
            }

            val sb = StringBuilder()
            sb.append("^")
            sb.append("(?=.*[0-9])")  // a digit must occur at least once
            sb.append("(?=.*[a-z])")  // a lower case letter must occur at least once
            sb.append("(?=.*[A-Z])")  // an upper case letter must occur at least once
            sb.append("(?=.*[!@#$%^&+=])")  // a special character must occur at least once
            sb.append("(?=\\S+$)")  // no whitespace allowed in the entire string
            sb.append(".{$min,}")  // anything, at least eight places though
            sb.append("$")

            val regex = sb.toString().toRegex()

            check(regex.matches(password)) {
                "PASSWORD_NOT_COMPLEX"
            }
        }

        @JvmStatic
        fun validateUsername(username: String) {
            val min = 4
            check(username.length >= min) {
                "USERNAME_TOO_SHORT"
            }

            val max = 100
            check(username.length <= max) {
                "USERNAME_TOO_LONG"
            }

            val regexAllowCharacters = "^[\\w_-]+".toRegex()
            check(regexAllowCharacters.matches(username)) {
                "USERNAME_ILLEGAL"
            }
        }

        @JvmStatic
        fun validateEmail(email: String) {

            val min = 6
            check(email.length >= min) {
                "EMAIL_TOO_SHORT"
            }

            val max = 150
            check(email.length <= max) {
                "EMAIL_TOO_LONG"
            }

            val regexMail = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
                    .toRegex()

            check(regexMail.matches(email)) {
                "EMAIL_RFC_5322"
            }
        }
    }
}