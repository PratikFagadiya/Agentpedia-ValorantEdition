package com.pratiik.valorantagentscompose.data.di

import com.pratiik.valorantagentscompose.data.remote.BASE_URL
import com.pratiik.valorantagentscompose.data.remote.NetworkApiService
import com.pratiik.valorantagentscompose.data.repository.AgentRepositoryImpl
import com.pratiik.valorantagentscompose.domain.repository.AgentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DatModule {

    @Provides
    fun provideNetworkApiService(): NetworkApiService {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NetworkApiService::class.java)
    }

    @Provides
    fun provideAgentRepo(apiService: NetworkApiService): AgentRepository {
        return AgentRepositoryImpl(apiService)
    }

}