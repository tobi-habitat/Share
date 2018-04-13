package de.markt.share.dto

data class AccountRegistration(
        val email:String,
        val username:String,
        val password:String,
        val passwordConfirm:String,
        val generalStandardTermsAndConditions:Boolean) {

    init {
        validateEmail()
        validateUsername()
        validatePassword()
        validateTerms()
    }

    private fun validateTerms() {
        check(generalStandardTermsAndConditions) {
            "terms are not accepted"
        }
    }

    private fun validatePassword() {

        val min = 6

        check(password.length >= min) {
            "password is too short. At least $min characters. length=${password.length}"
        }

        check(password == passwordConfirm) {
            "password are not equal"
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
            "password is not complex enough. Regex=$sb"
        }
    }

    private fun validateUsername() {
        val min = 4
        check(username.length >= min) {
            "username is too short. At least $min characters. username=${username} length=${username.length}"
        }

        val max = 100
        check(username.length <= max) {
            "username is too long. Max $max characters. username=${username} length=${username.length}"
        }

        val regexAllowCharacters = "^[\\w_-]+".toRegex()
        check(regexAllowCharacters.matches(username)) {
            "username has invalid characters. ${regexAllowCharacters.pattern} is allowed. Username=$username"
        }
    }

    private fun validateEmail() {

        val min = 6
        check(email.length >= min) {
            "email is too short. At least $min characters. email=${email} length=${email.length}"
        }

        val max = 150
        check(email.length <= max) {
            "email is too long. Maximum $max characters. email=${email} length=${email.length}"
        }

        val regexMail = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
                .toRegex()

        check(regexMail.matches(email)) {
            "email address does not equal to the General Email Regex (RFC 5322 Official Standard). email=${email}"
        }
    }
}