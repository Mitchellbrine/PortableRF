package mc.Mitchellbrine.portableRF.block;

import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.ItemHelper;
import cofh.thermalexpansion.ThermalExpansion;
import cofh.thermalexpansion.item.ItemCapacitor;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Mitchellbrine on 2015.
 */
public class ItemSolarGenerator extends ItemBlock implements IEnergyContainerItem {

	public ItemSolarGenerator(Block p_i45328_1_) {
		super(p_i45328_1_);
		this.setCreativeTab(ThermalExpansion.tabBlocks);
		this.setUnlocalizedName("itemSolarGenerator");
		this.setHasSubtypes(true);
		this.setMaxDamage(1);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}

	public int getDisplayDamage(ItemStack var1) {
		return var1.stackTagCompound == null?ItemCapacitor.CAPACITY[ItemHelper.getItemDamage(var1)]:ItemCapacitor.CAPACITY[ItemHelper.getItemDamage(var1)] - var1.stackTagCompound.getInteger("Energy");
	}

	public int getMaxDamage(ItemStack var1) {
		return ItemCapacitor.CAPACITY[ItemHelper.getItemDamage(var1)];
	}

	public int receiveEnergy(ItemStack var1, int var2, boolean var3) {
		int var4 = ItemHelper.getItemDamage(var1);
		if(var4 <= ItemCapacitor.Types.POTATO.ordinal()) {
			return 0;
		} else {
			if(var1.stackTagCompound == null) {
				EnergyHelper.setDefaultEnergyTag(var1, 0);
			}

			int var5 = var1.stackTagCompound.getInteger("Energy");
			int var6 = Math.min(var2, Math.min(ItemCapacitor.CAPACITY[var4] - var5, ItemCapacitor.RECEIVE[var4]));
			if(!var3 && var1.getItemDamage() != ItemCapacitor.Types.CREATIVE.ordinal()) {
				var5 += var6;
				var1.stackTagCompound.setInteger("Energy", var5);
			}

			return var6;
		}
	}

	public int extractEnergy(ItemStack var1, int var2, boolean var3) {
		if(var1.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(var1, 0);
		}

		int var4 = var1.stackTagCompound.getInteger("Energy");
		int var5 = Math.min(var2, Math.min(var4, ItemCapacitor.SEND[ItemHelper.getItemDamage(var1)]));
		if(!var3 && var1.getItemDamage() != ItemCapacitor.Types.CREATIVE.ordinal()) {
			var4 -= var5;
			var1.stackTagCompound.setInteger("Energy", var4);
			if(var4 == 0 && var1.getItemDamage() == ItemCapacitor.Types.POTATO.ordinal()) {
				var1.func_150996_a(Items.baked_potato);
			}
		}

		return var5;
	}

	public int getEnergyStored(ItemStack var1) {
		if(var1.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(var1, 0);
		}

		return var1.stackTagCompound.getInteger("Energy");
	}

	public int getMaxEnergyStored(ItemStack var1) {
		return ItemCapacitor.CAPACITY[var1.getItemDamage()];
	}

	@Override
	public String getUnlocalizedName(ItemStack p_77667_1_) {
		return "item.solarGenerator."+ItemCapacitor.NAMES[p_77667_1_.getItemDamage()];
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		world.setBlockMetadataWithNotify(x,y,z,metadata,3);
		return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
	}

	public int getMetadata(int p_77647_1_)
	{
		return p_77647_1_;
	}

}
