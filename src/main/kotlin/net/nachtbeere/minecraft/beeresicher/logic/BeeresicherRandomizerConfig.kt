package net.nachtbeere.minecraft.beeresicher.logic

import org.bukkit.configuration.MemorySection
import java.util.logging.Logger

class BeeresicherRandomizerConfig(rawConfig: MemorySection) {
    val useRandomize = rawConfig.getBoolean("use_randomize")
    val allowBonus = rawConfig.getBoolean("allow_bonus")
    val randomCells: HashMap<String, BeeresicherRandomizerRandomCell> = HashMap()
    val bonusCells: HashMap<String, BeeresicherRandomizerBonusCell> = HashMap()

    init {
        for (cell in rawConfig.getList("random")!!) {
            if (cell != null) {
                val c = BeeresicherRandomizerRandomCell.ModelMapper.from(cell as LinkedHashMap<String, HashMap<String, Any>>)
                randomCells[c.name] = c
            }
        }
        for (cell in rawConfig.getList("bonus")!!) {
            if (cell != null) {
                val c = BeeresicherRandomizerBonusCell.ModelMapper.from(cell as LinkedHashMap<String, HashMap<String, Any>>)
                bonusCells[c.name] = c
            }
        }
    }
}

class BeeresicherRandomizerRandomCell(val name: String, val rate: Int) {
    object ModelMapper {
        fun from(map: LinkedHashMap<String, HashMap<String, Any>>): BeeresicherRandomizerRandomCell {
            val key = map.keys.iterator().next()
            return BeeresicherRandomizerRandomCell(
                name = key,
                rate = (map[key]?.get("rate") ?: error("")) as Int
            )
        }
    }
}

class BeeresicherRandomizerBonusCell(val name: String, val rate: Int, val income: String) {
    object ModelMapper {
        fun from(map: LinkedHashMap<String, HashMap<String, Any>>): BeeresicherRandomizerBonusCell {
            val key = map.keys.iterator().next()
            return BeeresicherRandomizerBonusCell(
                name = key,
                rate = (map[key]?.get("rate") ?: error("")) as Int,
                income = (map[key]?.get("income") ?: error("")) as String
            )
        }
    }
}