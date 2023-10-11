package com.pratiik.valorantagentscompose.presentation.viewmodels

import androidx.annotation.Keep
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratiik.valorantagentscompose.common.Resource
import com.pratiik.valorantagentscompose.domain.use_cases.GetAgentUseCase
import com.pratiik.valorantagentscompose.presentation.state_holder.AgentStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@Keep
@HiltViewModel
class AgentViewModel
@Inject constructor(
    private val getAgentUseCase: GetAgentUseCase
) : ViewModel() {

    private val _allAgentList = mutableStateOf(AgentStates())
    val allAgentList: State<AgentStates> = _allAgentList

    init {
        getAllAgent()
    }

    /**
     * This function needs to be executed on MAIN thread
     * Because it is interacting with the UI state and can only be done from UI thread (MAIN)
     */
    private fun getAllAgent() = viewModelScope.launch {

        getAgentUseCase(true).collect {
            when (it) {
                is Resource.Loading -> {
                    _allAgentList.value = AgentStates(isLoading = true)
                }

                is Resource.Success -> {
                    delay(1000)
                    _allAgentList.value = AgentStates(data = it.data, isLoading = false)
                }

                is Resource.Error -> {
                    delay(1000)
                    _allAgentList.value =
                        AgentStates(error = it.message.toString(), isLoading = false)
                }
            }
        }


//        getAgentUseCase(true).onEach {
//            when (it) {
//                is Resource.Loading -> {
//                    _allAgentList.value = AgentStates(isLoading = true)
//                }
//
//                is Resource.Success -> {
//                    _allAgentList.value =
//                        AgentStates(data = it.data ?: emptyList(), isLoading = false)
//                }
//
//                is Resource.Error -> {
//                    _allAgentList.value =
//                        AgentStates(error = it.message.toString(), isLoading = false)
//                }
//            }
//        }.launchIn(viewModelScope)

    }

}