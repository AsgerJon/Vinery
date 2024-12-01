package net.satisfy.vinery.core.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.satisfy.vinery.core.Vinery;

public class VineryWoodType {

    public static final WoodType DARK_CHERRY = WoodType.register(new WoodType(new ResourceLocation(Vinery.MOD_ID, "dark_cherry").toString(), BlockSetType.OAK));
}
