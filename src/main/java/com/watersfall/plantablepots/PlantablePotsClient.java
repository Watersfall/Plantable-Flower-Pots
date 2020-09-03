package com.watersfall.plantablepots;

import com.watersfall.plantablepots.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class PlantablePotsClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		System.out.println(PlantablePots.MOD_ID + ": Initializing Client");
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_RED_TULIP, RenderLayer.getCutout());
	}
}
