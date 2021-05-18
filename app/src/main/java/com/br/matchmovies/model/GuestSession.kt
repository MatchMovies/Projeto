package com.br.matchmovies.model

data class GuestSession(
    val expires_at: String,
    val guest_session_id: String,
    val success: Boolean
)