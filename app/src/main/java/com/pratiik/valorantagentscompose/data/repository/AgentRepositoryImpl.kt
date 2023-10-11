package com.pratiik.valorantagentscompose.data.repository

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.data.remote.NetworkApiService
import com.pratiik.valorantagentscompose.data.remote.dto.toDomainModel
import com.pratiik.valorantagentscompose.domain.model.AgentModel
import com.pratiik.valorantagentscompose.domain.repository.AgentRepository
import javax.inject.Inject

/**
 * This class and its methods are responsible for actually doing the network calls
 * and fetching the List of [AgentModel::class]
 *
 * @param apiService is used to call the GET method of retrofit which sends an HTTP GET request
 */
@Keep
class AgentRepositoryImpl @Inject constructor(
    private val apiService: NetworkApiService
) : AgentRepository {

    override suspend fun getAllAgents(isPlayableCharacter: Boolean): List<AgentModel> {
        try {
            return apiService.getAllAgents(isPlayableCharacter).data.map {
                it.toDomainModel()
            }
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}