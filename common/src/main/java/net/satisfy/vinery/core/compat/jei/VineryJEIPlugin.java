package net.satisfy.vinery.core.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.satisfy.vinery.core.compat.jei.category.ApplePressMashingCategory;
import net.satisfy.vinery.core.compat.jei.category.FermentationBarrelCategory;
import net.satisfy.vinery.core.compat.jei.category.ApplePressFermentingCategory; // Import der neuen Kategorie
import net.satisfy.vinery.core.compat.jei.transfer.FermentationTransferInfo;
import net.satisfy.vinery.core.recipe.ApplePressMashingRecipe;
import net.satisfy.vinery.core.recipe.FermentationBarrelRecipe;
import net.satisfy.vinery.core.recipe.ApplePressFermentingRecipe; // Import der neuen Rezeptklasse
import net.satisfy.vinery.core.registry.ObjectRegistry;
import net.satisfy.vinery.core.registry.RecipeTypesRegistry;
import net.satisfy.vinery.core.util.VineryIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class VineryJEIPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FermentationBarrelCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ApplePressFermentingCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ApplePressMashingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<FermentationBarrelRecipe> fermentationBarrelRecipes = rm.getAllRecipesFor(RecipeTypesRegistry.FERMENTATION_BARREL_RECIPE_TYPE.get());
        registration.addRecipes(FermentationBarrelCategory.FERMENTATION_BARREL, fermentationBarrelRecipes);

        List<ApplePressFermentingRecipe> applePressRecipes = rm.getAllRecipesFor(RecipeTypesRegistry.APPLE_PRESS_FERMENTING_RECIPE_TYPE.get());
        registration.addRecipes(ApplePressFermentingCategory.APPLE_PRESS_TYPE, applePressRecipes);

        List<ApplePressMashingRecipe> applePressMashingRecipes = rm.getAllRecipesFor(RecipeTypesRegistry.APPLE_PRESS_MASHING_RECIPE_TYPE.get());
        registration.addRecipes(ApplePressMashingCategory.APPLE_PRESS_MASHING_TYPE, applePressMashingRecipes);
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new VineryIdentifier("jei_plugin");
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(new FermentationTransferInfo());

    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ObjectRegistry.FERMENTATION_BARREL.get().asItem().getDefaultInstance(), FermentationBarrelCategory.FERMENTATION_BARREL);
        registration.addRecipeCatalyst(ObjectRegistry.APPLE_PRESS.get().asItem().getDefaultInstance(), ApplePressFermentingCategory.APPLE_PRESS_TYPE);
        registration.addRecipeCatalyst(ObjectRegistry.APPLE_PRESS.get().asItem().getDefaultInstance(), ApplePressMashingCategory.APPLE_PRESS_MASHING_TYPE);
    }

    public static void addSlot(IRecipeLayoutBuilder builder, int x, int y, Ingredient ingredient){
        builder.addSlot(RecipeIngredientRole.INPUT, x, y).addIngredients(ingredient);
    }

    private static void addItemStackInputSlot(IRecipeLayoutBuilder builder, ItemStack itemStack) {
        if (Minecraft.getInstance().level == null) return;
        builder.addSlot(RecipeIngredientRole.INPUT, 97, 45).addItemStack(itemStack);
    }

    private static void addItemStackOutputSlot(IRecipeLayoutBuilder builder, ItemStack itemStack) {
        builder.addSlot(RecipeIngredientRole.OUTPUT, 77, 4).addItemStack(itemStack);
    }

    public static void buildSlotsFromRecipe(IRecipeLayoutBuilder builder, FermentationBarrelRecipe recipe) {

        final int BOTTOM_ROW_Y = 45;

        final NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        final int ingredientCount = recipeIngredients.size();
        if (ingredientCount >= 1) VineryJEIPlugin.addSlot(builder, 41, BOTTOM_ROW_Y, recipeIngredients.get(0));
        if (ingredientCount >= 2) VineryJEIPlugin.addSlot(builder, 59, BOTTOM_ROW_Y, recipeIngredients.get(1));
        if (ingredientCount >= 3) VineryJEIPlugin.addSlot(builder, 77, BOTTOM_ROW_Y, recipeIngredients.get(2));

        VineryJEIPlugin.addItemStackInputSlot(builder, ObjectRegistry.WINE_BOTTLE.get().getDefaultInstance());

        assert Minecraft.getInstance().level != null;
        VineryJEIPlugin.addItemStackOutputSlot(builder, recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
    }
}
