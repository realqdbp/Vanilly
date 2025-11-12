package codes.qdbp.vanilly

import com.mojang.brigadier.Command
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionProvider
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.inventory.CraftingMenu
import net.minecraft.world.level.GameType

object Commands {
    fun craft(context: CommandContext<CommandSourceStack>): Int {
        val player = context.source.player ?: return -1

        player.openMenu(craftingMenuProvider)
        return 1
    }

    fun enderchest(context: CommandContext<CommandSourceStack>): Int {

        val player = context.source.player ?: return -1
        player.openMenu(enderchestMenuProvider)

        return 1
    }

    fun freecam(context: CommandContext<CommandSourceStack>): Int {
        val player = context.source.player ?: return -1
        player.setGameMode(GameType.SPECTATOR)





        return 1
    }


    val useCraft = true
    val useEnderchest = true
    val useFreecam = true
    fun register() {

        if (useCraft) registerCmd("craft", ::craft, Triple("yay", IntegerArgumentType.integer(0, 10), TestSuggestionProvider))

        if (useEnderchest) registerCmd("enderchest", ::enderchest)
        if (useFreecam) registerCmd("freecam", ::freecam)

//        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
//            codes.qdbp.vanilly.Commands::class.declaredFunctions.forEach { function ->
//                dispatcher.register(
//                    Commands.literal(function.name)
//                        .apply {  }
//                        .executes { context ->
//                            function.call(Commands, context) as Int
//                        }
//                )
//            }
//        }
    }

    fun registerCmd(name: String, ref: Command<CommandSourceStack>, vararg arguments: Triple<String, ArgumentType<*>, SuggestionProvider<CommandSourceStack>>) {
        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            dispatcher.register(
                Commands.literal(name)
                    .apply {
                        arguments.forEach { (key, argumentType, suggestionProvider) ->
                            then(Commands.argument(key, argumentType)
                                .suggests(suggestionProvider)
                            )
                        }
                    }
                    .executes(ref)
            )
        }
    }
}

val craftingMenuProvider = object : MenuProvider {
    override fun getDisplayName() = Component.translatable("container.crafting")
    override fun createMenu(i: Int, inventory: Inventory, player: Player) =
        object : CraftingMenu (i, inventory, ContainerLevelAccess.create(player.level(), player.blockPosition())) {
            override fun stillValid(player: Player) = true
        }
}


val enderchestMenuProvider = object : MenuProvider {
    override fun getDisplayName() = Component.translatable("container.enderchest")
    override fun createMenu(i: Int, inventory: Inventory, player: Player) =
        ChestMenu.threeRows(i, inventory, player.enderChestInventory)
}