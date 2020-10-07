package net.nachtbeere.minecraft.beeresicher.logic

import net.nachtbeere.minecraft.beeresicher.player.BeeresicherPlayerMetadata
import kotlin.random.Random

class BeeresicherRandomizer(val config: BeeresicherRandomizerConfig) {
    private val maxRate = 1000
    private val minRate = 0

    private fun draw(targetRate: Int): Boolean {
        val magicNumber = Random.nextInt(this.maxRate)
        return magicNumber in this.minRate..targetRate
    }

    fun isRewarded(playerMetadata: BeeresicherPlayerMetadata): Boolean {
        val drawCell = config.randomCells.getOrDefault(
            playerMetadata.permissionGroup,
            config.randomCells.iterator().next().value
        )
        return draw(drawCell.rate)
    }

    fun isBonusRewarded(playerMetadata: BeeresicherPlayerMetadata): Boolean {
        return true
    }


}