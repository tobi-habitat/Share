package de.markt.share.dto

data class JwtAuthenticationRequest(val email: String, val password: String)
data class JwtAuthenticationResponse(val token: String)
