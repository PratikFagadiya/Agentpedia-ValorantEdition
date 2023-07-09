package com.pratiik.valorantagentscompose.domain.repository

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.domain.model.AgentModel
import retrofit2.http.Query
@Keep
interface AgentRepository {

    suspend fun getAllAgents(@Query("isPlayableCharacter") isPlayableCharacter: Boolean): List<AgentModel>

}