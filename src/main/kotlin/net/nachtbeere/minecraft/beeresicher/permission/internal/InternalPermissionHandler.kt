package net.nachtbeere.minecraft.beeresicher.permission.internal

import net.nachtbeere.minecraft.beeresicher.permission.BeeresicherPermissionHandler
import org.bukkit.entity.Player

class InternalPermissionHandler(): BeeresicherPermissionHandler() {
    override fun getPermissionGroup(player: Player): String {
        return if (player.isOp) {
            "admin"
        } else {
            "default"
        }
    }
}