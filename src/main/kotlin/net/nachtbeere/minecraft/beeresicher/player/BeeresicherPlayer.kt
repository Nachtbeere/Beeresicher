package net.nachtbeere.minecraft.beeresicher.player

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.time.OffsetDateTime

class BeeresicherPlayer(currentInventory: MutableList<ItemStack>,
                        currentArmors: MutableList<ItemStack>,
                        currentExp: Int,
                        manufactureCode: Int) {
    private val actualInventory = currentInventory
    private val actualArmors = currentArmors
    private val actualExp = currentExp
    val inventory: List<ItemStack> = actualInventory
    val armors: List<ItemStack> = actualArmors
    val manufactureCode: Int = manufactureCode
    val createdAt: OffsetDateTime = OffsetDateTime.now()

    fun updateInventory(item: ItemStack) {
        actualInventory.add(item)
    }

    fun isSucceed(): Boolean {
        return this.manufactureCode < 30
    }

    fun migration(player: Player) {
        player.inventory.contents = inventory.toTypedArray()
        player.inventory.setArmorContents(armors.toTypedArray())
        player.updateInventory()
    }
}