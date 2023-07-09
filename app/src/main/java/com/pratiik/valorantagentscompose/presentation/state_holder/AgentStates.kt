package com.pratiik.valorantagentscompose.presentation.state_holder

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.domain.model.AgentModel
@Keep
data class AgentStates(
    val isLoading: Boolean = true, val data: List<AgentModel>? = null, val error: String = ""
)