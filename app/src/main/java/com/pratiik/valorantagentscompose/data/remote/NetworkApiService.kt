package com.pratiik.valorantagentscompose.data.remote

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.data.remote.dto.AgentsDTO
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://valorant-api.com/v1/"

@Keep
interface NetworkApiService {

//    https://valorant-api.com/v1/agents?isPlayableCharacter=true

    @GET("agents")
    suspend fun getAllAgents(@Query("isPlayableCharacter") isPlayableCharacter: Boolean): AgentsDTO

}