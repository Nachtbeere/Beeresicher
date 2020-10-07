package net.nachtbeere.minecraft.beeresicher.economy

import org.bukkit.configuration.MemorySection

class BeeresicherEconomyConfig(rawConfig: MemorySection) {
    val useEconomy = rawConfig.getBoolean("use_economy_system")
    val economyMethod = rawConfig.getString("economy_method")
}