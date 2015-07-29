package me.twentyonez.envirofix.common;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enviromine.handlers.EM_StatusManager;
import enviromine.trackers.EnviroDataTracker;
import me.twentyonez.envirofix.util.ConfigHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

/**
 * EnviroFix
 *
 * @author TwentyOneZ
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 * A quick fix for status values reset after death on Cauldron servers using Enviromine Mod.
 */
public class EFEventHandler {

	public EFEventHandler() {
		
	}
	
	@SubscribeEvent(priority=EventPriority.HIGH)
	public void playerRespawnEvent(EntityJoinWorldEvent event) {
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			if ((player.isEntityAlive()) && (player.getEntityData().getBoolean("envirofix"))) {
				//I check if the player have the variable I created (i.e: if (s)he died and respawned) before updating the tracker  
				player.getEntityData().removeTag("envirofix");
				EnviroDataTracker tracker = EM_StatusManager.lookupTracker(player);
				if (tracker != null) {
					//Get values from config file and update tracker  
					tracker.bodyTemp = ConfigHelper.tempFixed.floatValue();
					tracker.airQuality = ConfigHelper.airFixed.floatValue();
					tracker.sanity = ConfigHelper.sanityFixed.floatValue();
					tracker.hydration = ConfigHelper.hydrationFixed.floatValue();
					tracker.updateData();			
				}
			}
		}
	}
	
	@SubscribeEvent(priority=EventPriority.HIGH) 
	public void playerDeathEvent(LivingDeathEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			//I need to attach a variable to a player to differ a player that died and respawned from one logging into the server 
			player.getEntityData().setBoolean("envirofix", true);
		}
	}
	
}