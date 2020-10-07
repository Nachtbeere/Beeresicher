package net.nachtbeere.minecraft.beeresicher.permission

import net.nachtbeere.minecraft.beeresicher.permission.internal.InternalPermissionHandler
import net.nachtbeere.minecraft.beeresicher.permission.luckperm.LuckpermHandler
import org.bukkit.entity.Player

class PermissionMethods() {
    companion object {
        const val INTERNAL: String = "internal"
        const val LUCKPERM: String = "luckperm"
    }
}

class BeeresicherPermissionFactory() {
    fun createHandler(config: BeeresicherPermissionConfig): BeeresicherPermissionHandler {
        return when (config.permissionMethod) {
            PermissionMethods.LUCKPERM -> LuckpermHandler()
            else -> InternalPermissionHandler()
        }
    }
}

abstract class BeeresicherPermissionHandler() {
    val defaultPermissionGroup = "default"

    abstract fun getPermissionGroup(player: Player): String
}
