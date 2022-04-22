package com.boomber.npcdialogue

import com.boomber.npcdialogue.api.IDialoguer
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World

object PlayerEventHandler {
    fun handle(player: PlayerEntity, world: World, hand: Hand, entity: Entity, hitResult: EntityHitResult?): ActionResult {
        if (entity !is IDialoguer) {
            return ActionResult.PASS
        }

        if (!player.getStackInHand(hand).isEmpty) {
            return ActionResult.PASS
        }

        val success = DialogueManager.play(player, entity, entity.dialogues)

        return if (success) {
            ActionResult.success(true)
        } else {
            ActionResult.FAIL
        }
    }
}
