package com.watersfall.plantablepots;

import com.watersfall.plantablepots.block.ModBlocks;
import com.watersfall.plantablepots.script.GenerateJSON;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PlantablePots implements ModInitializer
{
	public static final String MOD_ID = "plantable_pots";
	@Override public void onInitialize()
	{
		System.out.println(PlantablePots.MOD_ID + ": Initializing Mod");
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot"), ModBlocks.PLANTABLE_FLOWER_POT);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_red_tulip"), ModBlocks.PLANTABLE_FLOWER_POT_RED_TULIP);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_orange_tulip"), ModBlocks.PLANTABLE_FLOWER_POT_ORANGE_TULIP);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_white_tulip"), ModBlocks.PLANTABLE_FLOWER_POT_WHITE_TULIP);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_pink_tulip"), ModBlocks.PLANTABLE_FLOWER_POT_PINK_TULIP);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_oxeye_daisy"), ModBlocks.PLANTABLE_FLOWER_POT_OXEYE_DAISY);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_cornflower"), ModBlocks.PLANTABLE_FLOWER_POT_CORNFLOWER);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_lily_of_the_valley"), ModBlocks.PLANTABLE_FLOWER_POT_LILY_OF_THE_VALLEY);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_azure_bluet"), ModBlocks.PLANTABLE_FLOWER_POT_AZURE_BLUET);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_allium"), ModBlocks.PLANTABLE_FLOWER_POT_ALLIUM);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_blue_orchid"), ModBlocks.PLANTABLE_FLOWER_POT_BLUE_ORCHID);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_poppy"), ModBlocks.PLANTABLE_FLOWER_POT_POPPY);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "plantable_flower_pot_dandelion"), ModBlocks.PLANTABLE_FLOWER_POT_DANDELION);
		GenerateJSON.generate();
	}
}
