
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
        String playerworldlocation = player.getLocation().getWorld().getName();
        Arguments entry = new Arguments(player.getDisplayName());
        LPMCNavigator nl = new LPMCNavigator(); 
        
        
        
        
        if(plugin.hasCompassNav(player)){
            return;
        }
        
        else{
            if (helditem != null){
            if (helditem.getTypeId() == 345){

            int playerstoredlocationxint = (int)playerstoredlocation.getX();
            int playerstoredlocationyint = (int)playerstoredlocation.getY();
            int playerstoredlocationzint = (int)playerstoredlocation.getZ();

            String playerstoredlocationx = Integer.toString(playerstoredlocationxint);
            String playerstoredlocationy = Integer.toString(playerstoredlocationyint);
            String playerstoredlocationz = Integer.toString(playerstoredlocationzint);

                if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){

                    player.setCompassTarget(playerstoredlocation);

                    String locationstored = ChatColor.GREEN + "Compass tuned to your current location in " + entry.getValue("world") + ":";
                    String locationstoredx = ChatColor.BLUE + "X: " + ChatColor.WHITE + playerstoredlocationx;
                    String locationstoredy = ChatColor.BLUE + "Y: " + ChatColor.WHITE + playerstoredlocationy;
                    String locationstoredz = ChatColor.BLUE + "Z: " + ChatColor.WHITE + playerstoredlocationz;

                    player.sendMessage(locationstored);
                    player.sendMessage(locationstoredx);
                    player.sendMessage(locationstoredy);
                    player.sendMessage(locationstoredz);
                }
            
            
                
                if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK){
               
                    player.setCompassTarget(player.getWorld().getSpawnLocation());
                    player.sendMessage(ChatColor.GREEN + "Compass Pointed to " + playerworldlocation + "'s spawn: ");
                }

            }
            else{
                return;
            }
             }
        
        }   
          
        }
        
    
    
    
    
    }
  

        
           
      
    
    

