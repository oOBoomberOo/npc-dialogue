package com.boomber.npcdialogue.api

import com.boomber.npcdialogue.dialogue.Dialogue
import com.boomber.npcdialogue.dialogue.Dialogues
import kotlinx.serialization.json.JsonNull.content
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText

class DialogueBuilder() {
    private var counter = 0

    private var dialogues: MutableMap<Int, Dialogue> = mutableMapOf()

    fun then(duration: Int, content: Dialogue): DialogueBuilder {
        dialogues[counter] = content
        counter += duration
        return this
    }

    fun then(duration: Int, content: Text) = then(duration, content = Dialogue.of(content))
    fun then(duration: Int, translation: String, vararg args: Any) = then(duration, content = TranslatableText(translation, *args))

    fun build(): Dialogues {
        return dialogues
    }
}
