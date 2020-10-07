package net.nachtbeere.minecraft.beeresicher.registry

class StorageType() {
    companion object {
        const val file = "file"
        const val sqlite = "sqlite"
        const val mysql = "mysql"
        const val postgres = "postgres"
    }
}


class BeeresicherRegistryStorageFactory() {
    fun createStorage(config: BeeresicherRegistryConfig): BeeresicherRegistryStorage {
        return when (config.storageMethod) {
            StorageType.file -> BeeresicherFileStorage(config)
            StorageType.sqlite -> BeeresicherFileStorage(config)
            StorageType.mysql -> BeeresicherMySqlStorage(config)
            else -> BeeresicherMockStorage(config)
        }
    }
}


abstract class BeeresicherRegistryStorage(config: BeeresicherRegistryConfig) {
    private val config = config

    fun UUIDLookup() {

    }
}

class BeeresicherMockStorage(config: BeeresicherRegistryConfig) : BeeresicherRegistryStorage(config) {

}

class BeeresicherFileStorage(config: BeeresicherRegistryConfig) : BeeresicherRegistryStorage(config) {

}

class BeeresicherMySqlStorage(config: BeeresicherRegistryConfig) : BeeresicherRegistryStorage(config) {

}