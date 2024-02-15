package net.watersfall.plantablepots.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.watersfall.plantablepots.PlantablePots;
import net.watersfall.plantablepots.block.entity.UnsupportedPlantableFlowerPotBlockEntity;
import org.jetbrains.annotations.Nullable;

/**
 * This block provides support for flowers that don't have built in support
 * using a BlockEntity and a generic set of models and textures
 */
public class UnsupportedPlantableFlowerPotBlock extends CustomFlowerPotBlock implements BlockEntityProvider
{
	public UnsupportedPlantableFlowerPotBlock(Settings settings)
	{
		super(settings);
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
	public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state)
	{
		if(world.getBlockEntity(pos) instanceof UnsupportedPlantableFlowerPotBlockEntity entity)
		{
			return new ItemStack(entity.getFlower());
		}
		return super.getPickStack(world, pos, state);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		if(state.get(AGE) < 3)
		{
			return super.getRenderType(state);
		}
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		ItemStack stack = player.getStackInHand(hand);
		int age = state.get(AGE);
		Block flower;
		if(world.getBlockEntity(pos) instanceof UnsupportedPlantableFlowerPotBlockEntity entity)
		{
			flower = entity.getFlower();
		}
		else
		{
			return ActionResult.PASS;
		}
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


	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
	{
		return new UnsupportedPlantableFlowerPotBlockEntity(pos, state);
	}
}
