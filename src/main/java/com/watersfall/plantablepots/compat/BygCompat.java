package com.watersfall.plantablepots.compat;

import com.watersfall.plantablepots.PlantablePots;
import com.watersfall.plantablepots.block.PlantableFlowerPotBlock;
import corgiaoc.byg.core.BYGBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class BygCompat
{
	public static final ArrayList<Block> BLOCKS = new ArrayList<>();

	public static void init()
	{
		for(int i = 0; i < BYGBlocks.flowerPotBlocks.size(); i++)
		{
			FlowerPotBlock block = (FlowerPotBlock) BYGBlocks.flowerPotBlocks.get(i);
			Identifier id = new Identifier(PlantablePots.MOD_ID, "plantable_flower_pot_" + BYGBlocks.flowerIDs.get(i).split(":")[1]);
			BLOCKS.add(Registry.register(Registry.BLOCK, id, new PlantableFlowerPotBlock(block.getContent(), FabricBlockSettings.copyOf(block))));
		}
	}

	public static void initClient()
	{
		for(Block block : BLOCKS)
		{
			BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
		}
	}
}
