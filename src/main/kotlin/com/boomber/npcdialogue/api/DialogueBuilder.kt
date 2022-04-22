package com.boomber.npcdialogue.api

import com.boomber.npcdialogue.dialogue.Dialogue
import com.boomber.npcdialogue.dialogue.Dialogues
import kotlinx.serialization.json.JsonNull.content
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText

class DialogueBuilder(content: Dialogue) {
    private var counter = 0

    private var dialogues: MutableMap<Int, Dialogue> = mutableMapOf(
        Pair(counter, content)
    )

    constructor(translation: String): this(Dialogue.of(translation))

    constructor(text: Text): this(Dialogue.of(text))

    fun then(duration: Int, content: Dialogue): DialogueBuilder {
        dialogues[counter] = content
        counter += duration
        return this
    }

    fun then(duration: Int, content: Text) = then(duration, content = Dialogue.of(content))
    fun then(duration: Int, translation: String) = then(duration, content = Dialogue.of(translation))

    fun build(): Dialogues {
        return dialogues
    }
}
