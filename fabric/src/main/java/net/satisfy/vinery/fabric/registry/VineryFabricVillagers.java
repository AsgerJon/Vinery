package net.satisfy.vinery.fabric.registry;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.satisfy.vinery.fabric.config.VineryFabricConfig;
import net.satisfy.vinery.registry.ObjectRegistry;
import net.satisfy.vinery.util.VillagerUtil;
import net.satisfy.vinery.util.VineryIdentifier;

public class VineryFabricVillagers {

    private static final VineryIdentifier WINEMAKER_POI_IDENTIFIER = new VineryIdentifier("winemaker_poi");
    public static final PoiType WINEMAKER_POI;
    public static final VillagerProfession WINEMAKER;

    static {
        WINEMAKER_POI = PointOfInterestHelper.register(
                WINEMAKER_POI_IDENTIFIER,
                1,
                12,
                ObjectRegistry.APPLE_PRESS.get()
        );

        WINEMAKER = Registry.register(
                BuiltInRegistries.VILLAGER_PROFESSION,
                new ResourceLocation("vinery", "winemaker"),
                VillagerProfessionBuilder.create()
                        .id(new ResourceLocation("vinery", "winemaker"))
                        .workstation(ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, WINEMAKER_POI_IDENTIFIER))
                        .build()
        );
    }

    public static void registerPOIAndProfession() {
        // Stelle sicher, dass die statische Initialisierung ausgeführt wurde
    }

    public static void init(MinecraftServer server) {
        VineryFabricConfig config = AutoConfig.getConfigHolder(VineryFabricConfig.class).getConfig();
        RegistryAccess registryAccess = server.registryAccess();

        registerTradesForLevel(config.villager.level1, 1, registryAccess);
        registerTradesForLevel(config.villager.level2, 2, registryAccess);
        registerTradesForLevel(config.villager.level3, 3, registryAccess);
        registerTradesForLevel(config.villager.level4, 4, registryAccess);
        registerTradesForLevel(config.villager.level5, 5, registryAccess);
    }

    private static void registerTradesForLevel(VineryFabricConfig.VillagerSettings.TradeLevelSettings tradeLevelSettings, int level, RegistryAccess registryAccess) {
        TradeOfferHelper.registerVillagerOffers(WINEMAKER, level, factories -> {
            for (VineryFabricConfig.VillagerSettings.TradeEntry entry : tradeLevelSettings.trades) {
                String[] parts = entry.item.split(":");
                if (parts.length >= 2) {
                    String modId = parts[0];
                    String itemId = parts[1];
                    ResourceLocation rl = new ResourceLocation(modId, itemId);
                    Item item = registryAccess.registryOrThrow(Registries.ITEM).get(rl);
                    if (item != null) {
                        if (entry.type == VineryFabricConfig.VillagerSettings.TradeType.BUY) {
                            factories.add(new VillagerUtil.BuyForOneEmeraldFactory(item, entry.price, entry.maxUses, entry.experience));
                        } else if (entry.type == VineryFabricConfig.VillagerSettings.TradeType.SELL) {
                            factories.add(new VillagerUtil.SellItemFactory(item, entry.price, entry.maxUses, entry.experience));
                        }
                    } else {
                        System.err.println("Vinery Villager Trade Item not found: " + rl);
                    }
                }
            }
        });
    }
}
