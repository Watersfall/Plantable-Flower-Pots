package com.watersfall.plantablepots.mixin;

import com.watersfall.plantablepots.PlantTradeOffer;
import com.watersfall.plantablepots.block.ModBlocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractTraderEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TraderOfferList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTraderEntity.class)
public abstract class WanderingTraderTradesMixin extends AbstractTraderEntity
{
	private static final TradeOffers.Factory[] SPECIAL_TRADES = new TradeOffers.Factory[]{
			new PlantTradeOffer(new ItemStack(ModBlocks.NIRNROOT.asItem()), 7, 1, 2, 10, 0.05F)
	};

	public WanderingTraderTradesMixin(EntityType<? extends AbstractTraderEntity> entityType, World world)
	{
		super(entityType, world);
	}

	@Inject(method = "fillRecipes", at = @At("TAIL"))
	protected void addSpecialTrades(CallbackInfo callback)
	{
		if(this.random.nextInt(2) == 0)
		{
			TraderOfferList traderOfferList = this.getOffers();
			traderOfferList.add(SPECIAL_TRADES[this.random.nextInt(SPECIAL_TRADES.length)].create(this, this.random));
		}

	}
}
