
package me.Mentioum.LPMCNavigator;

import org.bukkit.Location;
import com.mini.Arguments;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;



public class PlayerCompassListener  extends PlayerListener{
    private final LPMCNavigator plugin;
   
    public PlayerCompassListener(LPMCNavigator plugin){
        this.plugin = plugin;
    }
    
    
    @Override
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack helditem = event.getItem();
        Location playerstoredlocation = player.getLocation();
        Action action = event.getAction();
        
        
        
        
        if(plugin.hasCompassNav(player)){
            return;
        }
        
        else{
            if (helditem != null && helditem.getTypeId() == 345 ){

            int playerstoredlocationxint = (int)playerstoredlocation.getX();
            int playerstoredlocationyint = (int)playerstoredlocation.getY();
            int playerstoredlocationzint = (int)playerstoredlocation.getZ();


                if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){

                    player.setCompassTarget(playerstoredlocation);

                    player.sendMessage(ChatColor.GREEN + "Compass tuned to your current location in " + player.getLocation().getWorld().getName() + ":");
                    player.sendMessage(ChatColor.BLUE + "X: " + ChatColor.WHITE + playerstoredlocationxint);
                    player.sendMessage(ChatColor.BLUE + "Y: " + ChatColor.WHITE + playerstoredlocationyint);
                    player.sendMessage(ChatColor.BLUE + "Z: " + ChatColor.WHITE + playerstoredlocationzint);
            
                }
                
                if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK){
               
                    player.setCompassTarget(player.getWorld().getSpawnLocation());
                    player.sendMessage(ChatColor.GREEN + "Compass Pointed to " + player.getLocation().getWorld().getName() + "'s spawn: ");
                }

            }
            else{
                return;
            }
             }
        
        }   
          
        }
        
  

        
           
      
    
    

