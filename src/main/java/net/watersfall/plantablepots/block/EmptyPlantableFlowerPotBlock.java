package net.watersfall.plantablepots.block;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.watersfall.plantablepots.PlantablePots;

public class EmptyPlantableFlowerPotBlock extends CustomFlowerPotBlock
{
	static
	{
		UseBlockCallback.EVENT.register(EmptyPlantableFlowerPotBlock::turnFlowerPotIntoPlantableFlowerPot);
	}

	public static ActionResult turnFlowerPotIntoPlantableFlowerPot(PlayerEntity player, World world, Hand hand, BlockHitResult hit)
	{
		BlockState state = world.getBlockState(hit.getBlockPos());
		ItemStack stack = player.getStackInHand(hand);
		if(!stack.isOf(Items.BONE_MEAL))
		{
			return ActionResult.PASS;
		}
		if(!(state.getBlock() instanceof FlowerPotBlock))
		{
			return ActionResult.PASS;
		}
		if(!world.isClient)
		{
			FlowerPotBlock block = (FlowerPotBlock)state.getBlock();
			Block newBlock = block;
			if(block.getContent().getDefaultState().isAir())
			{
				newBlock = PlantablePots.EMPTY_PLANTABLE_POT;
			}
			else if(PlantableFlowerPotBlock.FLOWER_TO_POTTED_FLOWER.containsKey(block.getContent()))
			{
				newBlock = PlantableFlowerPotBlock.FLOWER_TO_POTTED_FLOWER.get(block.getContent());
			}
			world.setBlockState(hit.getBlockPos(), newBlock.getDefaultState());
		}
		return ActionResult.success(world.isClient);
	}

	public EmptyPlantableFlowerPotBlock(Settings settings)
	{
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		ItemStack stack = player.getStackInHand(hand);
		if(stack.getItem() instanceof BlockItem item)
		{
			Block block = item.getBlock();
			PlantableFlowerPotBlock flowerPot = PlantableFlowerPotBlock.FLOWER_TO_POTTED_FLOWER.get(block);
			if(flowerPot != null)
			{
				if(!world.isClient)
				{
					stack.decrement(1);
					world.setBlockState(pos, flowerPot.getDefaultState());
				}
				return ActionResult.success(world.isClient);
			}
		}
		return super.onUse(state, world, pos, player, hand, hit);
	}
}
