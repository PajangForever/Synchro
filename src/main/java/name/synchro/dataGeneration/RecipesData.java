package name.synchro.dataGeneration;

import name.synchro.Synchro;
import name.synchro.api.MillstoneRecipeJsonBuilder;
import name.synchro.registrations.ModBlocks;
import name.synchro.registrations.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class RecipesData extends FabricRecipeProvider {

    public RecipesData(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PLANT_FIBRE, 2)
                .input(ModItems.BANANA).
                criterion("has_banana", RecipeProvider.conditionsFromItem(ModItems.BANANA))
                .offerTo(exporter, new Identifier(Synchro.MOD_ID, "plant_fibre_from_banana"));
        VanillaRecipeProvider.offerReversibleCompactingRecipesWithReverseRecipeGroup(
                exporter, RecipeCategory.MISC, ModItems.PLANT_FIBRE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PLANT_FIBRE_BLOCK,
                "plant_fibre_from_its_block", "plant_fibre");
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.PACKED_MUD, 4)
                .input(Blocks.DIRT, 4).input(ModItems.PLANT_FIBRE, 4).input(Items.WATER_BUCKET)
                .criterion("has_plant_fibre", RecipeProvider.conditionsFromItem(ModItems.PLANT_FIBRE))
                .offerTo(exporter, new Identifier(Synchro.MOD_ID, "packed_mud_from_dirt"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.MUD, 8)
                .input(Blocks.DIRT, 8).input(Items.WATER_BUCKET)
                .criterion("has_dirt", RecipeProvider.conditionsFromItem(Blocks.DIRT))
                .offerTo(exporter, new Identifier(Synchro.MOD_ID, "mud_from_dirt"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.PACKED_MUD, 1)
                .input(Blocks.MUD, 1).input(ModItems.PLANT_FIBRE, 1)
                .criterion("has_plant_fibre", RecipeProvider.conditionsFromItem(ModItems.PLANT_FIBRE))
                .offerTo(exporter, new Identifier(Synchro.MOD_ID, "packed_mud_from_mud"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.CLAY, 8)
                .input(Blocks.MUD, 8).input(ItemTags.SAND)
                .criterion("has_mud", RecipeProvider.conditionsFromItem(Blocks.MUD))
                .offerTo(exporter, new Identifier(Synchro.MOD_ID, "clay_from_mud"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FRESH_FORAGE, 4)
                .pattern("abc").pattern("bcb").pattern("cba")
                .input('a', Items.SWEET_BERRIES).input('b', Ingredient.ofItems(Items.GRASS, Items.FERN)).input('c', Items.WHEAT)
                .criterion("has_wheat", RecipeProvider.conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(Synchro.MOD_ID, "fresh_forage"));
        generateMillstoneRecipes(exporter);
    }

    private void generateMillstoneRecipes(Consumer<RecipeJsonProvider> exporter){
        MillstoneRecipeJsonBuilder.create(Ingredient.fromTag(ItemTags.PLANKS), new ItemStack(ModItems.PLANT_FIBRE, 4), 180)
                .offerTo(exporter, "plant_fibre_from_planks");
        MillstoneRecipeJsonBuilder.create(Ingredient.ofItems(Items.STICK), new ItemStack(ModItems.PLANT_FIBRE, 1), 45)
                .offerTo(exporter, "plant_fibre_from_stick");
        MillstoneRecipeJsonBuilder.create(Ingredient.fromTag(ItemTags.SAPLINGS), new ItemStack(ModItems.PLANT_FIBRE, 1), 45)
                .offerTo(exporter, "plant_fibre_from_saplings");
        MillstoneRecipeJsonBuilder.create(Ingredient.ofItems(ModItems.CRACKED_ORES, ModItems.LUMP_ORES, ModItems.CRUSHED_ORES)
                        , new ItemStack(ModItems.ORES_DUST, 1), 90).copyNbt()
                .offerTo(exporter, "dust_from_ores");
    }
}
