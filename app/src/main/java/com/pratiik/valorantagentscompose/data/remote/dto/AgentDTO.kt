package com.pratiik.valorantagentscompose.data.remote.dto

import androidx.annotation.Keep
import com.pratiik.valorantagentscompose.domain.model.AgentModel
@Keep
data class AgentDTO(
    val abilities: List<AbilityDTO>,
    val assetPath: String,
    val background: String,
    val backgroundGradientColors: List<String>,
    val bustPortrait: String,
    val characterTags: List<String>,
    val description: String,
    val developerName: String,
    val displayIcon: String,
    val displayIconSmall: String,
    val displayName: String,
    val fullPortrait: String,
    val fullPortraitV2: String,
    val isAvailableForTest: Boolean,
    val isBaseContent: Boolean,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val killfeedPortrait: String,
    val role: RoleDTO,
    val uuid: String,
    val voiceLine: VoiceLineDTO
)

fun AgentDTO.toDomainModel(): AgentModel {

    return AgentModel(uuid = this.uuid,
        displayName = this.displayName,
        fullPortrait = this.fullPortrait,
        backgroundGradientColor = this.backgroundGradientColors.subList(0, 2),
        abilities = this.abilities.map {
            it.toDomainModel()
        }.filter {
            it.displayIcon != null
        },
        background = this.background,
        role = this.role.displayName,
        description = this.description
    )

}