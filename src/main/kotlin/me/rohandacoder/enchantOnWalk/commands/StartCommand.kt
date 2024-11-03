package me.rohandacoder.enchantOnWalk.commands

import me.rohandacoder.enchantOnWalk.gameStarted
import me.rohandacoder.enchantOnWalk.server
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class StartCommand : CommandExecutor {
    override fun onCommand(player: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        if (player.hasPermission("enchantonwalk.admin") or player.isOp) {
            server.sendMessage(
                Component.text("[EnchantOnWalk] ").append(
                    Component.text("The Game has been started by ${player.name}").decorate(TextDecoration.BOLD)
                        .color(NamedTextColor.YELLOW)
                )
            )
            gameStarted = true
            return false
        } else {
            player.sendMessage(
                Component.text("You Don't Have Permissions To Execute This Command.").color(NamedTextColor.RED)
            )
            return false
        }
    }
}