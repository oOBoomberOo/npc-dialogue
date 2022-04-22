package com.boomber.npcdialogue.api

import com.boomber.npcdialogue.dialogue.Dialogue
import com.boomber.npcdialogue.dialogue.Dialogues
import net.minecraft.text.Text

class DialogueBuilder(content: Dialogue) {
    private var counter = 0

    private var dialogues: MutableMap<Int, Dialogue> = mutableMapOf(
        Pair(counter, content)
    )

    fun then(duration: Int, content: Dialogue): DialogueBuilder {
        dialogues[counter] = content
        counter += duration
        return this
    }

    fun then(duration: Int, content: Text) = then(duration, content = Dialogue.of(content))
    fun then(duration: Int, content: String) = then(duration, content = Dialogue.of(content))

    fun build(): Dialogues {
        return dialogues
    }
}
