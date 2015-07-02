package mc.Mitchellbrine.portableRF;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mc.Mitchellbrine.portableRF.block.BlockRegistry;
import mc.Mitchellbrine.portableRF.proxy.CommonProxy;

/**
 * Created by Mitchellbrine on 2015.
 */
@Mod(modid = "PortableRF",name = "Portable RF Devices", version = "1.0.0")
public class PortableRF {

	@SidedProxy(clientSide = "mc.Mitchellbrine.portableRF.proxy.ClientProxy",serverSide = "mc.Mitchellbrine.portableRF.proxy.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerStuff();

		BlockRegistry.init();

	}

}
