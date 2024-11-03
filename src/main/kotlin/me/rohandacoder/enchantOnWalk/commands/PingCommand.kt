package me.rohandacoder.enchantOnWalk.commands

import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class PingCommand : CommandExecutor {
    override fun onCommand(player: CommandSender, p1: Command, p2: String, p3: Array<out String>?): Boolean {
        player.sendMessage(Component.text("Pong!"))
        return false
    }
}