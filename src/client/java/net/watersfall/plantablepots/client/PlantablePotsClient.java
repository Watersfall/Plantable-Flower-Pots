package net.watersfall.plantablepots.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.watersfall.plantablepots.PlantablePots;
import net.watersfall.plantablepots.client.render.block.UnsupportedPlantableFlowerPotBlockEntityRenderer;

public class PlantablePotsClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				PlantablePots.OAK_SAPLING,
				PlantablePots.SPRUCE_SAPLING,
				PlantablePots.BIRCH_SAPLING,
				PlantablePots.JUNGLE_SAPLING,
				PlantablePots.ACACIA_SAPLING,
				PlantablePots.CHERRY_SAPLING,
				PlantablePots.DARK_OAK_SAPLING,
				PlantablePots.MANGROVE_PROPAGULE,
				PlantablePots.FERN,
				PlantablePots.DANDELION,
				PlantablePots.POPPY,
				PlantablePots.BLUE_ORCHID,
				PlantablePots.ALLIUM,
				PlantablePots.AZURE_BLUET,
				PlantablePots.RED_TULIP,
				PlantablePots.ORANGE_TULIP ,
				PlantablePots.WHITE_TULIP,
				PlantablePots.PINK_TULIP,
				PlantablePots.OXEYE_DAISY,
				PlantablePots.CORNFLOWER,
				PlantablePots.LILY_OF_THE_VALLEY,
				PlantablePots.WITHER_ROSE,
				PlantablePots.RED_MUSHROOM,
				PlantablePots.BROWN_MUSHROOM,
				PlantablePots.CACTUS,
				PlantablePots.BAMBOO,
				PlantablePots.CRIMSON_FUNGUS,
				PlantablePots.WARPED_FUNGUS,
				PlantablePots.CRIMSON_ROOTS,
				PlantablePots.WARPED_ROOTS,
				PlantablePots.AZALEA_BUSH,
				PlantablePots.FLOWERING_AZALEA_BUSH,
				PlantablePots.UNSUPPORTED_FLOWER_POT
		);
		ColorProviderRegistry.BLOCK.register(
				(state, world, pos, tintIndex) ->
				{
					return world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getDefaultColor();
				},
				PlantablePots.FERN
		);
		BlockEntityRendererFactories.register(PlantablePots.UNSUPPORTED_FLOWER_POT_BLOCK_ENTITY, UnsupportedPlantableFlowerPotBlockEntityRenderer::new);
	}
}
