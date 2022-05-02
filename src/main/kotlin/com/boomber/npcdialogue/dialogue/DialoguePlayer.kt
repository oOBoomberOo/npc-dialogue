package com.boomber.npcdialogue.dialogue

import com.boomber.npcdialogue.api.IDialoguer
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Formatting
import kotlin.math.max
import kotlin.math.min

typealias Dialogues = Map<Int, Dialogue>

open class DialoguePlayer(
    val dialogues: Dialogues,
    val player: PlayerEntity,
    val npc: IDialoguer,
) {
    private var frame = 0
    private val maxFrame = dialogues.maxOf { (frame, _) -> frame }
    private val current: Dialogue? get() = dialogues[frame]

    val hasStopped get() = frame == maxFrame + 1
    val isPlaying get() = !hasStopped

    open fun tick() {
        current?.also {
            speak(it)
        }

        frame = min(frame + 1, maxFrame + 1)
    }

    private fun speak(dialogue: Dialogue) {
        val msg = dialogue.content
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
