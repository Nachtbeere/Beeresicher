package net.nachtbeere.minecraft.beeresicher

import net.nachtbeere.minecraft.beeresicher.economy.BeeresicherEconomyConfig
import net.nachtbeere.minecraft.beeresicher.logic.BeeresicherLogic
import net.nachtbeere.minecraft.beeresicher.registry.BeeresicherRegistryConfig
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent

class BeeresicherListener(pluginInstance: Beeresicher) : Listener {
    private val logic = BeeresicherLogic(pluginInstance)

    init {
        pluginInstance.server.pluginManager.registerEvents(this, pluginInstance)
    }

    @EventHandler(priority = EventPriority.LOW)
    fun onPlayerDeath(event: PlayerDeathEvent) {
        logic.onDeath(event)
    }

    @EventHandler(priority = EventPriority.LOW)
    fun onPlayerRespawn(event: PlayerRespawnEvent) {
        logic.onRespawn(event)
    }

}