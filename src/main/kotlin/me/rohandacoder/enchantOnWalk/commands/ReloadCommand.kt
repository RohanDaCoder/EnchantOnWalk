package me.rohandacoder.enchantOnWalk.commands

import me.rohandacoder.enchantOnWalk.EnchantOnWalk
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ReloadCommand(private val plugin: EnchantOnWalk) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("enchantonwalk.reload")) {
            plugin.reloadConfig()
            Component.text("Configuration reloaded.").color(NamedTextColor.GREEN)
        } else {
            sender.sendMessage(
                Component.text("You do not have permission to reload the configuration.").color(NamedTextColor.RED)
            )
        }
        return true
    }
}
