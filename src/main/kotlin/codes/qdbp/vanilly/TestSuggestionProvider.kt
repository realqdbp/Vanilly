//package codes.qdbp.vanilly
//
//import com.mojang.brigadier.context.CommandContext
//import com.mojang.brigadier.suggestion.SuggestionProvider
//import com.mojang.brigadier.suggestion.Suggestions
//import com.mojang.brigadier.suggestion.SuggestionsBuilder
//import net.minecraft.commands.CommandSourceStack
//import java.util.concurrent.CompletableFuture
//
//object TestSuggestionProvider : SuggestionProvider<CommandSourceStack> {
//    override fun getSuggestions(
//        context: CommandContext<CommandSourceStack>,
//        builder: SuggestionsBuilder
//    ): CompletableFuture<Suggestions> {
//        (1..10).forEach { builder.suggest(it) }
//        return builder.buildFuture()
//    }
//}