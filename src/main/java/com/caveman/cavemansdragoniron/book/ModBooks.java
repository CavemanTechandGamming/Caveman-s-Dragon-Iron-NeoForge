package com.caveman.cavemansdragoniron.book;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.WrittenBookContent;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Registry of lore books for the Dragon Iron mod.
 * Each book is a written book with fixed title, author, and pages.
 * Use the book id with {@link #getStack(String)} to obtain an ItemStack for loot or creative.
 */
public final class ModBooks {

    private static final String AUTHOR = "The First Dragon Smith";

    private static final Map<String, Supplier<ItemStack>> BOOKS = Map.ofEntries(
            Map.entry("volume_1_the_fragment", ModBooks::createVolume1TheFragment),
            Map.entry("volume_2_the_smith_who_failed", ModBooks::createVolume2TheSmithWhoFailed),
            Map.entry("volume_3_the_eyes_that_open", ModBooks::createVolume3TheEyesThatOpen),
            Map.entry("volume_4_the_dragon_and_the_flame", ModBooks::createVolume4TheDragonAndTheFlame),
            Map.entry("volume_5_the_first_dragon_smith", ModBooks::createVolume5TheFirstDragonSmith),
            Map.entry("epilogue_the_flame_that_remains", ModBooks::createEpilogueTheFlameThatRemains)
    );

    private ModBooks() {}

    /**
     * Returns a new ItemStack of the lore book with the given id, or ItemStack.EMPTY if unknown.
     */
    public static ItemStack getStack(String bookId) {
        Supplier<ItemStack> supplier = BOOKS.get(bookId);
        return supplier != null ? supplier.get() : ItemStack.EMPTY;
    }

    /**
     * Returns true if the given book id is registered.
     */
    public static boolean hasBook(String bookId) {
        return BOOKS.containsKey(bookId);
    }

    private static ItemStack createVolume1TheFragment() {
        String title = "Volume I — The Fragment";
        List<Filterable<Component>> pages = List.of(
                page("I was never meant to become a smith. My hands were made for the road, not the forge. I walked beneath open skies, slept beside dying campfires, and chased rumors into places wiser men avoided. Adventure was enough. It was all I knew."),
                page("The night everything changed began like any other. Rain fell in steady sheets, hissing against the coals of my fire. The forest was quiet in that unnatural way that warns of something watching, though nothing yet moves."),
                page("I felt it before I saw it. That crawling sense at the edge of thought, as though the world itself had shifted slightly out of place. When I turned, it stood between the trees, impossibly tall, its eyes glowing with cold violet light."),
                page("An Enderman. I had seen them from afar before, wandering plains or standing motionless in shallow water as if confused by its touch. But never this close. Never where I could hear its breath, slow and steady, like wind through hollow stone."),
                page("I tried not to look directly at it. Every traveler knows the rule. They are peaceful, until they are not. But fear makes fools of us all. My eyes met its face, if face it could be called, and in that instant the air fractured."),
                page("It screamed—not with sound, but with presence. One moment it stood beneath the trees. The next it stood behind me. Steel left its sheath by instinct alone. The fight was chaos, every swing landing against a place it no longer was."),
                page("I do not know how long it lasted. Exhaustion claimed it before it claimed me. When the creature finally collapsed, it did not fall like flesh and bone. It simply ceased to stand, leaving behind only what it carried."),
                page("An Ender Pearl lay in the mud, faintly warm, humming with power I did not understand. But beside it rested something else. Something small. Something dark. A fragment of metal unlike any I had ever seen."),
                page("It was heavier than iron should be, yet smaller than a coin. Its surface was smooth, untouched by rust or wear. Rain struck it without leaving a mark. Even in the cold mud, it seemed unchanged by the world around it."),
                page("I turned it in the firelight, expecting it to glow as iron does when heated. It did not. It reflected nothing. The flames licked across its surface as though denied entry, as though the metal had already endured a greater fire."),
                page("I left it buried in the coals while I slept, certain that by morning it would soften, or crack, or change in some small way. All metals yield to time and flame. It is the first lesson every smith learns."),
                page("Morning came. The fire was ash. The fragment remained.\n\nUntouched.\n\nUnchanged.\n\nUnmoved by the heat that had consumed the wood around it."),
                page("In that moment, I understood something simple and terrifying. This metal did not belong to my world. It did not obey its rules. It had come from somewhere else. Somewhere beyond furnace or forge."),
                page("I wrapped it carefully and carried it with me. Not as treasure, but as a question. And questions, I had learned, were far more dangerous than gold."),
                page("I did not yet know it, but that fragment would lead me beyond the Overworld, beyond fire, beyond death itself.\n\nIt was the first piece of Dragon Iron.")
        );
        return createBook(title, pages);
    }

    private static ItemStack createVolume2TheSmithWhoFailed() {
        String title = "Volume II — The Smith Who Failed";
        List<Filterable<Component>> pages = List.of(
                page("I traveled for three days before I found a village with a proper forge. The fragment never left my pack. Its weight was constant, though it was small. I began to feel as though I carried not metal, but something waiting."),
                page("The village smith was an old man, his arms marked by burns and scars earned honestly. His forge roared with familiar heat, the smell of coal and iron thick in the air. He glanced at me only briefly before returning to his work."),
                page("When I showed him the fragment, his hands slowed. He did not speak at first. He simply took it, turning it beneath the forge light. His expression shifted—not to greed, but to confusion."),
                page("He placed it upon his anvil and struck it with measured force. The ringing sound was wrong. Iron sings when struck. This did not sing. It answered with a dull, final tone, as though the hammer itself had erred."),
                page("He struck harder. Then harder still. The hammerhead chipped before the fragment showed even the faintest imperfection. The smith frowned, not in anger, but in disbelief."),
                page("He moved it into the furnace, burying it beneath white-hot coals. The bellows roared. Flame consumed the air until the forge glowed brighter than day. We waited. Minutes stretched into an hour."),
                page("When he removed it, the fragment was unchanged. Not glowing. Not softened. Not even warm to the touch. He dropped it onto the anvil again, slower this time, as though it might answer differently."),
                page("\"It does not belong to this fire,\" he said quietly.\n\nI asked him what that meant.\n\nHe did not answer immediately."),
                page("Instead, he told me of metals he had worked in his life. Iron from the deepest caves. Gold from desert temples. Even scraps of ancient alloy recovered from ruined bastions far below the world."),
                page("\"All things yield,\" he said. \"Given enough heat. Enough time.\"\n\nHe turned the fragment once more beneath the light.\n\n\"This does not yield.\""),
                page("I told him where I had found it. His eyes shifted at the mention of the Enderman. He did not question me. Men who live by the forge understand that truth often arrives in impossible forms."),
                page("\"If it comes from them,\" he said, \"then it comes from where they belong.\"\n\nI asked where that was.\n\nHe only shook his head."),
                page("\"Their home is not ours. Their fire is not ours. Whatever shaped this metal did so beyond our reach.\"\n\nHe placed it back into my hand.\n\nIt felt heavier than before."),
                page("\"Find its origin,\" he said. \"Only then will you find the flame that can shape it.\"\n\nI left the village the next morning, carrying more than metal.\n\nI carried purpose."),
                page("I began to hunt the Endermen.\n\nNot for pearls.\n\nBut for answers.")
        );
        return createBook(title, pages);
    }

    private static ItemStack createVolume3TheEyesThatOpen() {
        String title = "Volume III — The Eyes That Open";
        List<Filterable<Component>> pages = List.of(
                page("Endermen are not easily hunted. They do not wander like cattle nor stalk like wolves. They appear where they choose and vanish when pressed. To pursue them is to chase something that does not follow the rules of distance."),
                page("I learned to fight without staring. To strike at shadows and listen for the crack of displaced air. Many nights ended in bruises and near death. Many days ended with nothing at all."),
                page("But sometimes, when one fell, it left behind more than a pearl.\n\nAnother fragment.\n\nDark. Heavy. Untouched by flame."),
                page("The pearls began to gather in my pack. Smooth, warm, faintly pulsing as if alive. I did not yet know their full purpose, only that they bent space when thrown, tearing me forward through the air."),
                page("It happened by accident. In frustration, I hurled one high into the sky. Instead of falling, it hovered—pulling against my hand, straining toward some unseen point beyond the horizon."),
                page("When it shattered, it burst in a direction.\n\nNot random.\n\nNot chaotic.\n\nGuided."),
                page("I gathered more. I tested again. Each pearl that rose and broke pointed the same way, as though something beneath the earth called to them.\n\nThe fragments of metal lay heavy in my pack.\n\nWaiting."),
                page("The trail led across plains and rivers, through forests and deserts, always pulling downward as much as forward. Each shattered pearl narrowed the path.\n\nI began to suspect what the smith had meant."),
                page("The Endermen belonged somewhere.\n\nAnd the pearls remembered."),
                page("When the final pearl burst above a patch of quiet stone, it did not travel far. It sank, as if the answer lay beneath my feet.\n\nSo I dug."),
                page("Stone gave way to brick.\n\nCracked. Moss-covered. Ancient.\n\nA corridor stretched into darkness, built by hands long gone from this world."),
                page("The stronghold felt older than ruin. Libraries stood untouched, their books long since looted or decayed. Cells and stairwells twisted without pattern. It was not abandoned.\n\nIt was forgotten."),
                page("At its heart stood a frame of strange stone blocks, each set with an empty socket. I felt the pearls stir in my pack, resonating with the structure as though completing something long unfinished."),
                page("One by one, I placed them.\n\nEach Eye settled into its socket with a sound like distant thunder. When the final one locked into place, the air shifted.\n\nThe portal ignited."),
                page("It did not blaze like flame. It did not shimmer like water.\n\nIt became a window into endless darkness.\n\nAnd I understood.\n\nIf the metal refused our fire—\n\nThen I would seek the fire it obeyed.\n\nI stepped through.")
        );
        return createBook(title, pages);
    }

    private static ItemStack createVolume4TheDragonAndTheFlame() {
        String title = "Volume IV — The Dragon and the Flame";
        List<Filterable<Component>> pages = List.of(
                page("The fall was silent.\n\nThere was no wind as I emerged, no sound of arrival. I stood upon pale stone beneath a sky without sun or stars. The air felt thinner—not colder, not warmer—simply absent of comfort."),
                page("The island floated in endless void. Beyond its edges was nothing. No distant horizon. No land beyond sight. Only darkness stretching forever.\n\nEndermen walked freely here.\n\nThey did not watch me.\n\nI was beneath their notice."),
                page("Then the sky trembled.\n\nA shadow crossed overhead, vast and deliberate. The sound that followed was not a roar but a distortion, like stone grinding against the bones of the world itself.\n\nThe dragon descended."),
                page("It circled the obsidian pillars that rose like monuments around the island. Atop each pillar burned a crystal of blinding light. When the dragon passed near them, its wounds closed as if time itself reversed."),
                page("Its eyes were violet, like the Endermen's. Its wings did not beat the air so much as command it. When it exhaled, the breath it released was not simple flame. It was luminous, corrosive, wrong."),
                page("Where that breath struck stone, the ground shimmered with lingering energy. It was not consumed like wood in fire. It was altered—scarred by something deeper than heat.\n\nI watched carefully.\n\nEven in battle, I watched."),
                page("The fight was long. I shattered crystals atop pillars while the dragon tore through stone and air alike. It struck with body and breath, a guardian bound to this place.\n\nThe void waited below for any mistake."),
                page("When the final crystal fell and the dragon could no longer heal, it descended in fury. We clashed upon the island's center, steel against scale, will against ancient force.\n\nAnd then—\n\nIt faltered."),
                page("The dragon did not collapse like flesh. It rose.\n\nLight erupted from within it, beams piercing sky and stone. Its body fractured into radiance, dissolving into nothingness without decay.\n\nNo corpse remained.\n\nOnly silence."),
                page("At the center of the island, a portal of return formed from bedrock and light. Above it rested an egg, unmoving and silent, as if death here was not final but cyclical.\n\nBut something else had changed."),
                page("Around the island's edge, gateways shimmered into existence—small portals suspended in frames of bedrock. They pulsed faintly, inviting passage across the void to lands unseen.\n\nI did not hesitate."),
                page("The outer islands stretched farther than sight could measure. Chorus plants grew in clusters, their fruit shifting space when consumed. The End felt vast—larger than the central island had suggested.\n\nAnd then I saw them."),
                page("Cities.\n\nRising from the pale stone like monuments to a vanished age. Towers of purpur and end stone brick. Bridges suspended over nothing. Windows stained violet.\n\nNo builders walked their halls."),
                page("Shulkers guarded the corridors, snapping open and closed like living locks. But beyond them lay treasures untouched by time.\n\nAnd deep within one tower—\n\nI found heat."),
                page("A furnace stood in a chamber of end stone and purpur.\n\nIts flame burned violet.\n\nIt did not flicker.\n\nIt did not consume fuel.\n\nIt endured.\n\nI placed a fragment of the metal inside.\n\nAnd for the first time—\n\nIt changed.")
        );
        return createBook(title, pages);
    }

    private static ItemStack createVolume5TheFirstDragonSmith() {
        String title = "Volume V — The First Dragon Smith";
        List<Filterable<Component>> pages = List.of(
                page("The fragment did not melt.\n\nIt did not glow as iron does.\n\nInstead, it softened slowly, as though remembering something it had once been. The violet flame did not consume it. It awakened it.\n\nI watched without breathing."),
                page("The metal bent beneath my tools with resistance, but not refusal. It yielded only to this flame, as though all lesser fires had been unworthy of the task. The smith had been right.\n\nIt did not belong to our world."),
                page("This forge was different from any I had known. No coal fed it. No bellows strengthened it. The flame endured without source, steady and eternal, as though drawn from something beyond the furnace itself."),
                page("I placed another fragment within. Again, the flame answered. Again, the metal yielded. Nuggets became ingots beneath this impossible fire. Shape emerged where before there had only been resistance.\n\nThe dragon's flame was the key."),
                page("I understood then what I had witnessed on the central island. The dragon's breath was not destruction alone. It was transformation. Its fire did not merely end things.\n\nIt remade them."),
                page("This metal had been born in that flame.\n\nTouched by it.\n\nChanged by it.\n\nNo ordinary furnace could replicate what the dragon itself had created.\n\nBut this forge could."),
                page("I worked until exhaustion claimed me, shaping what fragments I had carried across worlds. Tools formed beneath my hands, their edges perfect, their surfaces unmarred by heat or time.\n\nThey did not cool.\n\nThey endured."),
                page("When I finished, I extinguished nothing.\n\nThe flame did not require me.\n\nIt had burned before my arrival.\n\nIt would burn after my departure."),
                page("I returned to the Overworld with ingots in hand and knowledge in mind. The village smith met me again beside his forge, his eyes narrowing as I placed the metal before him.\n\nHe knew immediately."),
                page("Together, we rebuilt what I had seen.\n\nNot perfectly.\n\nNot completely.\n\nBut enough.\n\nWhen the new forge ignited, its flame burned violet, just as the original had.\n\nThe metal yielded."),
                page("Word spread slowly at first. Then quickly. Smiths came to see it. Adventurers came to trade for it. All asked the same question.\n\nWhat was this metal?\n\nI gave them the only name that fit."),
                page("Dragon Iron.\n\nForged in the fire of the Ender Dragon.\n\nCarried to our world by Endermen, whose presence bridges realms.\n\nWorked only in the flame that first shaped it."),
                page("Even now, Endermen walk our lands. And sometimes, when they fall, they carry fragments of that distant fire with them. Without them, Dragon Iron would never reach us.\n\nThey are the unwitting bearers of its legacy."),
                page("I was once an adventurer who sought treasure.\n\nInstead, I found truth.\n\nMetal that refused our fire.\n\nFlame that refused our world.\n\nAnd the knowledge to bring the two together."),
                page("I was never meant to become a smith.\n\nBut I walked into the End and returned with its flame.\n\nAnd so long as that flame endures—\n\nSo too will Dragon Iron.\n\nAnd I will forever be its first keeper.\n\nThe first Dragon Smith.")
        );
        return createBook(title, pages);
    }

    private static ItemStack createEpilogueTheFlameThatRemains() {
        String title = "Epilogue — The Flame That Remains";
        List<Filterable<Component>> pages = List.of(
                page("Years have passed since I first stepped through the portal.\n\nThe stronghold still stands beneath the earth. The End still waits beyond its frame. The dragon still returns when called, as it always has.\n\nThe world endures its cycles.\n\nAnd so do I."),
                page("The forge burns each night.\n\nIts violet flame no longer feels alien to me. It does not roar. It does not demand fuel. It simply exists — steady, patient, unending.\n\nLike the End itself."),
                page("Dragon Iron is no longer a rumor.\n\nIt rests now in the hands of those who understand its origin. Not many. Never many.\n\nSome metals are too dangerous to spread without wisdom."),
                page("Adventurers still come to my door.\n\nThey speak of tall figures that carry strange fragments. Of pearls that guide them across continents. Of strongholds buried beneath forgotten stone.\n\nThey ask if the stories are true.\n\nI never answer directly."),
                page("Instead, I ask them a question:\n\n\"Are you willing to walk where the sky ends?\"\n\nMost laugh.\n\nSome leave.\n\nA rare few do not.\n\nThose are the ones who return changed."),
                page("The Endermen still wander our world.\n\nThey do not age.\n\nThey do not build.\n\nThey carry blocks and fragments from places beyond our sight.\n\nPerhaps they are scavengers.\n\nPerhaps they are survivors.\n\nPerhaps they are witnesses."),
                page("I have come to believe something simple.\n\nThe End is not a place of death.\n\nIt is a place of permanence.\n\nNothing grows there.\n\nNothing decays.\n\nThings simply remain."),
                page("The dragon's flame was never meant to destroy.\n\nIt preserves.\n\nIt transforms.\n\nIt remembers.\n\nAnd Dragon Iron remembers with it."),
                page("I have not returned to the End in many years.\n\nI no longer need to.\n\nThe lesson it offered was never about conquest.\n\nIt was about understanding."),
                page("The egg still rests upon its bedrock pedestal when the dragon falls.\n\nThe portal still opens.\n\nThe gateways still shimmer into existence.\n\nThe cycle continues, untouched by my presence.\n\nAs it should."),
                page("I am older now.\n\nMy hands are scarred by more than heat. They are marked by choice.\n\nI chose to follow a fragment.\n\nI chose to open a door.\n\nI chose to carry something back."),
                page("If one day I no longer rise to tend the forge, the flame will not die.\n\nIt does not depend on me.\n\nIt never did.\n\nIt was here before I understood it.\n\nIt will remain long after I am gone."),
                page("Perhaps another will find a fragment carried by an Enderman.\n\nPerhaps another will seek a smith and be told \"no.\"\n\nPerhaps another will follow the eyes to the stone frame below the earth.\n\nThe path remains."),
                page("If you are reading this, then you stand at the edge of that path.\n\nKnow this:\n\nThe End does not call loudly.\n\nIt waits."),
                page("And should you choose to answer it—\n\nStep forward without fear.\n\nThe flame will be there.\n\nIt always is.")
        );
        return createBook(title, pages);
    }

    private static ItemStack createBook(String title, List<Filterable<Component>> pages) {
        WrittenBookContent content = new WrittenBookContent(
                Filterable.passThrough(title),
                AUTHOR,
                0,
                pages,
                true
        );
        ItemStack stack = new ItemStack(Items.WRITTEN_BOOK);
        stack.set(DataComponents.WRITTEN_BOOK_CONTENT, content);
        return stack;
    }

    private static Filterable<Component> page(String text) {
        return Filterable.passThrough(Component.literal(text));
    }
}
