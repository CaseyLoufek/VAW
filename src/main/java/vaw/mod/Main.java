package vaw.mod;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import vaw.mod.init.EntityInit;
import vaw.mod.proxy.CommonProxy;
import vaw.mod.util.Config;
import vaw.mod.util.Reference;
import vaw.mod.util.handlers.EventsHandler;
import vaw.mod.util.handlers.FuelHandler;
import vaw.mod.util.handlers.GUIHandler;
import vaw.mod.util.handlers.RecipeHandler;
import vaw.mod.util.handlers.RegistryHandler;
import vaw.mod.worldgen.WolfsbaneGen;

@Mod(modid = Reference.MODID, name = Reference.NAME,  version = Reference.VERSION)

public class Main {


	public static boolean isFAMMloaded;

	@Mod.Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new EventsHandler());
		
		//config
        File directory = event.getModConfigurationDirectory();
        CommonProxy.config = new Configuration(new File(directory.getPath(), "vaw.cfg"));
        Config.readConfig();

        EntityInit.init();
        proxy.initModels();
     
  		GameRegistry.registerFuelHandler(new FuelHandler());   
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) 
	{

		RegistryHandler.initRegistries();
		
		GameRegistry.registerWorldGenerator(new WolfsbaneGen(), 0);
		
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GUIHandler());
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) 
	{
		RecipeHandler.RegisterCrafting();
		RecipeHandler.RegisterSmelting();
		
        EntityInit.postInit();
		//config
        if (CommonProxy.config.hasChanged()) {
        	CommonProxy.config.save();
        }
        
	}
	
	
}
