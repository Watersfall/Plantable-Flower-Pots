package net.watersfall.plantablepots.block;

import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.watersfall.plantablepots.PlantablePots;

import java.util.HashMap;
import java.util.Map;

public class PlantableFlowerPotBlock extends CustomFlowerPotBlock
{
	public static final Map<Block, PlantableFlowerPotBlock> FLOWER_TO_POTTED_FLOWER = new HashMap<>();

	public final Block flower;

	public PlantableFlowerPotBlock(Block flower, Settings settings)
	{
		super(settings);
		this.flower = flower;
		FLOWER_TO_POTTED_FLOWER.put(flower, this);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		super.appendProperties(builder);
		builder.add(AGE);
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
	{
		int age = state.get(AGE);
		if(age < 3)
		{
			world.setBlockState(pos, state.with(AGE, age + 1));
		}
	}

	@Override
	public boolean hasRandomTicks(BlockState state)
	{
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
	{
		if(world.getBaseLightLevel(pos, 0) >= 9)
		{
			if(random.nextInt(10) == 0)
			{
				this.grow(world, random, pos, state);
			}
		}
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		ItemStack stack = player.getStackInHand(hand);
		int age = state.get(AGE);
		if(age == 3 && stack.isOf(Items.SHEARS))
		{
			if(!world.isClient)
			{
				ItemStack droppedStack = new ItemStack(flower);
				Vec3d center = pos.toCenterPos();
				ItemScatterer.spawn(world, center.x, center.y, center.z, droppedStack);
				world.setBlockState(pos, state.with(AGE, 0));
				stack.damage(1, player, playerx -> playerx.sendToolBreakStatus(hand));
			}
			return ActionResult.success(world.isClient);
		}
		else if(stack.isEmpty() && hand == Hand.MAIN_HAND)
		{
			if(!world.isClient)
			{
				if(age == 3)
				{
					ItemStack droppedStack = new ItemStack(flower);
					Vec3d center = pos.toCenterPos();
					ItemScatterer.spawn(world, center.x, center.y, center.z, droppedStack);
				}
				world.setBlockState(pos, PlantablePots.EMPTY_PLANTABLE_POT.getDefaultState());
			}
			return ActionResult.success(world.isClient);
		}
		return ActionResult.PASS;
	}

	@Override
	public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state)
	{
		return new ItemStack(flower);
	}
}
