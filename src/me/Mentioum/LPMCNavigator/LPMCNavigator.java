package me.Mentioum.LPMCNavigator;
import com.mini.Mini;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class LPMCNavigator extends JavaPlugin {
    public static LPMCNavigator plugin;
    public static final Logger logger = Logger.getLogger("Minecraft");
    private PlayerCompassListener playercompasslistener= new PlayerCompassListener(this); //Defines Compass Listener
    public static String mainDirectory = "plugins/LPMCNavigator";//Set main directory for easy reference
    private PluginManager manager;
    private File directory;
    public Mini database;
    
    private Set<Player> navigatorList = new HashSet<Player>();
    
   
    
    
    @Override
    public void onDisable() {
        PluginManager pm = getServer().getPluginManager();
        PluginDescriptionFile pdfFile = this.getDescription();
        LPMCNavigator.logger.info( pdfFile.getName() + " V." + pdfFile.getVersion() + " by " + pdfFile.getAuthors() + " is disabled");
    }

    
    
    
    
    @Override
    public void onEnable() {
    
    PluginManager pm = getServer().getPluginManager();
   
    PluginDescriptionFile pdfFile = this.getDescription();
    LPMCNavigator.logger.info( pdfFile.getName() + " V" + pdfFile.getVersion() + " by " + pdfFile.getAuthors() + " is enabled");
    directory = getDataFolder();
    database = new Mini(directory.getPath(), "playercompasslocations.mini");
    
    pm.registerEvent(Type.PLAYER_INTERACT, this.playercompasslistener, Priority.Normal, this);
    
    
    
    
    
    
    getCommand("navigator").setExecutor(new CommandExecutor() {
           
        
        
        public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] args) {
                if (args.length > 0){
                    return false;
                }
                
                if (cs instanceof Player){
                    
                    Player player = (Player)cs;
                    toggleCompassState(player, !hasCompassNav(player));
                            
                    
                } else {
                    cs.sendMessage(ChatColor.RED + "Command must be performed as a player!");
                }
            
                
                return true;
                
            }
        });
    
    }

    
    public boolean hasCompassNav(Player player){
        return navigatorList.contains(player);
    }
    
    
    
    public void toggleCompassState(Player player, boolean enabled){
        
        if(enabled){
            
                navigatorList.add(player);
                player.sendMessage(ChatColor.BLUE + "Compass navigation mode" + ChatColor.RED + " disabled" + ChatColor.BLUE + ".");
                
                
                
        } else {
            
                navigatorList.remove(player);
                player.sendMessage(ChatColor.BLUE + "Compass navigation mode" + ChatColor.GREEN + " enabled" + ChatColor.BLUE + ".");
        }
    } 
    
}