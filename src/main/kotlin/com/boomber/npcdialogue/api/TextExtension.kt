package com.boomber.npcdialogue.api

import net.minecraft.item.ItemStack
import net.minecraft.text.HoverEvent
import net.minecraft.text.LiteralText
import net.minecraft.text.Text
import net.minecraft.text.Texts
import net.minecraft.util.Formatting

fun ItemStack.getDisplayText(): Text =
    LiteralText("[").append(name).append("]")
        .formatted(Formatting.DARK_AQUA)
        .styled {
            it.withHoverEvent(HoverEvent(HoverEvent.Action.SHOW_ITEM, HoverEvent.ItemStackContent(this)))
        }

fun List<Text>.join(separator: String): Text =
    Texts.join(this, LiteralText(separator))
