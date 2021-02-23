package net.nachtbeere.minecraft.beeresicher.logic

enum class BeeresicherVitaChamberManufactureCode(code: Int) {
    DEFAULT(10),
    SUCCESS(20),
    SUCCESS_WITH_BONUS(21),
    FAILED(40),
    FAILED_NOT_TARGET(44),
    ERROR(50)
}