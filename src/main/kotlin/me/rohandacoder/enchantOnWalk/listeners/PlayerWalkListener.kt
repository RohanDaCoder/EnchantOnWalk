package me.rohandacoder.enchantOnWalk.listeners

import me.rohandacoder.enchantOnWalk.EnchantOnWalk
import me.rohandacoder.enchantOnWalk.gameStarted
import me.rohandacoder.enchantOnWalk.numofench
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.util.Vector

class PlayerWalkListener(private val plugin: EnchantOnWalk) : Listener {
    private val lastLocations = mutableMapOf<Player, Vector>()

    @EventHandler
    fun onPlayerWalk(event: PlayerMoveEvent) {
        if (!gameStarted) return
        val player = event.player
        val currentLocation = player.location.toVector()

        if (lastLocations[player] != currentLocation) {
            enchantItem(player)
            lastLocations[player] = currentLocation
        }
    }

    private fun enchantItem(player: Player) {
        player.inventory.contents.forEach { item ->
            if (item?.type?.isAir?.not() == true) {
                val randomEnchantment = Enchantment.values().random()
                val removeSilkTouch = plugin.config.getBoolean("removeSilkTouch", false)
                if (removeSilkTouch && randomEnchantment == Enchantment.SILK_TOUCH) return

                val meta = item.itemMeta ?: return
                val currentLevel = meta.getEnchantLevel(randomEnchantment)
                meta.addEnchant(randomEnchantment, currentLevel + 1, true)
                item.itemMeta = meta
                addOneToCounter(player)
            }
        }
    }

    private fun addOneToCounter(player: Player) {
        val uuid = player.uniqueId
        val newEnchantmentCount = (numofench[uuid] ?: 0) + 1
        numofench[uuid] = newEnchantmentCount

        val milestones = listOf(100, 500, 1000, 2000, 3000, 4000, 5000, 6000)
        if (newEnchantmentCount in milestones) {
            sendEnchantmentCountMessage(player, newEnchantmentCount)
        }
    }

    private fun sendEnchantmentCountMessage(player: Player, count: Int) {
        val message = Component.text().append(
            Component.text("[", NamedTextColor.LIGHT_PURPLE),
            Component.text("!", NamedTextColor.LIGHT_PURPLE),
            Component.text("] ", NamedTextColor.LIGHT_PURPLE),
            Component.text("You have been enchanted ", NamedTextColor.WHITE),
            Component.text("$count", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
            Component.text(" times!", NamedTextColor.WHITE)
        )
        player.sendMessage(message)
    }
}
