package net.nachtbeere.minecraft.beeresicher

import net.nachtbeere.minecraft.beeresicher.messages.BeeresicherMessages

data class BeeresicherConfig(
    val mode: String,
    val debug: Boolean,
    val messages: BeeresicherMessages
)