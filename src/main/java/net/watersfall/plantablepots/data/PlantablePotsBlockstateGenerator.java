package net.watersfall.plantablepots.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.watersfall.plantablepots.PlantablePots;
import net.watersfall.plantablepots.block.PlantableFlowerPotBlock;
import net.watersfall.plantablepots.mixin.FlowerPotBlockAccessor;

public class PlantablePotsBlockstateGenerator extends FabricModelProvider
{

	public PlantablePotsBlockstateGenerator(FabricDataOutput output)
	{
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
	{

		blockStateModelGenerator.blockStateCollector.accept(
				BlockStateModelGenerator.createSingletonBlockState(
						PlantablePots.EMPTY_PLANTABLE_POT,
						ModelIds.getBlockModelId(Blocks.FLOWER_POT)
				)
		);
		registerPlantablePot(PlantablePots.OAK_SAPLING, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.SPRUCE_SAPLING, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.BIRCH_SAPLING, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.JUNGLE_SAPLING, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.ACACIA_SAPLING, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.CHERRY_SAPLING, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.DARK_OAK_SAPLING, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.MANGROVE_PROPAGULE, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.FERN, blockStateModelGenerator, true);
		registerPlantablePot(PlantablePots.DANDELION, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.POPPY, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.BLUE_ORCHID, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.ALLIUM, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.AZURE_BLUET, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.RED_TULIP, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.ORANGE_TULIP , blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.WHITE_TULIP, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.PINK_TULIP, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.OXEYE_DAISY, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.CORNFLOWER, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.LILY_OF_THE_VALLEY, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.WITHER_ROSE, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.RED_MUSHROOM, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.BROWN_MUSHROOM, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.CACTUS, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.BAMBOO, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.CRIMSON_FUNGUS, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.WARPED_FUNGUS, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.CRIMSON_ROOTS, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.WARPED_ROOTS, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.AZALEA_BUSH, blockStateModelGenerator, false);
		registerPlantablePot(PlantablePots.FLOWERING_AZALEA_BUSH, blockStateModelGenerator, false);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator)
	{

	}

	public void registerPlantablePot(PlantableFlowerPotBlock flowerPotBlock, BlockStateModelGenerator blockStateModelGenerator, boolean tinted)
	{
		Block vanillaPot = FlowerPotBlockAccessor.getContentToPotted().get(flowerPotBlock.flower);
		blockStateModelGenerator.blockStateCollector.accept(
				VariantsBlockStateSupplier.create(flowerPotBlock).coordinate(
						BlockStateVariantMap
								.create(PlantableFlowerPotBlock.AGE)
								.register(integer -> {
									Identifier identifier;
									if(integer != 3)
									{
										identifier = blockStateModelGenerator.createSubModel(flowerPotBlock, "_age_" + integer, tinted ? Models.TINTED_FLOWER_POT_CROSS : Models.FLOWER_POT_CROSS, TextureMap::plant);
									}
									else
									{
										identifier = ModelIds.getBlockModelId(vanillaPot);
									}
									return BlockStateVariant.create().put(VariantSettings.MODEL, identifier);
								})
				)
		);
	}
}
