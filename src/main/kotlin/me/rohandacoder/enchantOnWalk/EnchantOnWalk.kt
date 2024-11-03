package me.rohandacoder.enchantOnWalk

import me.rohandacoder.enchantOnWalk.commands.PingCommand
import me.rohandacoder.enchantOnWalk.commands.ReloadCommand
import me.rohandacoder.enchantOnWalk.commands.StartCommand
import me.rohandacoder.enchantOnWalk.commands.StopCommand
import me.rohandacoder.enchantOnWalk.listeners.PlayerWalkListener
import org.bukkit.Bukkit.getServer
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

var gameStarted: Boolean = false
val server = getServer()
val numofench = mutableMapOf<UUID, Int>()

class EnchantOnWalk : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        gameStarted = false
        registerCommands()
        server.pluginManager.registerEvents(PlayerWalkListener(this), this)
        logger.info("Plugin Loaded")
    }

    private fun registerCommands() {
        getCommand("ping")?.setExecutor(PingCommand())
        getCommand("start")?.setExecutor(StartCommand())
        getCommand("stop")?.setExecutor(StopCommand())
        getCommand("reload")?.setExecutor(ReloadCommand(this))
        logger.info("Loaded Commands")
    }

    override fun onDisable() {
        gameStarted = false
        logger.info("Plugin Unloaded")
    }
}
