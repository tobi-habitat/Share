package de.markt.share.dto

data class JwtAuthenticationRequest(val username: String, val password: String)
data class JwtAuthenticationResponse(val token: String)

data class JwtUser(val username: String, val email: String, val enabled: Boolean, val authorities: List<Authority>, val lastPasswordReset: Long)
data class Authority(val authority: String)