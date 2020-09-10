package com.watersfall.plantablepots.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import java.util.function.ToIntFunction;

public class NirnrootPlantableFlowerPotBlock extends PlantableFlowerPotBlock
{
	public static ToIntFunction<BlockState> createLightLevelFromBlockState()
	{
		return (blockstate) -> {
			switch(blockstate.get(AGE))
			{
				case 1:
					return 1;
				case 2:
					return 2;
				case 3:
					return 5;
				default:
					return 0;
			}
		};
	}

	public NirnrootPlantableFlowerPotBlock(Block content, Settings settings)
	{
		super(content, settings);
	}
}
