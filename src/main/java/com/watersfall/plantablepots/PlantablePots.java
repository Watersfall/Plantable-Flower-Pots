package com.watersfall.plantablepots;

import com.watersfall.plantablepots.block.ModBlocks;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PlantablePots implements ModInitializer
{
	public static final String MOD_ID = "plantable_pots";
	@Override public void onInitialize()
	{
		System.out.println(PlantablePots.MOD_ID + ": Initializing Mod");
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot"), ModBlocks.PLANTABLE_FLOWER_POT);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "plantable_flower_pot"), new BlockItem(ModBlocks.PLANTABLE_FLOWER_POT, new Item.Settings().group(ItemGroup.REDSTONE)));
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_red_tulip"), ModBlocks.PLANTABLE_FLOWER_POT_RED_TULIP);
	}
}
