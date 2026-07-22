package codes.qdbp.vanilly.commands

import codes.qdbp.vanilly.components.SkipNight
import com.mojang.brigadier.Command
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.server.level.ServerPlayer

val skipNightCmd: LiteralArgumentBuilder<CommandSourceStack> = Commands.literal("skipNight")
    .executes {
        SkipNight.shouldForward = true

        Command.SINGLE_SUCCESS
    }