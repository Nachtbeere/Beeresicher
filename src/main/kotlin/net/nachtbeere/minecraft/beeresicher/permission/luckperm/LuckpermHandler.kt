package net.nachtbeere.minecraft.beeresicher.permission.luckperm

import net.nachtbeere.minecraft.beeresicher.permission.BeeresicherPermissionHandler
import org.bukkit.entity.Player

class LuckpermHandler() : BeeresicherPermissionHandler() {
    override fun getPermissionGroup(player: Player): String {
        return "default"
    }
}
