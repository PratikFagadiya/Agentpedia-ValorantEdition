package com.pratiik.valorantagentscompose.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Ability(
    val description: String? = null,
    val displayIcon: String? = null,
    val displayName: String? = null,
    val slot: String? = null
) : Parcelable