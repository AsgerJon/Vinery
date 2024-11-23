package net.satisfy.vinery.core.compat.rei.wine;


import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.satisfy.vinery.core.Vinery;
import net.satisfy.vinery.core.compat.rei.VineryReiClientPlugin;
import net.satisfy.vinery.core.recipe.FermentationBarrelRecipe;
import net.satisfy.vinery.core.registry.ObjectRegistry;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FermentationBarrelDisplay extends BasicDisplay {

    public static final CategoryIdentifier<FermentationBarrelDisplay> FERMENTATION_BARREL_DISPLAY = CategoryIdentifier.of(Vinery.MOD_ID, "fermentation_barrel_display");

    //TODO redo
    /*
    public FermentationBarrelDisplay(FermentationBarrelRecipe recipe) {
        this(EntryIngredients.ofIngredients(VineryReiClientPlugin.ingredients(recipe, new ItemStack(ObjectRegistry.WINE_BOTTLE.get()))), Collections.singletonList(EntryIngredients.of(recipe.getResultItem(BasicDisplay.registryAccess()))), Optional.ofNullable(recipe.getId()));
    }
    */

    public FermentationBarrelDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<ResourceLocation> location) {
        super(inputs, outputs, location);
    }


    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return FERMENTATION_BARREL_DISPLAY;
    }
}
