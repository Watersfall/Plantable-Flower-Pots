package net.watersfall.plantablepots;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.watersfall.plantablepots.block.EmptyPlantableFlowerPotBlock;
import net.watersfall.plantablepots.block.PlantableFlowerPotBlock;
import net.watersfall.plantablepots.block.UnsupportedPlantableFlowerPotBlock;
import net.watersfall.plantablepots.block.entity.UnsupportedPlantableFlowerPotBlockEntity;

public class PlantablePots implements ModInitializer
{
	public static final String ID = "plantable_pots";

	public static Identifier id(String id)
	{
		return new Identifier(ID, id);
	}

	public static EmptyPlantableFlowerPotBlock EMPTY_PLANTABLE_POT;
	public static PlantableFlowerPotBlock OAK_SAPLING;
	public static PlantableFlowerPotBlock SPRUCE_SAPLING;
	public static PlantableFlowerPotBlock BIRCH_SAPLING;
	public static PlantableFlowerPotBlock JUNGLE_SAPLING;
	public static PlantableFlowerPotBlock ACACIA_SAPLING;
	public static PlantableFlowerPotBlock CHERRY_SAPLING;
	public static PlantableFlowerPotBlock DARK_OAK_SAPLING;
	public static PlantableFlowerPotBlock MANGROVE_PROPAGULE;
	public static PlantableFlowerPotBlock FERN;
	public static PlantableFlowerPotBlock DANDELION;
	public static PlantableFlowerPotBlock POPPY;
	public static PlantableFlowerPotBlock BLUE_ORCHID;
	public static PlantableFlowerPotBlock ALLIUM;
	public static PlantableFlowerPotBlock AZURE_BLUET;
	public static PlantableFlowerPotBlock RED_TULIP;
	public static PlantableFlowerPotBlock ORANGE_TULIP ;
	public static PlantableFlowerPotBlock WHITE_TULIP;
	public static PlantableFlowerPotBlock PINK_TULIP;
	public static PlantableFlowerPotBlock OXEYE_DAISY;
	public static PlantableFlowerPotBlock CORNFLOWER;
	public static PlantableFlowerPotBlock LILY_OF_THE_VALLEY;
	public static PlantableFlowerPotBlock WITHER_ROSE;
	public static PlantableFlowerPotBlock RED_MUSHROOM;
	public static PlantableFlowerPotBlock BROWN_MUSHROOM;
	public static PlantableFlowerPotBlock CACTUS;
	public static PlantableFlowerPotBlock BAMBOO;
	public static PlantableFlowerPotBlock CRIMSON_FUNGUS;
	public static PlantableFlowerPotBlock WARPED_FUNGUS;
	public static PlantableFlowerPotBlock CRIMSON_ROOTS;
	public static PlantableFlowerPotBlock WARPED_ROOTS;
	public static PlantableFlowerPotBlock AZALEA_BUSH;
	public static PlantableFlowerPotBlock FLOWERING_AZALEA_BUSH;
	public static UnsupportedPlantableFlowerPotBlock UNSUPPORTED_FLOWER_POT;

	public static BlockEntityType<UnsupportedPlantableFlowerPotBlockEntity> UNSUPPORTED_FLOWER_POT_BLOCK_ENTITY;

	@Override
	public void onInitialize()
	{

		EMPTY_PLANTABLE_POT = register("empty_plantable_pot", new EmptyPlantableFlowerPotBlock(FabricBlockSettings.copyOf(Blocks.FLOWER_POT)));
		OAK_SAPLING = registerFlower(Blocks.OAK_SAPLING);
		SPRUCE_SAPLING = registerFlower(Blocks.SPRUCE_SAPLING);
		BIRCH_SAPLING = registerFlower(Blocks.BIRCH_SAPLING);
		JUNGLE_SAPLING = registerFlower(Blocks.JUNGLE_SAPLING);
		ACACIA_SAPLING = registerFlower(Blocks.ACACIA_SAPLING);
		CHERRY_SAPLING = registerFlower(Blocks.CHERRY_SAPLING);
		DARK_OAK_SAPLING = registerFlower(Blocks.DARK_OAK_SAPLING);
		MANGROVE_PROPAGULE = registerFlower(Blocks.MANGROVE_PROPAGULE);
		FERN = registerFlower(Blocks.FERN);
		DANDELION = registerFlower(Blocks.DANDELION);
		POPPY = registerFlower(Blocks.POPPY);
		BLUE_ORCHID = registerFlower(Blocks.BLUE_ORCHID);
		ALLIUM = registerFlower(Blocks.ALLIUM);
		AZURE_BLUET = registerFlower(Blocks.AZURE_BLUET);
		RED_TULIP = registerFlower(Blocks.RED_TULIP);
		ORANGE_TULIP  = registerFlower(Blocks.ORANGE_TULIP );
		WHITE_TULIP = registerFlower(Blocks.WHITE_TULIP);
		PINK_TULIP = registerFlower(Blocks.PINK_TULIP);
		OXEYE_DAISY = registerFlower(Blocks.OXEYE_DAISY);
		CORNFLOWER = registerFlower(Blocks.CORNFLOWER);
		LILY_OF_THE_VALLEY = registerFlower(Blocks.LILY_OF_THE_VALLEY);
		WITHER_ROSE = registerFlower(Blocks.WITHER_ROSE);
		RED_MUSHROOM = registerFlower(Blocks.RED_MUSHROOM);
		BROWN_MUSHROOM = registerFlower(Blocks.BROWN_MUSHROOM);
		CACTUS = registerFlower(Blocks.CACTUS);
		BAMBOO = registerFlower(Blocks.BAMBOO);
		CRIMSON_FUNGUS = registerFlower(Blocks.CRIMSON_FUNGUS);
		WARPED_FUNGUS = registerFlower(Blocks.WARPED_FUNGUS);
		CRIMSON_ROOTS = registerFlower(Blocks.CRIMSON_ROOTS);
		WARPED_ROOTS = registerFlower(Blocks.WARPED_ROOTS);
		AZALEA_BUSH = registerFlower(Blocks.AZALEA);
		FLOWERING_AZALEA_BUSH = registerFlower(Blocks.FLOWERING_AZALEA);
		UNSUPPORTED_FLOWER_POT = register("unsupported_flower_pot", new UnsupportedPlantableFlowerPotBlock(FabricBlockSettings.copyOf(Blocks.FLOWER_POT)));
		UNSUPPORTED_FLOWER_POT_BLOCK_ENTITY = BlockEntityType.Builder.create(UnsupportedPlantableFlowerPotBlockEntity::new, UNSUPPORTED_FLOWER_POT).build(null);
		Registry.register(Registries.BLOCK_ENTITY_TYPE, id("unsupported_flower_pot"), UNSUPPORTED_FLOWER_POT_BLOCK_ENTITY);
	}

	private <T extends Block> T register(String id, T block)
	{
		return Registry.register(Registries.BLOCK, id(id), block);
	}

	private PlantableFlowerPotBlock registerFlower(Block flower)
	{
		String flowerName = Registries.BLOCK.getId(flower).getPath();
		String id = flowerName + "_plantable_pot";
		return  register(id, new PlantableFlowerPotBlock(flower, FabricBlockSettings.copyOf(Blocks.FLOWER_POT)));
	}
}
