
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
    public static LPMCNavigator plugin;
    public PlayerCompassListener(LPMCNavigator instance){
        plugin = instance;
    }
   
    
    @Override
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack helditem = event.getItem();
        Location playerstoredlocation = player.getLocation();
        Action action = event.getAction();
        String playerworldlocation = player.getLocation().getWorld().getName();
        Arguments entry = new Arguments(player.getDisplayName());
        
        
        int playerstoredlocationxint = (int)playerstoredlocation.getX();
        int playerstoredlocationyint = (int)playerstoredlocation.getY();
        int playerstoredlocationzint = (int)playerstoredlocation.getZ();

        String playerstoredlocationx = Integer.toString(playerstoredlocationxint);
        String playerstoredlocationy = Integer.toString(playerstoredlocationyint);
        String playerstoredlocationz = Integer.toString(playerstoredlocationzint);
        
        if (helditem.getTypeId() == 345 && action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){
            
            entry.setValue("x", String.valueOf(playerstoredlocationx) );
            entry.setValue("y", String.valueOf(playerstoredlocationy) );
            entry.setValue("z", String.valueOf(playerstoredlocationz) );
            entry.setValue("world", playerworldlocation);
            
            plugin.database.addIndex(entry.getKey(), entry);
            
            plugin.database.update();
            
            String locationstored = ChatColor.GREEN + "Compass tuned to your current location in " + entry.getValue("world") + ":";
            String locationstoredx = ChatColor.BLUE + "X: " + ChatColor.WHITE + entry.getValue("x");
            String locationstoredy = ChatColor.BLUE + "Y: " + ChatColor.WHITE + entry.getValue("y");
            String locationstoredz = ChatColor.BLUE + "Z: " + ChatColor.WHITE + entry.getValue("z");
            player.sendMessage(locationstored);
            player.sendMessage(locationstoredx);
            player.sendMessage(locationstoredy);
            player.sendMessage(locationstoredz);
           
    }    
        else{    
             if(helditem.getTypeId() == 345 && action == Action.LEFT_CLICK_AIR  || action == Action.LEFT_CLICK_BLOCK){
                Arguments getentry = plugin.database.getArguments(player.getDisplayName());
                        
                
                String world = getentry.getValue("world");
                
                        double x = Double.parseDouble(getentry.getValue("x"));
                        double y = Double.parseDouble(getentry.getValue("y"));
                        double z = Double.parseDouble(getentry.getValue("y"));
                        
                        Location newLocation = new Location(plugin.getServer().getWorld(world), x, y, z);
                 
                        player.setCompassTarget(newLocation);
                        
                        String locationstoredx = ChatColor.BLUE +  "X: " + ChatColor.WHITE + getentry.getValue("x");
                        String locationstoredy = ChatColor.BLUE + "Y: " + ChatColor.WHITE +  getentry.getValue("y");
                        String locationstoredz = ChatColor.BLUE + "Z: " + ChatColor.WHITE + getentry.getValue("z");
                        
                        player.sendMessage(ChatColor.GREEN + "Compass Pointed to:");
                        player.sendMessage(locationstoredx);
                        player.sendMessage(locationstoredy);
                        player.sendMessage(locationstoredz);
                        
        }
        
        
             else{
                 
                return;
                 }
             
        }
             
        }
        
    }
  

        
           
      
    
    

