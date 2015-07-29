package me.twentyonez.envirofix.util;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * EnviroFix
 *
 * @author TwentyOneZ
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 * A quick fix for status values reset after death on Cauldron servers using Enviromine Mod.
 */
public class ConfigHelper {

	public ConfigHelper() {
		
	}
	
	public static Double tempFixed;
	private static Property tempFixedProp;

	public static Double airFixed;
	private static Property airFixedProp;

	public static Double hydrationFixed;
	private static Property hydrationFixedProp;

	public static Double sanityFixed;
	private static Property sanityFixedProp;

	public static void setupConfig(Configuration cfg, Logger logger) {
		try {
			tempFixedProp = cfg.get("General", "tempFixed", 37.0);
			tempFixedProp.comment = "The value temperature will be set on death";
			tempFixed = tempFixedProp.getDouble(37.0);

			airFixedProp = cfg.get("General", "airFixed", 100.0);
			airFixedProp.comment = "The value air quality will be set on death";
			airFixed = airFixedProp.getDouble(100.0);

			sanityFixedProp = cfg.get("General", "sanityFixed", 100.0);
			sanityFixedProp.comment = "The value sanity will be set on death";
			sanityFixed = sanityFixedProp.getDouble(100.0);

			hydrationFixedProp = cfg.get("General", "hydrationFixed", 100.0);
			hydrationFixedProp.comment = "The value hydration will be set on death";
			hydrationFixed = hydrationFixedProp.getDouble(100.0);

		} catch(Exception e) {
			logger.log(Level.ERROR, "Error trying to create EnviroFix config file!");
			e.printStackTrace();
		} finally {
			if(cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

}
