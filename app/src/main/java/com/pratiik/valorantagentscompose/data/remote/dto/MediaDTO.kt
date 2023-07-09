package com.pratiik.valorantagentscompose.data.remote.dto

import androidx.annotation.Keep

@Keep
data class MediaDTO(
    val id: Int,
    val wave: String,
    val wwise: String
)