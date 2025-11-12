package codes.qdbp.vanilly

import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.GameType


fun ServerPlayer.startFreecam() {
    this.setGameMode(GameType.SPECTATOR)
    this.sendSystemMessage(Component.literal("Du kannst mit /fc <welt> zwischen Welten wechseln."))
}

fun ServerPlayer.stopFreecam(){
    this.setGameMode(GameType.SURVIVAL)
}

fun registerFreecam() {


}