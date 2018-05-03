package de.markt.share.dto

import java.util.*

data class JwtAuthenticationRequest(val username: String, val password: String)
data class JwtAuthenticationResponse(val token: String)

data class JwtUser(val nickname: String, val username: String, val email: String, val enabled: Boolean, val authorities: List<Authority>, val lastPasswordReset: Date)
data class Authority(val authority: String)