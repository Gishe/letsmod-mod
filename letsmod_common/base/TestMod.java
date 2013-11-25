package base;

import items.Items;
import net.minecraft.creativetab.CreativeTabs;
import network.PacketHandler;
import proxies.CommonProxy;
import Blocks.Blocks;
import Config.ConfigHandler;
import CreativeTabs.TabLetsMod;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import entities.Entities;


@Mod(modid=ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION)
@NetworkMod(channels = {ModInformation.CHANNEL}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class TestMod
{
    @Instance(ModInformation.ID)
    public static TestMod instance;
    @SidedProxy(clientSide="proxies.ClientProxy", serverSide="proxies.CommonProxy")
    public static CommonProxy proxy;
    
    public static CreativeTabs tab = new TabLetsMod();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        Items.init();
        Blocks.init();
                
        proxy.initSounds();
        proxy.initRenders();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
        Items.addNames();
        Blocks.addNames();
        
        Items.registerRecipes();
        
        Blocks.registerTileEntities();
        Entities.init();
    }
    
    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent event) {
    
    }
}
