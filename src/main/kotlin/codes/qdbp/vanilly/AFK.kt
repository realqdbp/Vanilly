package codes.qdbp.vanilly

import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer

private val afkPlayers = mutableListOf<ServerPlayer>()

fun ServerPlayer.startAFK() {
    afkPlayers.add(this)
    this.isInvulnerable = true

    this.sendSystemMessage(Component.literal("Jetzt bist du AFK!"))
}

fun ServerPlayer.isAFK() = afkPlayers.contains(this)

fun ServerPlayer.endAFK() {

    afkPlayers.remove(this)
    this.isInvulnerable = false

    this.sendSystemMessage(Component.literal("Jetzt bist du nicht mehr AFK!"))
}