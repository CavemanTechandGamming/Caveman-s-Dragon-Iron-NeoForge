package com.caveman.cavemansdragoniron.event;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

/**
 * Client-only game events. Tooltips are driven by translation keys added in {@link com.caveman.cavemansdragoniron.datagen.ModLanguageProvider}.
 */
@EventBusSubscriber(modid = CavemansDragonIron.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.isEmpty()) {
            return;
        }
        String descriptionId = stack.getDescriptionId();
        if (!descriptionId.startsWith("item.cavemansdragoniron.") && !descriptionId.startsWith("block.cavemansdragoniron.")) {
            return;
        }
        String tooltipKey = descriptionId + ".tooltip";
        event.getToolTip().add(Component.translatable(tooltipKey).withStyle(ChatFormatting.GRAY));
    }
}
