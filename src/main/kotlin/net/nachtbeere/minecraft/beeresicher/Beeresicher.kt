package net.nachtbeere.minecraft.beeresicher

import net.nachtbeere.minecraft.beeresicher.economy.BeeresicherEconomyConfig
import net.nachtbeere.minecraft.beeresicher.logic.BeeresicherRandomizerConfig
import net.nachtbeere.minecraft.beeresicher.messages.BeeresicherMessages
import net.nachtbeere.minecraft.beeresicher.permission.BeeresicherPermissionConfig
import net.nachtbeere.minecraft.beeresicher.registry.BeeresicherRegistryConfig
import org.bukkit.configuration.MemorySection
import java.io.File
import org.bukkit.plugin.java.JavaPlugin

class Beeresicher : JavaPlugin() {
    var beeresicherConfig: BeeresicherConfig? = null
    var beeresicherRegistryConfig: BeeresicherRegistryConfig? = null
    var beeresicherRandomizerConfig: BeeresicherRandomizerConfig? = null
    var beeresicherPermissionConfig: BeeresicherPermissionConfig? = null
    var beeresicherEconomyConfig: BeeresicherEconomyConfig? = null
    private var beeresicherListener: BeeresicherListener? = null

    private fun loadConfig() {
        if (!(File(this.dataFolder, "config.yml")).exists()) this.saveDefaultConfig()
        beeresicherConfig = BeeresicherConfig(
            mode = this.config.getString("mode")!!.toLowerCase(),
            debug = this.config.getBoolean("debug"),
            messages = BeeresicherMessages(this.config.get("messages") as MemorySection)
        )
        beeresicherRegistryConfig = BeeresicherRegistryConfig(
            rawConfig = this.config.get("storage") as MemorySection
        )
        beeresicherRandomizerConfig = BeeresicherRandomizerConfig(
            rawConfig = this.config.get("randomize") as MemorySection
        )
        beeresicherPermissionConfig = BeeresicherPermissionConfig(
            rawConfig = this.config.get("permission") as MemorySection
        )
        beeresicherEconomyConfig = BeeresicherEconomyConfig(
            rawConfig = this.config.get("economy") as MemorySection
        )
    }

    override fun onLoad() {
        loadConfig()
    }

    override fun onEnable() {
        if (!(File(this.dataFolder, "config.yml")).exists()) this.saveDefaultConfig()
        beeresicherListener = BeeresicherListener(
            pluginInstance = this
        )
    }

    override fun onDisable() {
        this.beeresicherListener = null
    }
}
