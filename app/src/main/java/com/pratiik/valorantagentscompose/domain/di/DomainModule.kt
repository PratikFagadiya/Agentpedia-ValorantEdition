package com.pratiik.valorantagentscompose.domain.di

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.domain.repository.AgentRepository
import com.pratiik.valorantagentscompose.domain.use_cases.GetAgentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@Keep
@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetAgentUseCase(agentRepository: AgentRepository): GetAgentUseCase {
        return GetAgentUseCase(agentRepository)
    }

}