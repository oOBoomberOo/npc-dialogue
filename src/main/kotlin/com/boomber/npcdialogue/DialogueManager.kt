package com.boomber.npcdialogue

import com.boomber.npcdialogue.api.IDialoguer
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
        activeDialogues.forEach { (_, player) -> player.tick() }

        activeDialogues.filter { (_, player) -> player.hasStopped }.forEach { (_, player) ->
            player.npc.ended()
            logger.info("stopped playing dialogue $player")
        }

        activeDialogues = activeDialogues.filter { it.value.isPlaying }.toMutableMap()
    }

    fun play(player: PlayerEntity, npc: IDialoguer, dialogues: Dialogues): Boolean {
        val current = activeDialogues[npc.entity.uuid]

        if (current != null) {
            logger.warn("$player already has an active dialogue: $current")
            return false
        }

        activeDialogues[npc.entity.uuid] = DialoguePlayer(dialogues, player, npc).also {
            npc.begun(player)
        }

        logger.info("started playing dialogues for $player")

        return true
    }
}
