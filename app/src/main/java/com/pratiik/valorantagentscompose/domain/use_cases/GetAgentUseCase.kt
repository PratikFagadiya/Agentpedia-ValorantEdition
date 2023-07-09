package com.pratiik.valorantagentscompose.domain.use_cases

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.common.Resource
import com.pratiik.valorantagentscompose.domain.model.AgentModel
import com.pratiik.valorantagentscompose.domain.repository.AgentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
@Keep
class GetAgentUseCase @Inject constructor(private val agentRepository: AgentRepository) {

    operator fun invoke(isPlayableCharacter: Boolean): Flow<Resource<List<AgentModel>>> = flow {

        emit(Resource.Loading())

        try {
            emit(
                Resource.Success(data = agentRepository.getAllAgents(isPlayableCharacter))
            )
        } catch (e: Exception) {
            e.localizedMessage
            emit(Resource.Error(message = e.localizedMessage))
        }

    }

}