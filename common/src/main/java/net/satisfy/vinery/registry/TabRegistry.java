package net.satisfy.vinery.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.satisfy.vinery.Vinery;

@SuppressWarnings("unused")
public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Vinery.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> VINERY_TAB = CREATIVE_MODE_TABS.register("vinery", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
            .icon(() -> new ItemStack(ObjectRegistry.FERMENTATION_BARREL.get()))
            .title(Component.translatable("creativetab.vinery.tab"))
            .displayItems((parameters, out) -> {
                out.accept(new ItemStack(ObjectRegistry.CHERRY.get()));
                out.accept(new ItemStack(ObjectRegistry.ROTTEN_CHERRY.get()));
                out.accept(new ItemStack(ObjectRegistry.RED_GRAPE_SEEDS.get()));
                out.accept(new ItemStack(ObjectRegistry.RED_GRAPE.get()));
                out.accept(new ItemStack(ObjectRegistry.WHITE_GRAPE_SEEDS.get()));
                out.accept(new ItemStack(ObjectRegistry.WHITE_GRAPE.get()));
                out.accept(new ItemStack(ObjectRegistry.SAVANNA_RED_GRAPE_SEEDS.get()));
                out.accept(new ItemStack(ObjectRegistry.SAVANNA_RED_GRAPE.get()));
                out.accept(new ItemStack(ObjectRegistry.SAVANNA_WHITE_GRAPE_SEEDS.get()));
                out.accept(new ItemStack(ObjectRegistry.SAVANNA_WHITE_GRAPE.get()));
                out.accept(new ItemStack(ObjectRegistry.TAIGA_RED_GRAPE_SEEDS.get()));
                out.accept(new ItemStack(ObjectRegistry.TAIGA_RED_GRAPE.get()));
                out.accept(new ItemStack(ObjectRegistry.TAIGA_WHITE_GRAPE_SEEDS.get()));
                out.accept(new ItemStack(ObjectRegistry.TAIGA_WHITE_GRAPE.get()));
                out.accept(new ItemStack(ObjectRegistry.JUNGLE_RED_GRAPE_SEEDS.get()));
                out.accept(new ItemStack(ObjectRegistry.JUNGLE_RED_GRAPE.get()));
                out.accept(new ItemStack(ObjectRegistry.JUNGLE_WHITE_GRAPE_SEEDS.get()));
                out.accept(new ItemStack(ObjectRegistry.JUNGLE_WHITE_GRAPE.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_SAPLING.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_TREE_SAPLING.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_LEAVES.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_LEAVES.get()));
                out.accept(new ItemStack(ObjectRegistry.WHITE_GRAPE_BAG.get()));
                out.accept(new ItemStack(ObjectRegistry.RED_GRAPE_BAG.get()));
                out.accept(new ItemStack(ObjectRegistry.CHERRY_BAG.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_BAG.get()));
                out.accept(new ItemStack(ObjectRegistry.GRAPEVINE_POT.get()));
                out.accept(new ItemStack(ObjectRegistry.FERMENTATION_BARREL.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_PRESS.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_CHAIR.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_TABLE.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_DRAWER.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_CABINET.get()));
                out.accept(new ItemStack(ObjectRegistry.STRIPPED_DARK_CHERRY_LOG.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_LOG.get()));
                out.accept(new ItemStack(ObjectRegistry.STRIPPED_DARK_CHERRY_WOOD.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_WOOD.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_LOG.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_WOOD.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_BEAM.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_PLANKS.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_FLOORBOARD.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_STAIRS.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_SLAB.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_FENCE.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_FENCE_GATE.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_BUTTON.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_PRESSURE_PLATE.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_DOOR.get()));
                out.accept(new ItemStack(BoatAndSignRegistry.DARK_CHERRY_SIGN_ITEM.get()));
                out.accept(new ItemStack(BoatAndSignRegistry.DARK_CHERRY_HANGING_SIGN_ITEM.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_BARREL.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_TRAPDOOR.get()));
                out.accept(new ItemStack(ObjectRegistry.WINDOW.get()));
                out.accept(new ItemStack(ObjectRegistry.LOAM.get()));
                out.accept(new ItemStack(ObjectRegistry.LOAM_STAIRS.get()));
                out.accept(new ItemStack(ObjectRegistry.LOAM_SLAB.get()));
                out.accept(new ItemStack(ObjectRegistry.COARSE_DIRT_SLAB.get()));
                out.accept(new ItemStack(ObjectRegistry.DIRT_SLAB.get()));
                out.accept(new ItemStack(ObjectRegistry.GRASS_SLAB.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_JUICE.get()));
                out.accept(new ItemStack(ObjectRegistry.RED_GRAPEJUICE.get()));
                out.accept(new ItemStack(ObjectRegistry.WHITE_GRAPEJUICE.get()));
                out.accept(new ItemStack(ObjectRegistry.RED_TAIGA_GRAPEJUICE.get()));
                out.accept(new ItemStack(ObjectRegistry.WHITE_TAIGA_GRAPEJUICE.get()));
                out.accept(new ItemStack(ObjectRegistry.RED_JUNGLE_GRAPEJUICE.get()));
                out.accept(new ItemStack(ObjectRegistry.WHITE_JUNGLE_GRAPEJUICE.get()));
                out.accept(new ItemStack(ObjectRegistry.RED_SAVANNA_GRAPEJUICE.get()));
                out.accept(new ItemStack(ObjectRegistry.WHITE_SAVANNA_GRAPEJUICE.get()));
                out.accept(new ItemStack(ObjectRegistry.CHORUS_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.CHERRY_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.MAGNETIC_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.NOIR_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.LILITU_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.MELLOHI_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.STAL_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.STRAD_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.SOLARIS_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.BOLVAR_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.AEGIS_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.CLARK_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.CHENET_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.KELP_CIDER.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_CIDER.get()));
                out.accept(new ItemStack(ObjectRegistry.JELLIE_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.RED_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.JO_SPECIAL_MIXTURE.get()));
                out.accept(new ItemStack(ObjectRegistry.CRISTEL_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.CREEPERS_CRUSH.get()));
                out.accept(new ItemStack(ObjectRegistry.VILLAGERS_FRIGHT.get()));
                out.accept(new ItemStack(ObjectRegistry.GLOWING_WINE.get()));
                out.accept(new ItemStack(ObjectRegistry.MEAD.get()));
                out.accept(new ItemStack(ObjectRegistry.BOTTLE_MOJANG_NOIR.get()));
                out.accept(new ItemStack(ObjectRegistry.EISWEIN.get()));
                out.accept(new ItemStack(ObjectRegistry.WINE_BOTTLE.get()));
                out.accept(new ItemStack(ObjectRegistry.APPLE_MASH.get()));
                out.accept(new ItemStack(ObjectRegistry.GRAPEVINE_STEM.get()));
                out.accept(new ItemStack(ObjectRegistry.STORAGE_POT.get()));
                out.accept(new ItemStack(ObjectRegistry.FLOWER_BOX.get()));
                out.accept(new ItemStack(ObjectRegistry.FLOWER_POT_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.WINE_BOX.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_SHELF.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_CHERRY_BIG_TABLE.get()));
                out.accept(new ItemStack(ObjectRegistry.BASKET_ITEM.get()));
                out.accept(new ItemStack(ObjectRegistry.STACKABLE_LOG.get()));
                out.accept(new ItemStack(ObjectRegistry.STRAW_HAT.get()));
                out.accept(new ItemStack(ObjectRegistry.WINEMAKER_APRON.get()));
                out.accept(new ItemStack(ObjectRegistry.WINEMAKER_LEGGINGS.get()));
                out.accept(new ItemStack(ObjectRegistry.WINEMAKER_BOOTS.get()));
                out.accept(new ItemStack(ObjectRegistry.VINERY_STANDARD.get()));
                out.accept(new ItemStack(ObjectRegistry.MULE_SPAWN_EGG.get()));
                out.accept(new ItemStack(ObjectRegistry.WANDERING_WINEMAKER_SPAWN_EGG.get()));
                out.accept(new ItemStack(ObjectRegistry.OAK_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.OAK_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.OAK_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.SPRUCE_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.SPRUCE_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.SPRUCE_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.BIRCH_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.BIRCH_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.BIRCH_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.JUNGLE_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.JUNGLE_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.JUNGLE_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.ACACIA_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.ACACIA_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.ACACIA_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_OAK_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_OAK_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_OAK_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.MANGROVE_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.MANGROVE_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.MANGROVE_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.BAMBOO_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.BAMBOO_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.BAMBOO_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.CHERRY_WINE_RACK_SMALL.get()));
                out.accept(new ItemStack(ObjectRegistry.CHERRY_WINE_RACK_BIG.get()));
                out.accept(new ItemStack(ObjectRegistry.CHERRY_WINE_RACK_MID.get()));
                out.accept(new ItemStack(ObjectRegistry.OAK_LATTICE.get()));
                out.accept(new ItemStack(ObjectRegistry.SPRUCE_LATTICE.get()));
                out.accept(new ItemStack(ObjectRegistry.BIRCH_LATTICE.get()));
                out.accept(new ItemStack(ObjectRegistry.JUNGLE_LATTICE.get()));
                out.accept(new ItemStack(ObjectRegistry.ACACIA_LATTICE.get()));
                out.accept(new ItemStack(ObjectRegistry.DARK_OAK_LATTICE.get()));
                out.accept(new ItemStack(ObjectRegistry.MANGROVE_LATTICE.get()));
                out.accept(new ItemStack(ObjectRegistry.BAMBOO_LATTICE.get()));
                out.accept(new ItemStack(ObjectRegistry.CHERRY_LATTICE.get()));
            })
            .build());

    public static void init() {
        CREATIVE_MODE_TABS.register();
    }
}
