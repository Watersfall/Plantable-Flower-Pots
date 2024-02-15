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
import net.watersfall.plantablepots.block.entity.UnsupportedPlantableFlowerPotBlockEntity;
import net.watersfall.plantablepots.mixin.FlowerPotBlockAccessor;

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
			Block newBlock;
			if(block.getContent().getDefaultState().isAir())
			{
				newBlock = PlantablePots.EMPTY_PLANTABLE_POT;
			}
			else
			{
				newBlock = PlantableFlowerPotBlock.FLOWER_TO_POTTED_FLOWER.get(block.getContent());
				if(newBlock == null)
				{
					newBlock = PlantablePots.UNSUPPORTED_FLOWER_POT;
				}
			}
			world.setBlockState(hit.getBlockPos(), newBlock.getDefaultState());
			if(newBlock == PlantablePots.UNSUPPORTED_FLOWER_POT)
			{
				if(world.getBlockEntity(hit.getBlockPos()) instanceof UnsupportedPlantableFlowerPotBlockEntity entity)
				{
					entity.setFlower(block.getContent());
				}
			}
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
			else if(FlowerPotBlockAccessor.getContentToPotted().get(block) != null)
			{
				if(!world.isClient)
				{
					stack.decrement(1);
					world.setBlockState(pos, PlantablePots.UNSUPPORTED_FLOWER_POT.getDefaultState());
					if(world.getBlockEntity(hit.getBlockPos()) instanceof UnsupportedPlantableFlowerPotBlockEntity entity)
					{
						entity.setFlower(block);
					}
				}
				return ActionResult.success(world.isClient);
			}
		}
		return super.onUse(state, world, pos, player, hand, hit);
	}
}
