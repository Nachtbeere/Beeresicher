package net.nachtbeere.minecraft.beeresicher.logic

import net.nachtbeere.minecraft.beeresicher.player.BeeresicherPlayer
import net.nachtbeere.minecraft.beeresicher.player.BeeresicherPlayerMetadata
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack

class BeeresicherVitaChamberFactory() {
    fun createChamber(mode: String,
                      event: PlayerDeathEvent,
                      playerMetadata: BeeresicherPlayerMetadata,
                      randomizer: BeeresicherRandomizer): BeeresicherVitaChamber {
        return when (mode) {
            "simple" -> BeeresicherSimpleVitaChamber(event.entity.player!!, randomizer, playerMetadata)
            "advanced" -> BeeresicherAdvancedVitaChamber(event.entity.player!!, randomizer, playerMetadata)
            else -> BeeresicherSimpleVitaChamber(event.entity.player!!, randomizer, playerMetadata) // add warn log here
        }
    }
}

abstract class BeeresicherVitaChamber(
    val player: Player,
    private val randomizer: BeeresicherRandomizer,
    private val playerMetadata: BeeresicherPlayerMetadata
) {
    var manufactureCode: BeeresicherVitaChamberManufactureCode = BeeresicherVitaChamberManufactureCode.DEFAULT

    fun isRecoverable(): Boolean {
        if (randomizer.config.useRandomize) {
            return randomizer.isRewarded(playerMetadata)
        }
        return true
    }

    fun isBonusReward(): Boolean {
        if (randomizer.config.useRandomize) {
            return randomizer.isBonusRewarded(playerMetadata)
        }
        return false
    }

    fun manufactureClone(): BeeresicherPlayer {
        return BeeresicherPlayer(
            currentInventory = this.player.inventory.contents.toMutableList() ,
            currentArmors = this.player.inventory.armorContents.toMutableList(),
            currentExp = 0, // write later
            manufactureCode = this.manufactureCode
        )
    }

    fun emptyClone(): BeeresicherPlayer {
        return BeeresicherPlayer(
            currentInventory = emptyList<ItemStack>() as MutableList<ItemStack>,
            currentArmors = emptyList<ItemStack>() as MutableList<ItemStack>,
            currentExp = 0, // write later
            manufactureCode = this.manufactureCode
        )
    }

    abstract fun preExecute()

    abstract fun makeClone(): BeeresicherPlayer
}

class BeeresicherSimpleVitaChamber(player: Player, randomizer: BeeresicherRandomizer, playerMetadata: BeeresicherPlayerMetadata)
    : BeeresicherVitaChamber(player, randomizer, playerMetadata) {
    override fun preExecute() {
        if (isRecoverable()) {
            this.manufactureCode = BeeresicherVitaChamberManufactureCode.SUCCESS
        } else {
            this.manufactureCode = BeeresicherVitaChamberManufactureCode.FAILED
        }
    }

    override fun makeClone(): BeeresicherPlayer {
        return try {
            preExecute()
            manufactureClone()
        } catch (ex: Exception) {
            this.manufactureCode = BeeresicherVitaChamberManufactureCode.ERROR
            emptyClone()
        }
    }
}

class BeeresicherAdvancedVitaChamber(player: Player, randomizer: BeeresicherRandomizer, playerMetadata: BeeresicherPlayerMetadata)
    : BeeresicherVitaChamber(player, randomizer, playerMetadata) {
    override fun preExecute() {
        if (isRecoverable()) {
            if (isBonusReward()) {
                this.manufactureCode = BeeresicherVitaChamberManufactureCode.SUCCESS_WITH_BONUS
            } else {
                this.manufactureCode = BeeresicherVitaChamberManufactureCode.SUCCESS
            }
        } else {
            this.manufactureCode = BeeresicherVitaChamberManufactureCode.FAILED
        }
    }

    override fun makeClone(): BeeresicherPlayer {
        return try {
            preExecute()
            manufactureClone()
        } catch (ex: Exception) {
            this.manufactureCode = BeeresicherVitaChamberManufactureCode.ERROR
            emptyClone()
        }
    }
}