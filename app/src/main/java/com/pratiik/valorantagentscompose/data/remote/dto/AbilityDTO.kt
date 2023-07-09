package com.pratiik.valorantagentscompose.data.remote.dto

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.domain.model.Ability

@Keep
data class AbilityDTO(
    val description: String, val displayIcon: String, val displayName: String, val slot: String
)

fun AbilityDTO.toDomainModel(): Ability {

    return Ability(
        description = this.description,
        displayIcon = this.displayIcon,
        displayName = this.displayName,
        slot = this.slot
    )

}