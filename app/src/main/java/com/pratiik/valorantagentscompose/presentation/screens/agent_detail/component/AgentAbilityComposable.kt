package com.pratiik.valorantagentscompose.presentation.screens.agent_detail.component

import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pratiik.valorantagentscompose.domain.model.Ability
import com.pratiik.valorantagentscompose.presentation.ui.theme.BackgroundNavyBlue2
import com.pratiik.valorantagentscompose.presentation.ui.theme.ItemBackground
import com.pratiik.valorantagentscompose.presentation.ui.theme.Monserrat
import java.util.Locale
@Keep
@Composable
fun AgentAbilityComposable(ability: Ability) {

    Row(
        modifier = Modifier
            .height(70.dp)
            .clip(RoundedCornerShape(5.dp))
            .fillMaxSize()
            .background(ItemBackground),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Spacer(
            modifier = Modifier
                .size(10.dp)
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(ability.displayIcon)
                .crossfade(true).build(),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(7.dp))
                .background(BackgroundNavyBlue2)
                .size(50.dp)
                .aspectRatio(1F)
                .padding(10.dp),
            contentScale = ContentScale.Inside
        )

        Column(
            modifier = Modifier
                .padding(start = 10.dp, top = 5.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {


            ability.displayName?.let {
                Text(
                    text = it.uppercase(Locale.getDefault()),
                    color = Color.White,
                    fontFamily = Monserrat,
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp,
                )
            }

            ability.description?.let {
                Text(
                    text = it.uppercase(Locale.getDefault()),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 7.dp, bottom = 7.dp,  end = 10.dp),
                    color = Color.White.copy(alpha = 0.7f),
                    fontFamily = Monserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

        }

    }

}