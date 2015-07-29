package me.twentyonez.envirofix.main;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.twentyonez.envirofix.common.EFEventHandler;
import me.twentyonez.envirofix.util.ConfigHelper;

/**
 * EnviroFix
 *
 * @author TwentyOneZ
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 * A quick fix for status values reset after death on Cauldron servers using Enviromine Mod.
 */

@Mod(modid = "envirofix", name = "EnviroFix", version = MinecraftForge.MC_VERSION)
public class EFMainRegistry {
	public static EFMainRegistry modInstance;
	
	public EFMainRegistry() {
		
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHelper.setupConfig(new Configuration(event.getSuggestedConfigurationFile()), event.getModLog());
		
	}
	
	@EventHandler
	public void loadMod(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public void finishLoading(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("enviromine")) {
			//Just register for death and respawn events if enviromine is installed
			MinecraftForge.EVENT_BUS.register(new EFEventHandler());
		}
	}

}
