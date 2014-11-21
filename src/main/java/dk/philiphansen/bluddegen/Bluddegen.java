package dk.philiphansen.bluddegen;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Bluddegen.MODID, name = Bluddegen.NAME, version = Bluddegen.VERSION)
public class Bluddegen {
	public static final String MODID = "bluddegen";
	public static final String NAME = "BluddeGen";
	public static final String VERSION = "${version}";

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new BluddeGenerator(), 1);
	}
}
