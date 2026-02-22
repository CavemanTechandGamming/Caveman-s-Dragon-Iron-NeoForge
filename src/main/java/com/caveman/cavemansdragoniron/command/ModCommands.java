package com.caveman.cavemansdragoniron.command;

import com.caveman.cavemansdragoniron.book.ModBooks;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.List;

@EventBusSubscriber(modid = com.caveman.cavemansdragoniron.CavemansDragonIron.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModCommands {

    private static final List<String> BOOK_IDS = List.of(
            "volume_1_the_fragment",
            "volume_2_the_smith_who_failed",
            "volume_3_the_eyes_that_open",
            "volume_4_the_dragon_and_the_flame",
            "volume_5_the_first_dragon_smith",
            "epilogue_the_flame_that_remains"
    );

    private static final SuggestionProvider<CommandSourceStack> BOOK_ID_SUGGESTIONS = (context, builder) -> {
        BOOK_IDS.forEach(builder::suggest);
        return builder.buildFuture();
    };

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("dragoniron")
                        .then(Commands.literal("givebook")
                                .then(Commands.argument("book_id", com.mojang.brigadier.arguments.StringArgumentType.word())
                                        .suggests(BOOK_ID_SUGGESTIONS)
                                        .executes(ctx -> giveBook(ctx, null))
                                        .then(Commands.argument("target", EntityArgument.player())
                                                .executes(ctx -> giveBook(ctx, EntityArgument.getPlayer(ctx, "target")))))));
    }

    private static int giveBook(CommandContext<CommandSourceStack> ctx, ServerPlayer targetPlayer) {
        ServerPlayer target = targetPlayer != null ? targetPlayer : ctx.getSource().getPlayer();
        if (target == null) {
            ctx.getSource().sendFailure(Component.literal("No target player. Specify a player or run as a player."));
            return 0;
        }
        String bookId = com.mojang.brigadier.arguments.StringArgumentType.getString(ctx, "book_id");
        ItemStack stack = ModBooks.getStack(bookId);
        if (stack.isEmpty()) {
            ctx.getSource().sendFailure(Component.literal("Unknown book: " + bookId));
            return 0;
        }
        if (!target.getInventory().add(stack)) {
            target.drop(stack, false);
        }
        ctx.getSource().sendSuccess(() -> Component.literal("Gave " + target.getName().getString() + " the lore book: " + bookId), true);
        return 1;
    }
}
