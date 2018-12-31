package vaw.mod.util;

import net.minecraftforge.common.config.Configuration;
import vaw.mod.proxy.CommonProxy;

public class Config {

    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_MOBS = "mobs";
    private static final String CATEGORY_TOOLS = "tools";

//    public static boolean WolfsbaneInMountains = false;
//    public static boolean WolfsbaneInFlowerForests = true;
    
    //public static Item[] herbivoreFoods = {Items.BEETROOT};
    //public static String yourRealName = "Steve";

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
    	
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
            initMobConfig(cfg);
            initToolConfig(cfg);
        } catch (Exception e1) {
            //ModTut.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }
    
    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        //WolfsbaneInFlowerForests = cfg.getBoolean("Enable New Wool System", CATEGORY_GENERAL, WolfsbaneInFlowerForests, "Set to true to enable wolfsbane to spawn in flower forests");
//        WolfsbaneInMountains = cfg.getBoolean("Wolfsbane In Mountains", CATEGORY_GENERAL, WolfsbaneInMountains, "Set to true to enable wolfsbane to spawn in mountains and hills");

    }
    
    private static void initMobConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_MOBS, "Mob configuration");
    }

    private static void initToolConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_TOOLS, "Tool configuration");
    }
}
