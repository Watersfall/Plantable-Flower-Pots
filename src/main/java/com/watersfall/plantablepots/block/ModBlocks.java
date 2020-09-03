package com.watersfall.plantablepots.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class ModBlocks
{
	public static final Block PLANTABLE_FLOWER_POT = new PlantableFlowerPotBlock(Blocks.AIR, FabricBlockSettings.copyOf(Blocks.FLOWER_POT));
	public static final Block PLANTABLE_FLOWER_POT_RED_TULIP = new PlantableFlowerPotBlock(Blocks.RED_TULIP, FabricBlockSettings.copyOf(Blocks.POTTED_RED_TULIP));
	public static final Block PLANTABLE_FLOWER_POT_ORANGE_TULIP = new PlantableFlowerPotBlock(Blocks.ORANGE_TULIP, FabricBlockSettings.copyOf(Blocks.POTTED_ORANGE_TULIP));
	public static final Block PLANTABLE_FLOWER_POT_WHITE_TULIP = new PlantableFlowerPotBlock(Blocks.WHITE_TULIP, FabricBlockSettings.copyOf(Blocks.POTTED_WHITE_TULIP));
	public static final Block PLANTABLE_FLOWER_POT_PINK_TULIP = new PlantableFlowerPotBlock(Blocks.PINK_TULIP, FabricBlockSettings.copyOf(Blocks.POTTED_PINK_TULIP));
	public static final Block PLANTABLE_FLOWER_POT_OXEYE_DAISY = new PlantableFlowerPotBlock(Blocks.OXEYE_DAISY, FabricBlockSettings.copyOf(Blocks.POTTED_OXEYE_DAISY));
	public static final Block PLANTABLE_FLOWER_POT_CORNFLOWER = new PlantableFlowerPotBlock(Blocks.CORNFLOWER, FabricBlockSettings.copyOf(Blocks.POTTED_CORNFLOWER));
	public static final Block PLANTABLE_FLOWER_POT_LILY_OF_THE_VALLEY = new PlantableFlowerPotBlock(Blocks.LILY_OF_THE_VALLEY, FabricBlockSettings.copyOf(Blocks.POTTED_LILY_OF_THE_VALLEY));
	public static final Block PLANTABLE_FLOWER_POT_AZURE_BLUET = new PlantableFlowerPotBlock(Blocks.AZURE_BLUET, FabricBlockSettings.copyOf(Blocks.POTTED_AZURE_BLUET));
	public static final Block PLANTABLE_FLOWER_POT_ALLIUM = new PlantableFlowerPotBlock(Blocks.ALLIUM, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM));
	public static final Block PLANTABLE_FLOWER_POT_BLUE_ORCHID = new PlantableFlowerPotBlock(Blocks.BLUE_ORCHID, FabricBlockSettings.copyOf(Blocks.POTTED_BLUE_ORCHID));
	public static final Block PLANTABLE_FLOWER_POT_POPPY = new PlantableFlowerPotBlock(Blocks.POPPY, FabricBlockSettings.copyOf(Blocks.POTTED_POPPY));
	public static final Block PLANTABLE_FLOWER_POT_DANDELION = new PlantableFlowerPotBlock(Blocks.DANDELION, FabricBlockSettings.copyOf(Blocks.POTTED_DANDELION));
}
