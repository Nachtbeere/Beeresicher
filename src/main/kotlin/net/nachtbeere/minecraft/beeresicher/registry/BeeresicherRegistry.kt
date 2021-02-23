package net.nachtbeere.minecraft.beeresicher.registry

import net.nachtbeere.minecraft.beeresicher.player.BeeresicherPlayer
import java.util.*
import kotlin.collections.HashMap

class BeeresicherRegistry(config: BeeresicherRegistryConfig) {
    val config = config
    private val cache = HashMap<UUID, BeeresicherPlayer?>()
    private val storage = BeeresicherRegistryStorageFactory().createStorage(config)

    init {
        // storage to cache
    }

    fun pop(uuid: UUID): BeeresicherPlayer? {
        val player = cache[uuid]
        cache[uuid] = null
        return player
    }

    fun push(uuid: UUID, player: BeeresicherPlayer) {
        cache[uuid] = player
    }

    private fun updateStorageLater(uuid: UUID, player: BeeresicherPlayer) {

    }
}