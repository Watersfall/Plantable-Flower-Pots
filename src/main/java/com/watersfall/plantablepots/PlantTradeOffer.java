package com.watersfall.plantablepots;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class PlantTradeOffer implements TradeOffers.Factory
{
	private ItemStack stack;
	private int price, count, maxUses, experience;
	private float multiplier;

	public PlantTradeOffer(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier)
	{
		this.stack = stack;
		this.price = price;
		this.count = count;
		this.maxUses = maxUses;
		this.experience = experience;
		this.multiplier = multiplier;
	}

	@Override
	public @Nullable TradeOffer create(Entity entity, Random random)
	{
		return new TradeOffer(new ItemStack(Items.EMERALD, this.price), new ItemStack(this.stack.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
	}
}
