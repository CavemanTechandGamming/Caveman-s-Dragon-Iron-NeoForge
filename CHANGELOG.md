# Changelog

## Feb 2, 2026

- **Project start**
  - Initial commit; Caveman's Dragon Iron mod repository created.
- **Setup**
  - Neoforge 1.21.1 project setup and configuration.

---

## Feb 3, 2026

- **Setup**
  - Continued project setup.

---

## Feb 4, 2026

- **Core materials**
  - Added **Dragon Iron Nugget**, **Dragon Iron Ingot**, and **Dragon Iron Block**.
- **Creative tab**
  - Added a creative mode tab for Caveman's Dragon Iron items and blocks.

---

## Feb 5, 2026

- **Recipes**
  - Implemented crafting/smelting recipes for Dragon Iron materials.
- **Loot tables**
  - Added loot tables for Dragon Iron items.
- **Beacon**
  - Dragon Iron blocks can be used as a **beacon base**.
- **Enderman drops**
  - Endermen now drop **Dragon Iron Nuggets**.
- **Block resistance**
  - Added the ability to make blocks **dragon immune** and **Wither immune**.
- **Localization**
  - Full multilingual support for Caveman's Dragon Iron items and blocks.

---

## Feb 6, 2026

- **Beacon payment**
  - Dragon Iron Nuggets and Dragon Iron Ingots can now be used as **payment for the Beacon** (in addition to use as base).

---

## Feb 7, 2026

- **Data generation**
  - Started work on data gen (recipes, loot, etc.).
- **Meta**
  - README file updated.

---

## Feb 9, 2026

- **Data generation**
  - Continued work on data gen.
- **Building blocks**
  - Added Dragon Iron **half slab**, **stairs**, **doors**, **trapdoors**, **buttons**, **pressure plates**, **fences**, **fence gates**, and **walls**.
  - All of these blocks are **witherproof** and **dragon proof**.

---

## Feb 10, 2026

- **Dragon Iron tools**
  - Added: Dragon Iron Sword, Pickaxe, Hammer, Axe, Shovel, Hoe.
- **Dragon Iron armor**
  - Added: Dragon Iron Helmet, Chestplate, Leggings, Boots (all with armor trim capability).
- **Set bonus**
  - Full set of Dragon Iron armor grants a set bonus.
- **Dragon Iron gear never fully breaks**
  - Tools, weapons, and armor stop at **1 durability** instead of breaking; repair at anvil as usual.

---

## Feb 11, 2026

- **Release — v0.0.1**
  - First release (early release; playable).

---

## Feb 15, 2026

- **Custom villager**
  - **Dragon Smith** villager profession and POI added.

---

## Feb 16, 2026

- **Dragon Smith trades**
  - Full Dragon Smith trade set (nuggets, blocks, ingots, tools, armor, etc.).
  - Wandering Trader trades for Dragon Iron items added.

---

## Feb 19, 2026

- **Dragon Glass**
  - Dragon Glass block and pane added (crafting, blockstates, models, tags).
- **Dragon Iron Furnace**
  - New furnace block and UI; smelts faster than vanilla furnace.
- **Config**
  - Configurable **Dragon Iron Nugget** drop chance from Endermen (with in-game config screen).
- **Meta**
  - In-game mod description updated in `neoforge.mods.toml`.
- **Dragon Iron Furnace UI**
  - Furnace UI updated to match intended design; new container texture.

---

## Feb 20, 2026

- **Dragon Smith**
  - Dragon Smith villager profession now uses the **Dragon Iron Furnace** as its work block.

---

## Feb 21, 2026

- **Lore & tool tips**
  - Added tool tips to items in game; started adding lore.
- **Meta**
  - License updated to All Rights Reserved; fixed typo in credits.

---

## Feb 22, 2026

- **Chunk Eater enchantment**
  - New enchantment that breaks a 16×16 chunk-aligned horizontal layer when mining straight up or down (pickaxe, shovel, or hammer).
  - Only applies to **pickaxe, shovel, and hammer** (no axe).
  - Drops and experience orbs in that layer are pulled toward the player.
  - Obtainable: **End City** chests (rare), **fishing** (treasure), and **Librarian** (20 emeralds + 1 book, ~5% chance when cycling).
  - Enchantment weight set to 2 (Mending parity).
  - Layer uses the **same Y** as the block you break (no off-by-one).
  - Loot tables and rarity aligned with Mending; Librarian trade is a **possible** level-1 trade (~5% when cycling), not guaranteed.
- **Hammer: pickaxe + shovel**
  - Hammer can mine both pickaxe and shovel blocks (dirt, sand, gravel, stone, ores, etc.) via `mineable_with_hammer` tag.
- **Lore books**
  - Added lore books (Volumes 1–5 + Epilogue) to loot: End City, simple dungeon, abandoned mineshaft, village library/toolsmith/weaponsmith.
  - New loot modifier and creative tab entry for lore books.
- **End City loot**
  - End City treasure chests can contain: Dragon Iron nuggets, ingots, furnace, and enchanted Dragon Iron gear (plus existing nugget/ingot/furnace modifiers).
  - Fixed bug where certain block items were still burning in lava; added End City loot “loophole” as above.
- **Lore & assets**
  - Lore docs updated (Dragon Iron, Ender Dragon, vanilla End, etc.).
  - Some textures replaced with new “professionally made” versions (e.g. dragon glass, dragon iron block, door).
- **Meta**
  - README updated; work started on custom enchantment (Chunk Eater).
- **Mod config**
  - Config file and UI improved for readability.

---

## Feb 23, 2026

- **Release — v0.0.2**
  - Latest release: Dragon Smith, Dragon Iron Furnace, Dragon Glass, Chunk Eater enchantment, lore books, End City loot, config improvements, and more.

---

### Notes

- **Dragon Iron Furnace:** Furnace textures are temporary; professional textures are in progress.
- **Dragon Iron Hoe:** The Dragon Iron hoe texture is also being fixed.
