package net.nachtbeere.minecraft.beeresicher.messages

import net.nachtbeere.minecraft.beeresicher.logic.BeeresicherVitaChamberManufactureCode
import org.bukkit.configuration.MemorySection

class BeeresicherMessages(rawMessages: MemorySection) {
    val messageCollection: HashMap<BeeresicherVitaChamberManufactureCode, String> = hashMapOf()
    private var messagePrefix = ""
    private var messageSuffix = ""

    init {
        messagePrefix = rawMessages.getString("prefix")!!
        messageSuffix = rawMessages.getString("suffix")!!
        messageCollection[BeeresicherVitaChamberManufactureCode.DEFAULT] = rawMessages.getString("default")!!
        messageCollection[BeeresicherVitaChamberManufactureCode.SUCCESS] = rawMessages.getString("success")!!
        messageCollection[BeeresicherVitaChamberManufactureCode.SUCCESS_WITH_BONUS] = rawMessages.getString("success_with_bonus")!!
        messageCollection[BeeresicherVitaChamberManufactureCode.FAILED] = rawMessages.getString("failed")!!
        messageCollection[BeeresicherVitaChamberManufactureCode.FAILED_NOT_TARGET] = rawMessages.getString("failed_not_target")!!
        messageCollection[BeeresicherVitaChamberManufactureCode.ERROR] = rawMessages.getString("error")!!
    }

    fun message(code: BeeresicherVitaChamberManufactureCode): String {
        return "${messagePrefix} ${messageCollection[code]!!} ${messageSuffix}"
    }
}