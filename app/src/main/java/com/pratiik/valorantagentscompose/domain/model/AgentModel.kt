package com.pratiik.valorantagentscompose.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.domain.model.Ability
import kotlinx.parcelize.Parcelize
@Keep
@Parcelize
data class AgentModel(
    val uuid: String? = null,
    val displayName: String? = null,
    val fullPortrait: String? = null,
    val backgroundGradientColor: List<String>? = null,
    val abilities: List<Ability>? = null,
    val background: String? = null,
    val role : String? = null,
    val description: String? = null
) : Parcelable