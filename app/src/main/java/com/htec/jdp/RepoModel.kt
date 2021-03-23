package com.htec.jdp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepoModel(
    val name: String,
    val description: String?,
    @Json(name = "private") val isPrivate: Boolean
)