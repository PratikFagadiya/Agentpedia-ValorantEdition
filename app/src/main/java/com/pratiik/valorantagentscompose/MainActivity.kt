package com.pratiik.valorantagentscompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.Keep
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pratiik.valorantagentscompose.domain.model.AgentModel
import com.pratiik.valorantagentscompose.presentation.Screen
import com.pratiik.valorantagentscompose.presentation.screens.agent_detail.AgentDetailScreen
import com.pratiik.valorantagentscompose.presentation.screens.agent_list.AgentListScreen
import com.pratiik.valorantagentscompose.presentation.ui.theme.BackgroundNavyBlue2
import com.pratiik.valorantagentscompose.presentation.viewmodels.AgentViewModel
import com.pratiik.valorantagentscompose.presentation.viewmodels.SplashViewModel
import com.pratiik.valorantagentscompose.presentation.ui.theme.ValorantJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@Keep
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
//        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

//        splashScreen.setKeepOnScreenCondition { splashViewModel.isSplashLoading.value }

        setContent {

            val agentViewModel = hiltViewModel<AgentViewModel>()

            ValorantJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.AgentListScreen.route
                    ) {

                        composable(Screen.AgentListScreen.route) {
                            AgentListScreen(agentViewModel.allAgentList, navigateToDetailScreen = {

                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "agent_detail", value = it
                                )

                                navController.navigate(Screen.AgentDetailScreen.route)
                            })
                        }

                        composable(Screen.AgentDetailScreen.route) {
                            val agentDetail =
                                navController.previousBackStackEntry?.savedStateHandle?.get<AgentModel>(
                                    "agent_detail"
                                )

                            var color = BackgroundNavyBlue2

                            agentDetail?.backgroundGradientColor?.let {
                                color = Color(
                                    android.graphics.Color.parseColor(
                                        "#${
                                            (agentDetail.backgroundGradientColor[agentDetail.backgroundGradientColor.size - 1].dropLast(
                                                2
                                            ))
                                        }"
                                    )
                                )
                            }

                            AgentDetailScreen(agentDetail, color)
                        }

                    }

                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ValorantJetpackComposeTheme {
        Greeting("Android")
    }
}

// Final
// https://dribbble.com/shots/14073476-Valorant-Agents/attachments/5693293?mode=media

// Mockups
// https://i.pinimg.com/originals/cf/f4/57/cff457be0cf8019d883f7fa08063cae3.png
// https://www.youtube.com/watch?v=xY5AZfMeRqg
// https://dribbble.com/shots/20776486-Valorant-agents-mobile-app
// https://dribbble.com/shots/14073476-Valorant-Agents
// https://www.uplabs.com/posts/valorant-app-ui-c014cf8d-e640-41f9-9f80-c5b366ccb8e2