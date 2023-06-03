package satisfyu.vinery.compat.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.rei.plugin.common.BuiltinPlugin;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import satisfyu.vinery.compat.rei.press.WinePressCategory;
import satisfyu.vinery.compat.rei.press.WinePressDisplay;
import satisfyu.vinery.compat.rei.wine.FermentationBarrelCategory;
import satisfyu.vinery.compat.rei.wine.FermentationBarrelDisplay;
import satisfyu.vinery.recipe.FermentationBarrelRecipe;
import satisfyu.vinery.registry.ObjectRegistry;

import java.util.ArrayList;
import java.util.List;


public class VineryReiClientPlugin implements REIClientPlugin {

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new FermentationBarrelCategory());
        registry.add(new WinePressCategory());

        registry.addWorkstations(FermentationBarrelDisplay.FERMENTATION_BARREL_DISPLAY, EntryStacks.of(ObjectRegistry.FERMENTATION_BARREL.get()));
        registry.addWorkstations(WinePressDisplay.WINE_PRESS_DISPLAY, EntryStacks.of(ObjectRegistry.WINE_PRESS.get()));
        registry.addWorkstations(BuiltinPlugin.FUEL, EntryStacks.of(ObjectRegistry.WOOD_FIRED_OVEN.get()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(FermentationBarrelRecipe.class, FermentationBarrelDisplay::new);
        registry.add(new WinePressDisplay());
    }

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
    }

    public static List<Ingredient> ingredients(Recipe<Container> recipe, ItemStack stack){
        List<Ingredient> l = new ArrayList<>(recipe.getIngredients());
        l.add(0, Ingredient.of(stack.getItem()));
        return l;
    }

}
