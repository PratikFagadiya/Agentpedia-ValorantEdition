package com.pratiik.valorantagentscompose.presentation

import androidx.annotation.Keep

@Keep
sealed class Screen(val route: String) {

    object AgentListScreen : Screen("agent_list_screen")
    object AgentDetailScreen : Screen("agent_detail_screen")

}