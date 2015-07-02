package mc.Mitchellbrine.portableRF.block;

import mc.Mitchellbrine.portableRF.block.tile.TileEntitySolarGenerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

/**
 * Created by Mitchellbrine on 2015.
 */
public class PortableSolarGenerator extends BlockContainer {

	public PortableSolarGenerator() {
		super(Material.iron);
		this.setHardness(5.0F);
		this.setBlockName("portableSolarGenerator");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySolarGenerator(p_149915_2_);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
		if (world.getTileEntity(x,y,z) != null && world.getTileEntity(x,y,z) instanceof TileEntitySolarGenerator) {
			world.setBlockMetadataWithNotify(x,y,z,stack.getItemDamage(),3);
			TileEntitySolarGenerator te = (TileEntitySolarGenerator) world.getTileEntity(x,y,z);
			if (stack.hasTagCompound())
				te.readFromNBT(stack.getTagCompound());
		}
	}

	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (!p_149727_1_.isRemote) {
			p_149727_5_.addChatComponentMessage(new ChatComponentText(((TileEntitySolarGenerator)p_149727_1_.getTileEntity(p_149727_2_,p_149727_3_,p_149727_4_)).getEnergyStored(ForgeDirection.UNKNOWN) + "/" + ((TileEntitySolarGenerator)p_149727_1_.getTileEntity(p_149727_2_,p_149727_3_,p_149727_4_)).getMaxEnergyStored(ForgeDirection.UNKNOWN) + " RF"));
		}
		return false;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
		ItemStack stack = new ItemStack(ItemBlock.getItemFromBlock(this),1,metadata);
		TileEntity te = world.getTileEntity(x, y, z);
		NBTTagCompound nbt = new NBTTagCompound();
		te.writeToNBT(nbt);
		stack.setTagCompound(nbt);
		stacks.add(stack);
		return stacks;
	}
}
