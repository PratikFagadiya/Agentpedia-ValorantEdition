package com.pratiik.valorantagentscompose.data.remote.dto

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.data.remote.dto.AgentDTO
@Keep
data class AgentsDTO(
    val `data`: List<AgentDTO>,
    val status: Int
)