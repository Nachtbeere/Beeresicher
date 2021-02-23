package net.nachtbeere.minecraft.beeresicher.player

import net.nachtbeere.minecraft.beeresicher.logic.BeeresicherVitaChamberManufactureCode
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.time.OffsetDateTime

class BeeresicherPlayer(currentInventory: MutableList<ItemStack>,
                        currentArmors: MutableList<ItemStack>,
                        currentExp: Int,
                        manufactureCode: BeeresicherVitaChamberManufactureCode) {
    private val actualInventory = currentInventory
    private val actualArmors = currentArmors
    private val actualExp = currentExp
    val inventory: List<ItemStack> = actualInventory
    val armors: List<ItemStack> = actualArmors
    val manufactureCode = manufactureCode
    val createdAt: OffsetDateTime = OffsetDateTime.now()

    fun updateInventory(item: ItemStack) {
        actualInventory.add(item)
    }

    fun isSucceed(): Boolean {
        return when (this.manufactureCode) {
            BeeresicherVitaChamberManufactureCode.SUCCESS -> true
            BeeresicherVitaChamberManufactureCode.SUCCESS_WITH_BONUS -> true
            else -> false
        }
    }

    fun migration(player: Player) {
        player.inventory.contents = inventory.toTypedArray()
        player.inventory.setArmorContents(armors.toTypedArray())
        player.updateInventory()
    }
}