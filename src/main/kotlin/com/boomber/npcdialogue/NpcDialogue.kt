package com.boomber.npcdialogue

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import org.slf4j.Logger
import org.slf4j.LoggerFactory

public class NpcDialogue : ModInitializer {
    val logger: Logger = LoggerFactory.getLogger("NPC Dialogue")

    override fun onInitialize() {
        ServerTickEvents.START_SERVER_TICK.register(DialogueManager::tick)
        logger.info("Registered dialogue manager")
    }
}
