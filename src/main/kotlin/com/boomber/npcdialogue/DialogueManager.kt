package com.boomber.npcdialogue

import com.boomber.npcdialogue.dialogue.DialoguePlayer
import com.boomber.npcdialogue.dialogue.Dialogues
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.MinecraftServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

object DialogueManager {
    val logger: Logger = LoggerFactory.getLogger("Dialogue Manager")
    var activeDialogues: MutableMap<UUID, DialoguePlayer> = mutableMapOf()

    fun tick(server: MinecraftServer) {
        activeDialogues.forEach { (id, player) ->
            player.tick()

            if (player.hasStopped) {
                activeDialogues.remove(id)
                logger.info("stopped playing dialogue $player")
            }
        }
    }

    fun play(player: PlayerEntity, npc: Entity, dialogues: Dialogues): Boolean {
        val current = activeDialogues[npc.uuid]

        if (current != null) {
            logger.warn("$player already has an active dialogue: $current")
            return false
        }

        activeDialogues[npc.uuid] = DialoguePlayer(dialogues, player, npc)
        logger.info("started playing dialogues for $player")

        return true
    }
}
