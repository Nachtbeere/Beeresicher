package net.nachtbeere.minecraft.beeresicher.logic

import net.nachtbeere.minecraft.beeresicher.Beeresicher
import net.nachtbeere.minecraft.beeresicher.economy.BeeresicherEconomy
import net.nachtbeere.minecraft.beeresicher.permission.BeeresicherPermission
import net.nachtbeere.minecraft.beeresicher.registry.BeeresicherRegistry
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent

class BeeresicherLogic(instance: Beeresicher) {
    private val plugin = instance
    private val config = plugin.beeresicherConfig!!
    private val factory = BeeresicherVitaChamberFactory()
    private val registry = BeeresicherRegistry(plugin.beeresicherRegistryConfig!!)
    private val permission = BeeresicherPermission(plugin.beeresicherPermissionConfig!!)
    private val economy = BeeresicherEconomy(plugin.beeresicherEconomyConfig!!)
    private val randomizer = BeeresicherRandomizer(plugin.beeresicherRandomizerConfig!!)

    fun onDeath(event: PlayerDeathEvent) {
        val playerMetadata = permission.playerMetadata(event.entity.player!!)
        val chamber = factory.createChamber(config.mode, event, playerMetadata, randomizer)
        val clone = chamber.makeClone()
        registry.push(event.entity.player!!.uniqueId, clone)
        if (clone.isSucceed()) {
            event.drops.clear()
            event.keepLevel = true
            event.droppedExp = 0
        }
    }

    fun onRespawn(event: PlayerRespawnEvent) {
        val clone = registry.fetch(event.player.uniqueId)
        if (clone != null) {
            if (config.debug) {
                plugin.logger.info("initiate clone migrate")
            }
            if (clone.isSucceed()) {
                clone.migration(event.player)
            }
            event.player.sendMessage(config.messages.message(clone.manufactureCode))
        } else {
            event.player.sendMessage(config.messages.message(BeeresicherVitaChamberManufactureCode.DEFAULT))
        }
    }
}
