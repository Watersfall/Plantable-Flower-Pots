package net.watersfall.plantablepots.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.watersfall.plantablepots.PlantablePots;
import org.jetbrains.annotations.Nullable;

public class UnsupportedPlantableFlowerPotBlockEntity extends BlockEntity
{
	private Block flower = Blocks.AIR;

	public UnsupportedPlantableFlowerPotBlockEntity(BlockPos pos, BlockState state)
	{
		super(PlantablePots.UNSUPPORTED_FLOWER_POT_BLOCK_ENTITY, pos, state);
	}

	public void setFlower(Block flower)
	{
		if(world != null && !world.isClient)
		{
			world.updateListeners(getPos(), getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
		}
		this.flower = flower;
	}

	public Block getFlower()
	{
		return flower;
	}

	@Override
	public void readNbt(NbtCompound nbt)
	{
		super.readNbt(nbt);
		Registries.BLOCK.getOrEmpty(Identifier.tryParse(nbt.getString("block"))).ifPresent(this::setFlower);
	}

	@Override
	protected void writeNbt(NbtCompound nbt)
	{
		super.writeNbt(nbt);
		nbt.putString("block", Registries.BLOCK.getId(getFlower()).toString());
	}

	@Nullable
	@Override
	public Packet<ClientPlayPacketListener> toUpdatePacket()
	{
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt()
	{
		NbtCompound nbt = new NbtCompound();
		writeNbt(nbt);
		return nbt;
	}
}
