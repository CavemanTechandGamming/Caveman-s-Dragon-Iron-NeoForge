package com.caveman.cavemansdragoniron.client.gui;

import com.caveman.cavemansdragoniron.CavemansDragonIron;
import com.caveman.cavemansdragoniron.Config;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Custom config screen for the dragon iron nugget drop chance: number input with −5 / +5 buttons instead of a slider.
 */
public class ModConfigScreen extends Screen {
    private static final Component LABEL = Component.translatable("config.cavemansdragoniron.common.dragonIronNuggetEndermanDropChancePercent");
    private static final Component DESCRIPTION = Component.translatable("config.cavemansdragoniron.common.dragonIronNuggetEndermanDropChancePercent.tooltip");
    private static final Component TOOLTIP_SHORT = Component.translatable("config.cavemansdragoniron.common.dragonIronNuggetEndermanDropChancePercent.tooltipShort");
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int STEP = 5;
    private static final int FIELD_WIDTH = 50;
    private static final int BUTTON_WIDTH = 36;
    private static final int SPACING = 4;
    private static final int DESCRIPTION_WIDTH = 260;

    private static final String CONFIG_HEADER =
            "# Caveman's Dragon Iron - common config (editable)\n"
            + "# Base chance (0-100%) for Endermen to drop a dragon iron nugget when killed without Looting. Looting adds 10% per level.\n";

    private final Screen parent;
    private EditBox valueField;
    private int currentValue;

    public ModConfigScreen(Screen parent) {
        super(Component.translatable("config.cavemansdragoniron.title"));
        this.parent = parent;
        this.currentValue = Config.DRAGON_IRON_NUGGET_ENDERMAN_DROP_CHANCE_PERCENT.get();
    }

    @Override
    protected void init() {
        super.init();
        int centerX = width / 2;
        int totalRowWidth = BUTTON_WIDTH + SPACING + FIELD_WIDTH + SPACING + BUTTON_WIDTH;
        int rowLeft = centerX - totalRowWidth / 2;
        int rowY = height / 2 - 50;

        addRenderableWidget(Button.builder(Component.literal("−5"), b -> step(-STEP))
                .bounds(rowLeft, rowY, BUTTON_WIDTH, 20)
                .build());

        valueField = new EditBox(font, rowLeft + BUTTON_WIDTH + SPACING, rowY, FIELD_WIDTH, 20, Component.literal(""));
        valueField.setValue(String.valueOf(currentValue));
        valueField.setFilter(s -> s.isEmpty() || s.matches("\\d{0,3}"));
        valueField.setResponder(s -> {
            if (!s.isEmpty()) {
                try {
                    int v = Integer.parseInt(s);
                    currentValue = Math.min(MAX, Math.max(MIN, v));
                } catch (NumberFormatException ignored) {}
                }
        });
        addRenderableWidget(valueField);

        addRenderableWidget(Button.builder(Component.literal("+5"), b -> step(STEP))
                .bounds(rowLeft + BUTTON_WIDTH + SPACING + FIELD_WIDTH + SPACING, rowY, BUTTON_WIDTH, 20)
                .build());

        addRenderableWidget(Button.builder(Component.translatable("gui.done"), b -> saveAndClose())
                .bounds(centerX - 102, height / 2 + 50, 100, 20)
                .build());
        addRenderableWidget(Button.builder(Component.translatable("gui.cancel"), b -> onClose())
                .bounds(centerX + 2, height / 2 + 50, 100, 20)
                .build());
    }

    private void step(int delta) {
        currentValue = Math.min(MAX, Math.max(MIN, currentValue + delta));
        valueField.setValue(String.valueOf(currentValue));
    }

    private void saveAndClose() {
        try {
            if (!valueField.getValue().isEmpty()) {
                currentValue = Math.min(MAX, Math.max(MIN, Integer.parseInt(valueField.getValue())));
            }
        } catch (NumberFormatException ignored) {
            currentValue = Config.DRAGON_IRON_NUGGET_ENDERMAN_DROP_CHANCE_PERCENT.get();
        }
        saveConfigToFile(currentValue);
        onClose();
    }

    private void saveConfigToFile(int value) {
        if (minecraft == null) return;
        Path configDir = minecraft.gameDirectory.toPath().resolve("config");
        try {
            if (!Files.exists(configDir)) {
                Files.createDirectories(configDir);
            }
            Path configFile = configDir.resolve(CavemansDragonIron.MOD_ID + "-common.toml");
            String contents = CONFIG_HEADER + "dragonIronNuggetEndermanDropChancePercent = " + value + "\n";
            Files.writeString(configFile, contents, StandardCharsets.UTF_8);
        } catch (IOException e) {
            CavemansDragonIron.LOGGER.warn("Could not save config: {}", e.getMessage());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(font, title, width / 2, 20, 0xFFFFFF);
        // Label above the control row
        guiGraphics.drawCenteredString(font, LABEL, width / 2, height / 2 - 72, 0xE0E0E0);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        // Description below the control row, drawn on top so it's never covered (bright so it's readable)
        List<FormattedCharSequence> descriptionLines = font.split(DESCRIPTION, DESCRIPTION_WIDTH);
        int descY = height / 2 - 20;
        for (FormattedCharSequence line : descriptionLines) {
            guiGraphics.drawCenteredString(font, line, width / 2, descY, 0xE0E0E0);
            descY += font.lineHeight + 2;
        }
        // Tooltip when hovering over the label, value field, or −5/+5 buttons
        int rowTop = height / 2 - 72;
        int rowBottom = height / 2 - 30;
        int totalRow = BUTTON_WIDTH + SPACING + FIELD_WIDTH + SPACING + BUTTON_WIDTH;
        int rLeft = width / 2 - totalRow / 2;
        int rRight = rLeft + totalRow;
        boolean hoverControl = mouseY >= rowTop && mouseY <= rowBottom && mouseX >= rLeft - 20 && mouseX <= rRight + 20;
        if (hoverControl) {
            guiGraphics.renderTooltip(font, TOOLTIP_SHORT, mouseX, mouseY);
        }
    }

    @Override
    public void onClose() {
        if (minecraft != null) {
            minecraft.setScreen(parent);
        }
    }
}
