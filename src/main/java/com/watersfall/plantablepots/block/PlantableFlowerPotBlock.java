package com.watersfall.plantablepots.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class PlantableFlowerPotBlock extends FlowerPotBlock implements Fertilizable
{
	public static final IntProperty AGE = Properties.AGE_3;

	public PlantableFlowerPotBlock(Block content, Settings settings)
	{
		super(content, settings);
		setDefaultState(getStateManager().getDefaultState().with(AGE, 0));
	}

	public BlockState withAge(int age) {
		return this.getDefaultState().with(AGE, age);
	}

	public int getMaxAge()
	{
		return 3;
	}

	protected int getAge(BlockState state) {
		return state.get(AGE);
	}

	public boolean isMature(BlockState state)
	{
		return state.get(AGE) >= this.getMaxAge();
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		if(this.getContent() == Blocks.AIR)
		{
			return super.onUse(state, world, pos, player, hand, hit);
		}
		else if(!this.isMature(state))
		{
			if(player.getMainHandStack().getItem() == Items.BONE_MEAL)
			{
				BoneMealItem item = (BoneMealItem)player.getMainHandStack().getItem();
				item.useOnBlock(new ItemUsageContext(player, hand, hit));
			}
		}
		else if(this.isMature(state))
		{
			if(player.getMainHandStack().getItem() == Items.SHEARS)
			{
				if(!player.giveItemStack(new ItemStack(this.getContent())))
				{
					player.dropItem(new ItemStack(this.getContent()), false);
				}
				world.setBlockState(pos, this.withAge(0), 2);
			}
			return ActionResult.success(world.isClient);
		}
		return ActionResult.CONSUME;
	}

	@Override
	public boolean hasRandomTicks(BlockState state)
	{
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
	{
		if (world.getBaseLightLevel(pos, 0) >= 9)
		{
			int currentAge = this.getAge(state);
			if (currentAge < this.getMaxAge())
			{
				if (random.nextInt((int)(10.0F) + 1) == 0)
				{
					world.setBlockState(pos, this.withAge(currentAge + 1), 2);
				}
			}
		}
	}

	@Override
	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient)
	{
		return !this.isMature(state);
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state)
	{
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
	{
		world.setBlockState(pos, this.withAge(this.getAge(state) + 1), 2);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		builder.add(AGE);
	}

	public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos)
	{
		return state.getFluidState().isEmpty();
	}
}
