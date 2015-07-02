package mc.Mitchellbrine.portableRF.block;

import cpw.mods.fml.common.registry.GameRegistry;
import mc.Mitchellbrine.portableRF.block.tile.TileEntitySolarGenerator;
import net.minecraft.block.Block;

/**
 * Created by Mitchellbrine on 2015.
 */
public class BlockRegistry {

	public static Block portableSolarGenerator;

	public static void init() {
		portableSolarGenerator = new PortableSolarGenerator();

		GameRegistry.registerBlock(portableSolarGenerator, ItemSolarGenerator.class, "portableSolarGenerator");
		GameRegistry.registerTileEntity(TileEntitySolarGenerator.class,"tePortableSolarGenerator");

	}

}
