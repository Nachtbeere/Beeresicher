package net.nachtbeere.minecraft.beeresicher.permission

import org.bukkit.configuration.MemorySection

class BeeresicherPermissionConfig(rawConfig: MemorySection) {
    val usePermissionGroup = rawConfig.getBoolean("use_permission_group")
    val permissionMethod = rawConfig.getString("permission_method")
}