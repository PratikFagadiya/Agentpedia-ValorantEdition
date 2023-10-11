package com.pratiik.valorantagentscompose.presentation.screens.agent_list

import android.app.Activity
import androidx.annotation.Keep
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pratiik.valorantagentscompose.domain.model.AgentModel
import com.pratiik.valorantagentscompose.presentation.screens.agent_list.component.AgentComposable
import com.pratiik.valorantagentscompose.presentation.state_holder.AgentStates
import com.pratiik.valorantagentscompose.presentation.ui.theme.BackgroundNavyBlue
import com.pratiik.valorantagentscompose.presentation.ui.theme.BackgroundNavyBlue2
import com.pratiik.valorantagentscompose.presentation.ui.theme.Monserrat
import java.util.Locale
import kotlin.math.absoluteValue

@Keep
@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun AgentListScreen(agentState: State<AgentStates>, navigateToDetailScreen: (AgentModel) -> Unit) {

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = BackgroundNavyBlue.toArgb()
        }
    }

    val agentList = agentState.value
    val pagerState = rememberPagerState(initialPage = 1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundNavyBlue),
        verticalArrangement = Arrangement.Center
    ) {

        if (agentList.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        if (agentList.error.isNotBlank()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Data Not Found")
            }
        }

        if (agentList.data?.isNotEmpty() == true) {

            Text(
                text = ("Choose Your\nFavourite Agent").uppercase(Locale.getDefault()),
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                ),
                fontFamily = Monserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )

            agentList.data.let { agents ->
                Spacer(modifier = Modifier.height(30.dp))

                HorizontalPager(
                    pageCount = agents.size,
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 50.dp),
                    pageSpacing = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1F)
                ) { pageIndex ->

                    Card(modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {

                            val pageOffset =
                                ((pagerState.currentPage - pageIndex) + pagerState.currentPageOffsetFraction).absoluteValue

                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }

                            alpha = lerp(
                                start = 0.3F,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )

                        }) {
                        Box(
                            modifier = Modifier, contentAlignment = Alignment.Center
                        ) {
                            AgentComposable(
                                modifier = Modifier.fillMaxSize(), agentModel = agents[pageIndex]
                            ) {
                                navigateToDetailScreen(it)
                            }
                        }
                    }
                }

                AnimatedContent(
                    targetState = agents[pagerState.currentPage].displayName,
                    transitionSpec = {
                        fadeIn() with fadeOut()
                    },
                    label = "AgentDisplayName"
                ) {
                    it?.let { it1 ->
                        Text(
                            text = it1.uppercase(Locale.getDefault()),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp),
                            color = Color.White,
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                            ),
                            fontFamily = Monserrat,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                agents[pagerState.currentPage].abilities?.let {

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            alignment = Alignment.CenterHorizontally
                        )
                    ) {
                        agents[pagerState.currentPage].abilities?.let {
                            items(count = it.size) { abilityIndex ->

                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(it[abilityIndex].displayIcon).crossfade(true).build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(BackgroundNavyBlue2)
                                        .aspectRatio(1F)
                                        .padding(10.dp),
                                    contentScale = ContentScale.Inside
                                )

                            }
                        }

                    }
                }

            }
        }

    }

}

