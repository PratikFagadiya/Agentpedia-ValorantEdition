package com.pratiik.valorantagentscompose.domain.repository

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.domain.model.AgentModel
import retrofit2.http.Query

@Keep
interface AgentRepository {

    /**
     * This function fetches all the agents and returns a List<AgentModel>
     * This interface is implemented in [AgentRepositoryImpl] under [data.repository] package
     */
    suspend fun getAllAgents(
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean
    ): List<AgentModel>

}