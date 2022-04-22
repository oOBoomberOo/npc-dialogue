package com.boomber.npcdialogue.api

import com.boomber.npcdialogue.dialogue.Dialogues
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity

interface IDialoguer {
    val entity: Entity

    fun begun(player: PlayerEntity)

    fun ended()
}
