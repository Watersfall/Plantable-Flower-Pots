package com.watersfall.plantablepots.script;

import com.watersfall.plantablepots.block.ModBlocks;
import net.minecraft.block.Block;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerateJSON
{
	private static final File BLOCKSTATE_DIRECTORY = new File("C:\\Users\\Chris\\Desktop\\data\\blockstates");
	private static final File MODEL_DIRECTORY = new File("C:\\Users\\Chris\\Desktop\\data\\models\\block");
	private static final File LOOT_TABLE_DIRECTORY = new File("C:\\Users\\Chris\\Desktop\\data\\loot_tables\\blocks");

	private static final String BLOCKSTATE_JSON_TEMPLATE =
			"{\n" +
			"\t\"variants\": {\n" +
			"\t\t\"age=0\": {\n" +
			"\t\t\t\"model\": \"plantable_pots:block/{name}_age_0\"\n" +
			"\t\t},\n" +
			"\t\t\"age=1\": {\n" +
			"\t\t\t\"model\": \"plantable_pots:block/{name}_age_1\"\n" +
			"\t\t},\n" +
			"\t\t\"age=2\": {\n" +
			"\t\t\t\"model\": \"plantable_pots:block/{name}_age_2\"\n" +
			"\t\t},\n" +
			"\t\t\"age=3\": {\n" +
			"\t\t\t\"model\": \"plantable_pots:block/{name}_age_3\"\n" +
			"\t\t}\n" +
			"\t}\n" +
			"}";
	private static final String MODEL_JSON_TEMPLATE =
			"{\n" +
			"\t\"parent\": \"minecraft:block/flower_pot_cross\",\n" +
			"\t\"textures\": {\n" +
			"\t\t\"plant\": \"plantable_pots:block/{name}_age_{age}\"\n" +
			"\t}\n" +
			"}";
	private static final String MODEL_JSON_FINAL_TEMPLATE =
			"{\n" +
			"\t\"parent\": \"minecraft:block/flower_pot_cross\",\n" +
			"\t\"textures\": {\n" +
			"\t\t\"plant\": \"minecraft:block/{name}\"\n" +
			"\t}\n" +
			"}";
	private static final String LOOT_TABLE_JSON_TEMPLATE =
			"{\n" +
			"\t\"type\": \"minecraft:block\",\n" +
			"\t\"pools\": [\n" +
			"\t\t{\n" +
			"\t\t\t\"rolls\": 1,\n" +
			"\t\t\t\"entries\": [\n" +
			"\t\t\t\t{\n" +
			"\t\t\t\t\t\"type\": \"minecraft:item\",\n" +
			"\t\t\t\t\t\"name\": \"minecraft:flower_pot\"\n" +
			"\t\t\t\t}\n" +
			"\t\t\t],\n" +
			"\t\t\t\"conditions\": [\n" +
			"\t\t\t\t{\n" +
			"\t\t\t\t\t\"condition\": \"minecraft:survives_explosion\"\n" +
			"\t\t\t\t}\n" +
			"\t\t\t]\n" +
			"\t\t},\n" +
			"\t\t{\n" +
			"\t\t\t\"rolls\": 1,\n" +
			"\t\t\t\"entries\": [\n" +
			"\t\t\t\t{\n" +
			"\t\t\t\t\t\"type\": \"minecraft:item\",\n" +
			"\t\t\t\t\t\"name\": \"minecraft:{flower}\"\n" +
			"\t\t\t\t}\n" +
			"\t\t\t],\n" +
			"\t\t\t\"conditions\": [\n" +
			"\t\t\t\t{\n" +
			"\t\t\t\t\t\"condition\": \"minecraft:block_state_property\",\n" +
			"\t\t\t\t\t\"block\": \"plantable_pots:plantable_flower_pot_{flower}\",\n" +
			"\t\t\t\t\t\"properties\": {\"age\": \"3\"}\n" +
			"\t\t\t\t}\n" +
			"\t\t\t]\n" +
			"\t\t}\n" +
			"\t]\n" +
			"}";

	private static Map<String, Block> getFlowers()
	{
		HashMap<String, Block> map = new HashMap<>();
		map.put("plantable_flower_pot_red_tulip", ModBlocks.PLANTABLE_FLOWER_POT_RED_TULIP);
		map.put("plantable_flower_pot_orange_tulip", ModBlocks.PLANTABLE_FLOWER_POT_ORANGE_TULIP);
		map.put("plantable_flower_pot_white_tulip", ModBlocks.PLANTABLE_FLOWER_POT_WHITE_TULIP);
		map.put("plantable_flower_pot_pink_tulip", ModBlocks.PLANTABLE_FLOWER_POT_PINK_TULIP);
		map.put("plantable_flower_pot_oxeye_daisy", ModBlocks.PLANTABLE_FLOWER_POT_OXEYE_DAISY);
		map.put("plantable_flower_pot_cornflower", ModBlocks.PLANTABLE_FLOWER_POT_CORNFLOWER);
		map.put("plantable_flower_pot_lily_of_the_valley", ModBlocks.PLANTABLE_FLOWER_POT_LILY_OF_THE_VALLEY);
		map.put("plantable_flower_pot_azure_bluet", ModBlocks.PLANTABLE_FLOWER_POT_AZURE_BLUET);
		map.put("plantable_flower_pot_allium", ModBlocks.PLANTABLE_FLOWER_POT_ALLIUM);
		map.put("plantable_flower_pot_blue_orchid", ModBlocks.PLANTABLE_FLOWER_POT_BLUE_ORCHID);
		map.put("plantable_flower_pot_poppy", ModBlocks.PLANTABLE_FLOWER_POT_POPPY);
		map.put("plantable_flower_pot_dandelion", ModBlocks.PLANTABLE_FLOWER_POT_DANDELION);
		return map;
	}

	private static void generateBlockState(Map.Entry<String, Block> entry)
	{
		String name = entry.getKey();
		Block block = entry.getValue();
		File file = new File(BLOCKSTATE_DIRECTORY, name + ".json");
		String replace = name.replace("plantable_flower_pot_", "");
		String json = BLOCKSTATE_JSON_TEMPLATE.replace("{name}", replace.trim());
		try(FileWriter writer = new FileWriter(file))
		{
			writer.write(json);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void generateModels(Map.Entry<String, Block> entry)
	{
		String name = entry.getKey();
		Block block = entry.getValue();
		String replace = name.replace("plantable_flower_pot_", "");
		for(int i = 0; i < 3; i++)
		{
			File file = new File(MODEL_DIRECTORY, replace + "_age_" + i + ".json");
			String json = MODEL_JSON_TEMPLATE.replace("{name}", replace.trim()).replace("{age}", Integer.toString(i));
			try(FileWriter writer = new FileWriter(file))
			{
				writer.write(json);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		File file = new File(MODEL_DIRECTORY, replace + "_age_3.json");
		String json = MODEL_JSON_FINAL_TEMPLATE.replace("{name}", replace.trim());
		try(FileWriter writer = new FileWriter(file))
		{
			writer.write(json);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void generateLootTables(Map.Entry<String, Block> entry)
	{
		String name = entry.getKey().replace("plantable_flower_pot_", "");
		String json = LOOT_TABLE_JSON_TEMPLATE.replace("{flower}", name);
		File file = new File(LOOT_TABLE_DIRECTORY, entry.getKey() + ".json");
		try(FileWriter writer = new FileWriter(file))
		{
			writer.write(json);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void generate()
	{
		for(Map.Entry<String, Block> entry : getFlowers().entrySet())
		{
			generateBlockState(entry);
			generateModels(entry);
			generateLootTables(entry);
		}
	}


}
