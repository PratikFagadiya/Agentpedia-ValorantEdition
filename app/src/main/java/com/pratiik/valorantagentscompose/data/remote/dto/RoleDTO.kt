package com.pratiik.valorantagentscompose.data.remote.dto

import androidx.annotation.Keep

@Keep
data class RoleDTO(
    val assetPath: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val uuid: String
)