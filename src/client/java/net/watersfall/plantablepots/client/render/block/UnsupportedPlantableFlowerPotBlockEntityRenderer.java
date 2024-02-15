package net.watersfall.plantablepots.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.util.math.MatrixStack;
import net.watersfall.plantablepots.block.PlantableFlowerPotBlock;
import net.watersfall.plantablepots.block.entity.UnsupportedPlantableFlowerPotBlockEntity;
import net.watersfall.plantablepots.mixin.FlowerPotBlockAccessor;

public class UnsupportedPlantableFlowerPotBlockEntityRenderer implements BlockEntityRenderer<UnsupportedPlantableFlowerPotBlockEntity>
{
	private final BlockRenderManager renderer;

	public UnsupportedPlantableFlowerPotBlockEntityRenderer(BlockEntityRendererFactory.Context ctx)
	{
		this.renderer = ctx.getRenderManager();
	}

	@Override
	public void render(UnsupportedPlantableFlowerPotBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
	{
		if(entity.getCachedState().get(PlantableFlowerPotBlock.AGE) == 3)
		{
			BlockState block = FlowerPotBlockAccessor.getContentToPotted().get(entity.getFlower()).getDefaultState();
			renderer.renderBlock(block, entity.getPos(), entity.getWorld(), matrices, vertexConsumers.getBuffer(RenderLayer.getCutout()), true, entity.getWorld().getRandom());
		}
	}
}
