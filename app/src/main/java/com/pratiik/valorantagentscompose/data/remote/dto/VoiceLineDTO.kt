package com.pratiik.valorantagentscompose.data.remote.dto

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.data.remote.dto.MediaDTO
@Keep
data class VoiceLineDTO(
    val maxDuration: Double,
    val mediaList: List<MediaDTO>,
    val minDuration: Double
)