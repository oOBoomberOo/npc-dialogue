package com.boomber.npcdialogue.dialogue

import net.minecraft.sound.SoundEvent
import net.minecraft.text.LiteralText
import net.minecraft.text.Text

data class Dialogue(
    val content: Text,
    val sounds: List<SoundEvent>,
) {
    companion object {
        fun of(content: Text) = Dialogue(content)
        fun of(content: String) = Dialogue(content = LiteralText(content))
    }

    constructor(content: Text): this(content, sounds = listOf())

    fun withSound(sound: SoundEvent): Dialogue =
        this.copy(sounds = sounds.plus(sound))
}
