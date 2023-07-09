package com.pratiik.valorantagentscompose.presentation.screens.agent_detail

import android.app.Activity
import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.pratiik.valorantagentscompose.domain.model.AgentModel
import com.pratiik.valorantagentscompose.presentation.screens.agent_detail.component.AgentAbilityComposable
import com.pratiik.valorantagentscompose.presentation.ui.theme.BackgroundNavyBlue
import com.pratiik.valorantagentscompose.presentation.ui.theme.BackgroundNavyBlue2
import com.pratiik.valorantagentscompose.presentation.ui.theme.Monserrat
import java.util.Locale
@Keep
@Composable
fun AgentDetailScreen(agentDetail: AgentModel?, color: Color?) {

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color?.toArgb() ?: BackgroundNavyBlue2.toArgb()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundNavyBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4F)
                .background(
                    color ?: BackgroundNavyBlue2
                )
        ) {

            val (imgMain, imgBackground, txtDisplayName, txtRole) = createRefs()

            AsyncImage(
                model = agentDetail?.fullPortrait,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .alpha(0.2F)
                    .fillMaxWidth(0.9F)
                    .constrainAs(imgBackground) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    },
            )

            AsyncImage(
                model = agentDetail?.fullPortrait,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .fillMaxHeight()
                    .constrainAs(imgMain) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )

            Text(
                text = agentDetail?.displayName.toString().toUpperCase(Locale.ENGLISH),
                modifier = Modifier.constrainAs(txtDisplayName) {
                    start.linkTo(parent.start, margin = 30.dp)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    linkTo(bottom = parent.bottom, top = parent.top, bias = 0.4F)
                },
                style = TextStyle(
                    color = Color.White,
                    fontFamily = Monserrat,
                    fontWeight = FontWeight.Black,
                    letterSpacing = TextUnit(8F, TextUnitType.Sp),
                    fontSize = 22.sp
                )
            )

            Text(text = agentDetail?.role.toString(), modifier = Modifier
                .constrainAs(txtRole) {
                    top.linkTo(txtDisplayName.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(txtDisplayName.start)
                    end.linkTo(txtDisplayName.end)
                    linkTo(top = txtDisplayName.bottom, bottom = parent.bottom, bias = 0.3F)
                }
                .clip(shape = RoundedCornerShape(10.dp))
                .background(BackgroundNavyBlue)
                .padding(vertical = 12.dp, horizontal = 18.dp), style = TextStyle(
                color = Color.White, fontFamily = Monserrat, fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            ))

        }


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {


                Column {

                    Text(
                        text = "// BIOGRAPHY",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp, end = 18.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = Monserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                        )
                    )

                    Text(
                        text = agentDetail?.description.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, bottom = 5.dp, end = 18.dp),
                        style = TextStyle(
                            color = Color.White.copy(alpha = 0.5F),
                            fontFamily = Monserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            lineHeight = 25.sp
                        )
                    )


                    Text(
                        text = "// SPECIAL ABILITIES", modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 12.dp), style = TextStyle(
                            color = Color.White,
                            fontFamily = Monserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                        )
                    )
                }

            }

            agentDetail?.abilities?.let {
                items(it.size) { index ->
                    AgentAbilityComposable(it[index])
                }
            }
        }

    }

}

@Preview
@Composable
fun PreviewAgentDetailScreen() {

    AgentDetailScreen(
        agentDetail = AgentModel(
            "UUID",
            "Omen",
            "https://media.valorant-api.com/agents/5f8d3a7f-467b-97f3-062c-13acf203c006/fullportrait.png",
            backgroundGradientColor = listOf(
                "ff9c33ff", "b04621ff", "523a23ff", "44412eff"
            ),
            emptyList(),
            role = "Controller",
            description = "OMEN IS MOST \nBADASS CHARACTER"
        ),
        BackgroundNavyBlue2,
    )

}