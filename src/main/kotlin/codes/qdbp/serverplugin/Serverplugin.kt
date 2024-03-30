package codes.qdbp.serverplugin

import codes.qdbp.serverplugin.commands.*
import codes.qdbp.serverplugin.listeners.*
import codes.qdbp.serverplugin.misc.*
import codes.qdbp.serverplugin.recipes.Recipes
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.logging.Level

class Serverplugin : JavaPlugin() {
    private val logger = Bukkit.getLogger()

    /**
     * Create DataStorages
     */
    val backpackStorage = DataStorage(File(dataFolder, "backpackStorage.yml"))
    val deathStorage = DataStorage(File(dataFolder, "deathStorage.yml"))
    val mapImageStorage = DataStorage(File(dataFolder, "mapImageStorage.yml"))

    /**
     * Namespaced Keys
     */
    val invisItemFrameNSK = NamespacedKey(this, "invisible")
    val efficiencyUpgradeNSK = NamespacedKey(this, "efficiency")

    override fun onEnable() {

        /**
         * Update Checker
         */
        if (!checkPluginUpToDate(this)) logger.log(Level.WARNING, "There is a newer plugin version available!")

        /**
         * Config Defaults
         */
        config.addDefault("Features.useAFK", true)
        config.addDefault("Features.useBackpack", true)
        config.addDefault("Features.useCraft", true)
        config.addDefault("Features.useEnderchest", true)
        config.addDefault("Features.useFreecam", true)
        config.addDefault("Features.useSkipNight", true)
        config.addDefault("Features.useSwitchWorld", true)
        config.addDefault("Features.useDeaths", true)
        config.addDefault("Features.useEating", true)
        config.addDefault("Features.useSleep", true)
        config.addDefault("Features.useLightRecipe", true)
        config.addDefault("Features.useDoubleOpenDoors", true)
        config.addDefault("Features.useCustomMapImages", true)
        config.addDefault("Features.useUpgrade", true)
        config.addDefault("Features.useInvisibleItemFrames", true)
        config.addDefault("Features.useChangeUpgrade", true)
        config.addDefault("Features.useFastLeavesDecay", true)
        config.options().copyDefaults(true)
        saveConfig()

        /**
         * Get Config Toggles
         */
        val useAFK = config.getBoolean("Features.useAFK")
        val useBackpack = config.getBoolean("Features.useBackpack")
        val useCraft = config.getBoolean("Features.useCraft")
        val useEnderchest = config.getBoolean("Features.useEnderchest")
        val useFreecam = config.getBoolean("Features.useFreecam")
        val useSkipNight = config.getBoolean("Features.useSkipNight")
        val useSwitchWorld = config.getBoolean("Features.useSwitchWorld")
        val useDeaths = config.getBoolean("Features.useDeaths")
        val useEating = config.getBoolean("Features.useEating")
        val useSleep = config.getBoolean("Features.useSleep")
        val useLightRecipe = config.getBoolean("Features.useLightRecipe")
        val useDoubleOpenDoors = config.getBoolean("Features.useDoubleOpenDoors")
        val useCustomMapImages = config.getBoolean("Features.useCustomMapImages")
        val useUpgrade = config.getBoolean("Features.useUpgrade")
        val useInvisibleItemFrames = config.getBoolean("Features.useInvisibleItemFrames")
        val useChangeUpgrade = config.getBoolean("Features.useChangeUpgrade")
        val useFastLeavesDecay = config.getBoolean("Features.useFastLeavesDecay")

        /**
         * Commands
         */
        getCommand("use")?.setExecutor(UseCmd(this))
        getCommand("features")?.setExecutor(FeaturesCmd())
        getCommand("info")?.setExecutor(InfoCmd())
        if (useAFK) getCommand("afk")?.setExecutor(AfkCmd(this))
        if (useBackpack) getCommand("backpack")?.setExecutor(BackpackCmd(this))
        if (useCraft) getCommand("craft")?.setExecutor(CraftCmd())
        if (useEnderchest) getCommand("enderchest")?.setExecutor(EnderchestCmd())
        if (useFreecam) getCommand("freecam")?.setExecutor(FreecamCmd())
        if (useSwitchWorld) getCommand("switchworld")?.setExecutor(SwitchWorldCmd())
        if (useDeaths) getCommand("deaths")?.setExecutor(DeathsCmd(this))
        if (useSkipNight) getCommand("skipnight")?.setExecutor(SkipNightCmd(this))
        if (useCustomMapImages) getCommand("mapimage")?.setExecutor(MapImageCmd(this))
        if (useUpgrade) getCommand("upgrade")?.setExecutor(UpgradeCmd(this))
        if (useChangeUpgrade) getCommand("changeUpgrade")?.setExecutor(EditUpgradesCmd(this))

        /**
         * Listeners
         */
        server.pluginManager.registerEvents(PlayerJoinListener(this), this)
        if (useBackpack) server.pluginManager.registerEvents(InventoryCloseListener(this), this)
        if (useDeaths) {
            server.pluginManager.registerEvents(InventoryListener(this), this)
            server.pluginManager.registerEvents(PlayerDeathListener(this), this)
        }
        if (useEating) server.pluginManager.registerEvents(PlayerItemConsumeListener(), this)
        if (useAFK) server.pluginManager.registerEvents(PlayerKickListener(this), this)
        if (useSleep) server.pluginManager.registerEvents(PlayerBedEnterListener(this), this)
        if (useAFK) server.pluginManager.registerEvents(PlayerMoveListener(), this)
        if (useAFK || useFreecam) server.pluginManager.registerEvents(PlayerQuitListener(this), this) //TODO dont know about this one
        if (useDoubleOpenDoors) server.pluginManager.registerEvents(PlayerDoorInteractListener(), this) //TODO fix sneaking
        if (useInvisibleItemFrames) server.pluginManager.registerEvents(PlayerItemFrameChange(this), this)
        if (useInvisibleItemFrames) server.pluginManager.registerEvents(HangingPlaceEvent(this), this)
        if (useInvisibleItemFrames) server.pluginManager.registerEvents(BreakThisListenern(this), this)
        if (useFastLeavesDecay) server.pluginManager.registerEvents(LeavesDecayListener(), this)

        /**
         * Recipes
         */
        if (useLightRecipe) Bukkit.addRecipe(Recipes(this).lightRecipe)
        if (useInvisibleItemFrames) Bukkit.addRecipe(Recipes(this).invisItemFrameRecipe)

        //Reload MapImages
        mapImageStorage.storage.getIntegerList("mapIDs").forEach {
            val map = Bukkit.getMap(it)
            while (map?.renderers?.iterator()?.hasNext() ?: return) {
                map.removeRenderer(map.renderers.iterator().next())
            }
            MapImage(this, map)
        }
    }

    override fun onDisable() {

        /**
         * Return freecamplayers
         */
        if (freecamPlayers.isNotEmpty()) {
            freecamPlayers.forEach {
                freecamPlayers[it.key]!!.player.endFreecam()
            }
        }
    }
}