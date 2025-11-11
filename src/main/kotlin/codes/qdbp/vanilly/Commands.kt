package codes.qdbp.vanilly

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.inventory.CraftingMenu

object Commands {
    fun craft(context: CommandContext<CommandSourceStack>): Int {
        val player = context.source.player ?: return -1

        player.openMenu(craftingMenuProvider)
        return 1
    }


    fun demo(context: CommandContext<CommandSourceStack>): Int {
        context.source.sendSuccess({ Component.literal("a")}, false)
        return 1
    }

//    CommandRegistrationCallback.EVENT.register { dispatcher, buildContext, _ ->
//        dispatcher.register(Commands.literal("test_command")
//            .then(Commands.argument("value", ResourceArgument.resource(buildContext, Registries.ENTITY_TYPE))
//                .suggests(SuggestionProviders.cast(SuggestionProviders.SUMMONABLE_ENTITIES))
//                .executes(HelloCommand::testCommand)
//            )
//        )
//    }

    fun register() {

    }


}

val craftingMenuProvider = object : MenuProvider {
    override fun getDisplayName() = Component.translatable("container.crafting")
    override fun createMenu(i: Int, inventory: Inventory, player: Player) =
        object : CraftingMenu (i, inventory, ContainerLevelAccess.create(player.level(), player.blockPosition())) {
            override fun stillValid(player: Player) = true
        }
}