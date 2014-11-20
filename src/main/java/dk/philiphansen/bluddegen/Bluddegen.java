package dk.philiphansen.bluddegen;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Blocks;
import org.apache.logging.log4j.Logger;

@Mod(modid = Bluddegen.MODID, name = Bluddegen.NAME, version = Bluddegen.VERSION)
public class Bluddegen
{
    public static final String MODID = "bluddegen";
    public static final String NAME = "BluddeGen";
    public static final String VERSION = "${version}";

    public Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info(NAME + " has now loaded!");
    }
}
