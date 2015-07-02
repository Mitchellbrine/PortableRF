package mc.Mitchellbrine.portableRF.block.tile;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.TileEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.EnumSkyBlock;

/**
 * Created by Mitchellbrine on 2015.
 */
public class TileEntitySolarGenerator extends TileEnergyHandler {

	//public static final String[] NAMES = new String[]{"creative", "potato", "basic", "hardened", "reinforced", "resonant"};
	public static int[] SEND = new int[]{100000, 160, 80, 400, 4000, 16000};
	public static int[] RECEIVE = new int[]{0, 0, 200, 800, 8000, 32000};
	public static int[] CAPACITY = new int[]{100000, 32000, 80000, 400000, 4000000, 20000000};

	public int metadata;

	public TileEntitySolarGenerator() {
		metadata = 0;
	}

	public TileEntitySolarGenerator(int meta) {
		metadata = meta;
		storage = new EnergyStorage(CAPACITY[metadata],RECEIVE[metadata],SEND[metadata]);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj != null && !worldObj.isRemote && worldObj.getTotalWorldTime() % 20 == 0) {
			//if (worldObj.getSavedLightValue(EnumSkyBlock.Sky, xCoord, yCoord, zCoord) > 8) {
				storage.receiveEnergy(20 * Math.min(7, worldObj.getSavedLightValue(EnumSkyBlock.Sky, xCoord, yCoord, zCoord) - 8), false);
				System.out.println("DONE!");
			//}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("metadata",metadata);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		if (nbtTagCompound.hasKey("metadata")) {
			metadata = nbtTagCompound.getInteger("metadata");
		}
	}
}
