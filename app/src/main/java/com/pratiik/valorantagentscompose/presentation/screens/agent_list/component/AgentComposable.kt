package com.pratiik.valorantagentscompose.presentation.screens.agent_list.component

import androidx.annotation.Keep
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pratiik.valorantagentscompose.domain.model.AgentModel
@Keep
@Composable
fun AgentComposable(
    modifier: Modifier,
    agentModel: AgentModel,
    agentCardClicked: (AgentModel) -> Unit
) {

    val gradientColor = agentModel.backgroundGradientColor?.map {
        Color(android.graphics.Color.parseColor("#${it.dropLast(2)}"))
    }

    Box(
        modifier = modifier
            .background(brush = gradientColor!!.let {
                Brush.verticalGradient(
                    colors = it
                )
            })
            .fillMaxSize()
            .clickable {
                agentCardClicked(agentModel)
            }, contentAlignment = Alignment.Center
    ) {

        AsyncImage(
            model = agentModel.background,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3F),
            contentScale = ContentScale.Crop
        )

        AsyncImage(
            model = agentModel.fullPortrait,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }

}

@Preview
@Composable
fun PreviewAgentComposable() {
    AgentComposable(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.8F)
            .border(BorderStroke(1.dp, color = Color.White)), agentModel = AgentModel(
            "UUID",
            "Omen",
            "https://media.valorant-api.com/agents/5f8d3a7f-467b-97f3-062c-13acf203c006/fullportrait.png",
            backgroundGradientColor = listOf(
                "ff9c33ff", "b04621ff", "523a23ff", "44412eff"
            ),
            emptyList()
        )
    ){

    }
}