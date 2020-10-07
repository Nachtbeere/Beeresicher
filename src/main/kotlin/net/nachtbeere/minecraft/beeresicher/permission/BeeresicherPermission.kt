package net.nachtbeere.minecraft.beeresicher.permission

import net.nachtbeere.minecraft.beeresicher.player.BeeresicherPlayerMetadata
import org.bukkit.entity.Player

class BeeresicherPermission(val config: BeeresicherPermissionConfig) {
    private val handler = BeeresicherPermissionFactory().createHandler(config)

    fun playerMetadata(player: Player): BeeresicherPlayerMetadata {
        val metadata = BeeresicherPlayerMetadata()
        if (config.usePermissionGroup) {
            metadata.permissionGroup = handler.getPermissionGroup(player)
        } else {
            metadata.permissionGroup = handler.defaultPermissionGroup
        }
        return metadata
    }
}