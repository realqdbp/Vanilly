package codes.qdbp.vanilly

import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer

fun ServerPlayer.startAFK() {
    this.sendSystemMessage(Component.literal("Jetzt bist du afk"))
}