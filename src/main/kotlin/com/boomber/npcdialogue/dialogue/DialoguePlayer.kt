package com.boomber.npcdialogue.dialogue

import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Formatting
import kotlin.math.max

typealias Dialogues = Map<Int, Dialogue>

open class DialoguePlayer(
    val dialogues: Dialogues,
    val player: PlayerEntity,
    private val npc: Entity,
) {
    private var frame = 0
    private val maxFrame = dialogues.maxOf { (frame, _) -> frame }
    private val current: Dialogue? get() = dialogues[frame]

    val hasStopped get() = frame == maxFrame + 1

    open fun tick() {
        current?.also {
            speak(it)
        }

        frame = max(frame + 1, maxFrame + 1)
    }

    private fun speak(dialogue: Dialogue) {
        val name = npc.name.copy().append(": ").styled { it.withColor(Formatting.GREEN) }

        val msg = name.append(dialogue.content)
        player.sendMessage(msg, false)

        dialogue.sounds
            .ifEmpty {
                listOf(SoundEvents.ENTITY_CHICKEN_EGG)
            }
            .forEach {
                player.playSound(it, 1f, 1f)
            }
    }
}
