package net.nachtbeere.minecraft.beeresicher.registry

import org.bukkit.configuration.MemorySection

class BeeresicherRegistryConfig(rawConfig: MemorySection) {
    val useStorage = rawConfig.getString("use_storage")
    val storageMethod = rawConfig.getString("storage_method")
    val storageConnectionInfo = rawConfig.getString("storage_connection_info")
    val storageConnectionFlag = rawConfig.getString("storage_connection_flag")
    val storageConnectionPool = rawConfig.getString("storage_connection_pool")
}