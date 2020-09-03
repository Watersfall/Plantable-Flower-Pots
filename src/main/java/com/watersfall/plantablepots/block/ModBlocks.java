package com.watersfall.plantablepots.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class ModBlocks
{
	public static final Block PLANTABLE_FLOWER_POT = new PlantableFlowerPotBlock(Blocks.AIR, FabricBlockSettings.copyOf(Blocks.FLOWER_POT));
	public static final Block PLANTABLE_FLOWER_POT_RED_TULIP = new PlantableFlowerPotBlock(Blocks.RED_TULIP, FabricBlockSettings.copyOf(Blocks.POTTED_RED_TULIP).nonOpaque());
}
