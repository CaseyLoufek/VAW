package vaw.mod.util;

import net.minecraftforge.common.config.Configuration;
import vaw.mod.proxy.CommonProxy;

public class Config {

    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_MOBS = "mobs";
    private static final String CATEGORY_TOOLS = "tools";

    public static float timeScalar = 1.0f;
    
    public static int bottleStackSize = 8;

    public static boolean buckingDamage = true;

    public static boolean cookStews = true;
    
    public static boolean reversion = false; 

    public static boolean shardsFromBone = true; 
    public static boolean paperFromWood = true; 
    public static boolean sugarFromBeets = false; 
    public static boolean pumpkinRecipes = true; 
    public static boolean bedRecipes = true;

    public static boolean woolSystem = true;

    public static boolean boneArrows = true;
    public static boolean ironArrows = true;
    
    public static boolean craftableSaddles = true;
    public static boolean craftableNametags = true;
    public static boolean craftableHorseArmor = true;
    
    public static boolean leavesDropSticks = true;
    public static boolean noPunchingWood = true;
    public static boolean enableFlintTools = true;
    public static boolean enableKnives = true;
    
    public static boolean fewerDrops = false;
    public static boolean noUnnaturalMobs = false;
    public static boolean noVanillaAnimals = true;
    public static boolean enableSexes = true;
    public static boolean enableAdvancedStats = true;
    public static boolean enableStarvation = true;
    public static boolean enableOldAge = true;
    public static boolean enablePregnancy = true;
    public static boolean enableLitters = true;
    
    public static boolean enableHorses = true;
    public static boolean newHorseBreeding = true;
    public static boolean wildHorses = true;
    
    public static boolean enableCattle = true;
    public static boolean wildBovine = true;
    
    public static boolean enablePigs = true;
    public static boolean wildPorcines = true;
    
    public static boolean enableLlamas = true;
    public static boolean wildLlamas = true;
    
    public static boolean enableSheep = true;
    public static boolean wildSheep = true;
    
    public static boolean enableFowl = true;
    public static boolean wildFowl = true;
    
    public static boolean enableCats = false;
    public static boolean wildCats = true;

    public static boolean enableCanines = false;
    public static boolean wildCanines = true;

    public static boolean enableBears = false;
    public static boolean wildBears = true;
    
    public static boolean enableParrots = false;
    public static boolean wildParrots = true;
    
    public static boolean enableRabbits = false;
    public static boolean wildRabbits = true;
    
    public static boolean smoothGrowth = true;
    public static boolean sizeVariance = false;
    public static boolean enableWildBreeding = true;
    
    public static boolean disableVanillaTorches = true;
    public static boolean enableNewTorches = true;
    
    public static boolean dynamicBiomes = true;
    
    public static boolean replantBlocks = true;
    public static boolean waterBowls = true;
    public static boolean enableNewTanning = true;
    
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
        buckingDamage = cfg.getBoolean("Bucking Damage", CATEGORY_GENERAL, buckingDamage, "Deal damage when thrown off a horse");
        bottleStackSize = cfg.getInt("Bottle Stack Size", CATEGORY_GENERAL, 8, 1, 16, "Number of fat and oil bottles that can stack together");
        cookStews = cfg.getBoolean("Cook Stew", CATEGORY_GENERAL, cookStews, "Stews are crafted raw using water and require cooking");
        replantBlocks = cfg.getBoolean("Replant sapplings and other blocks", CATEGORY_GENERAL, replantBlocks, "Set to true to make sapplings and other blocks attempt to place themselves before decaying");
        leavesDropSticks = cfg.getBoolean("Leaves Drop Sticks", CATEGORY_GENERAL, leavesDropSticks, "Set to true to make leaves drop sticks, useful if wood requires axes");
        noPunchingWood = cfg.getBoolean("No Punching Wood", CATEGORY_GENERAL, noPunchingWood, "Set to true to make wood require an axe to harvest");
        shardsFromBone = cfg.getBoolean("Shards from Bone", CATEGORY_GENERAL, shardsFromBone, "Set to true enable crafting bone shards from vanilla bones");
        sugarFromBeets = cfg.getBoolean("Sugar from Beets", CATEGORY_GENERAL, sugarFromBeets, "Set to true enable crafting sugar from beetroots");
        paperFromWood = cfg.getBoolean("Paper from Wood", CATEGORY_GENERAL, paperFromWood, "Set to true enable crafting paper from logs");
        //pumpkinRecipes = cfg.getBoolean("Pumpkin Recipes", CATEGORY_GENERAL, pumpkinRecipes, "Set to true enable new pumpkin pieces and recipes");
        enableNewTanning = cfg.getBoolean("Enable New Tanning", CATEGORY_TOOLS, enableNewTanning, "Set to true to have skins drop fur/hide that requires processing with tannin from trees");
        woolSystem = cfg.getBoolean("Enable New Wool System", CATEGORY_TOOLS, woolSystem, "Set to true to enable new wool tufts and cloth rules");

    }
    
    private static void initMobConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_MOBS, "Mob configuration");
        reversion = cfg.getBoolean("Reversion", CATEGORY_MOBS, reversion, "Set to true to make untamed animals revert to wild forms after breeding");
        fewerDrops = cfg.getBoolean("Fewer Drops", CATEGORY_MOBS, fewerDrops, "Set to true to make mobs drop fewer items as in vanilla");
        smoothGrowth = cfg.getBoolean("Smooth Growth", CATEGORY_MOBS, smoothGrowth, "Set to true for new mobs to grow smoothly in size to adulthood");
        sizeVariance = cfg.getBoolean("Size Variance", CATEGORY_MOBS, sizeVariance, "Set to true to enable individual size variance for many new mobs, in addition to any variance from sex/age");
        noVanillaAnimals = cfg.getBoolean("No Vanilla Animals Spawns", CATEGORY_MOBS, noVanillaAnimals, "Set to true to prevent natural vanilla mobs");
        noUnnaturalMobs = cfg.getBoolean("No Unnatural Mob Spawns", CATEGORY_MOBS, noUnnaturalMobs, "Set to true to prevent unnatural vanilla mobs like giant spiders and undead");
        enableSexes = cfg.getBoolean("Enable Sexes", CATEGORY_MOBS, enableSexes, "Set to true to enable male and female mobs for more complex breeding and behavoir in new mobs");
        enableAdvancedStats = cfg.getBoolean("Enable Advanced Stats", CATEGORY_MOBS, enableAdvancedStats, "Set to true to enable hunger, happiness, taming and more detailed aging in new mobs");
        enableStarvation = cfg.getBoolean("Enable Starvation", CATEGORY_MOBS, enableStarvation, "Set to true to enable new animals to starve, requires Advanced Stats");
        enableOldAge = cfg.getBoolean("Enable Old Age", CATEGORY_MOBS, enableOldAge, "Set to true to enable new animals to die of old age, requires Advanced Stats");
        enablePregnancy = cfg.getBoolean("Enable Pregnancy", CATEGORY_MOBS, enablePregnancy, "Set to true to enable pregnancy in new animals, requires Advanced Stats and Sexes");
        enableLitters = cfg.getBoolean("Enable Litters", CATEGORY_MOBS, enableLitters, "Set to true to enable litters of offspring in new animals, requires Advanced Stats and Pregnancy");
        enableHorses = cfg.getBoolean("Enable Expanded Equines", CATEGORY_MOBS, enableHorses, "Set to true to enable new horses, donkeys, zebras, hybrids and quaggas");
        newHorseBreeding = cfg.getBoolean("New Horse Breeding", CATEGORY_MOBS, newHorseBreeding, "Set to true to replace normal breeding with more stable inheritence, requires Expanded Equines");
        wildHorses = cfg.getBoolean("Wild Horses", CATEGORY_MOBS, wildHorses, "Set to true to replace horse and donkey spawns with wild horses and wild asses that require domestication, requires Expanded Equines");
        enableCattle = cfg.getBoolean("Enable Expanded Bovines", CATEGORY_MOBS, enableCattle, "Set to true to enable new cattle, aurochs and mooshrooms");
        wildBovine = cfg.getBoolean("Wild Bovine", CATEGORY_MOBS, wildBovine, "Set to true to replace cattle spawns with aurochs that require domestication, requires Expanded Bovines");
        enablePigs = cfg.getBoolean("Enable Expanded Porcines", CATEGORY_MOBS, enablePigs, "Set to true to enable new pigs and wild boar");
        wildPorcines = cfg.getBoolean("Wild Porcines", CATEGORY_MOBS, wildPorcines, "Set to true to replace pigs spawns with Wild Boar that require domestication, requires Expanded Porcines");
        enableSheep = cfg.getBoolean("Enable Expanded Sheep", CATEGORY_MOBS, enableSheep, "Set to true to enable new sheep and mouflon");
        wildSheep = cfg.getBoolean("Wild Sheep", CATEGORY_MOBS, wildSheep, "Set to true to replace sheep spawns with mouflon that require domestication, requires Expanded Sheep");
        enableLlamas = cfg.getBoolean("Enable Expanded Llamas", CATEGORY_MOBS, enableLlamas, "Set to true to enable new sheep and mouflon");
        wildLlamas = cfg.getBoolean("Wild Llamas", CATEGORY_MOBS, wildLlamas, "Set to true to replace sheep spawns with mouflon that require domestication, requires Expanded Llamas");
        enableFowl = cfg.getBoolean("Enable Expanded Fowls", CATEGORY_MOBS, enableFowl, "Set to true to enable new junglefowl, chickens and ducks");
        wildFowl = cfg.getBoolean("Wild Fowls", CATEGORY_MOBS, wildFowl, "Set to true to replace sheep chickens and ducks with junglefowl and mallards that require domestication, requires Expanded Fowls");
        enableBears = cfg.getBoolean("Enable Expanded Bears", CATEGORY_MOBS, enableBears, "Set to true to enable new bears. NOTE: Currently placeholder that only disables vanilla spawn");
        enableCats = cfg.getBoolean("Enable Expanded Cats", CATEGORY_MOBS, enableCats, "Set to true to enable new cats. NOTE: Currently placeholder that only disables vanilla spawn");
        enableRabbits = cfg.getBoolean("Enable Expanded Rabbits", CATEGORY_MOBS, enableRabbits, "Set to true to enable new rabbits. NOTE: Currently placeholder that only disables vanilla spawn");
        enableCanines = cfg.getBoolean("Enable Expanded Canines", CATEGORY_MOBS, enableCanines, "Set to true to enable new canines. NOTE: Currently placeholder that only disables vanilla spawn");
        enableParrots = cfg.getBoolean("Enable Expanded Parrots", CATEGORY_MOBS, enableParrots, "Set to true to enable new parrots. NOTE: Currently placeholder that only disables vanilla spawn");
        enableWildBreeding = cfg.getBoolean("Enable Wild Breeding", CATEGORY_MOBS, enableWildBreeding, "Set to true to enable new mobs to breed naturally based on population size and happiness. Requires Advanced Stats, disables automatic hand breeding.");
        dynamicBiomes = cfg.getBoolean("Dynamic Biome Assignment", CATEGORY_MOBS, dynamicBiomes, "Set to true to automatically assign mob spawns to new biomes based on biome dictionary information");
    }

    private static void initToolConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_TOOLS, "Tool configuration");
        waterBowls = cfg.getBoolean("Water Bowls", CATEGORY_TOOLS, waterBowls, "Set to true to allow bowls to be filled with water and enable raw soup recipes");
        enableFlintTools = cfg.getBoolean("Enable Flint Tools", CATEGORY_TOOLS, enableFlintTools, "Set to true to allow construction of simple flint tools");
        enableKnives = cfg.getBoolean("Enable Knives", CATEGORY_TOOLS, enableKnives, "Set to true to allow construction of knives for crafting and combat");
        boneArrows = cfg.getBoolean("Enable Bone Arrows", CATEGORY_TOOLS, boneArrows, "Set to true to allow construction of arrows using bone shards");
        ironArrows = cfg.getBoolean("Enable Iron Arrows", CATEGORY_TOOLS, ironArrows, "Set to true to allow construction of arrows using iron nuggets");
        craftableSaddles = cfg.getBoolean("Craftable Saddles", CATEGORY_TOOLS, craftableSaddles, "Set to true to allow construction of saddles");
        craftableNametags = cfg.getBoolean("Craftable Nametags", CATEGORY_TOOLS, craftableNametags, "Set to true to allow construction of nametags");
        craftableHorseArmor = cfg.getBoolean("Craftable Horse Armor", CATEGORY_TOOLS, craftableHorseArmor, "Set to true to allow construction of horse armors");
        enableNewTorches = cfg.getBoolean("Enable New Torch Recipes", CATEGORY_TOOLS, enableNewTorches, "Set to true to enable new recipes for torches");
        bedRecipes = cfg.getBoolean("Enable New Bed Recipes", CATEGORY_TOOLS, bedRecipes, "Set to true to enable new recipes for beds");

        disableVanillaTorches = cfg.getBoolean("Disable Vanilla Torch Recipes", CATEGORY_TOOLS, disableVanillaTorches, "Set to true to remove vanilla recipes for torches");
    }
}
