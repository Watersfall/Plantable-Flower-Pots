package com.watersfall.plantablepots;

import com.watersfall.plantablepots.block.ModBlocks;
import com.watersfall.plantablepots.compat.BygCompat;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderLayer;

public class PlantablePotsClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		System.out.println(PlantablePots.MOD_ID + ": Initializing Client");
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_RED_TULIP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_ORANGE_TULIP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_PINK_TULIP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_WHITE_TULIP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_OXEYE_DAISY, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_CORNFLOWER, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_LILY_OF_THE_VALLEY, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_AZURE_BLUET, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_ALLIUM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_BLUE_ORCHID, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_POPPY, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANTABLE_FLOWER_POT_DANDELION, RenderLayer.getCutout());
		if(FabricLoader.getInstance().isModLoaded("byg"))
		{
			BygCompat.initClient();
		}
	}
}
