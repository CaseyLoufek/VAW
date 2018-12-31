package vaw.mod.init;


import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vaw.mod.Main;
import vaw.mod.entity.monster.EntityVampire;
import vaw.mod.renderer.entity.RenderVampire;
import vaw.mod.util.Reference;

public class EntityInit {

    private static int entityId = 1;
    
    public static void init()
    {
    }

    public static void postInit()
    {
    	registerEntity("vampire", EntityVampire.class, entityId++, 1116416,  4073990);
    }
    private static void registerEntity(String name, Class<? extends Entity> cls, int id)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), cls, name, id++, Main.instance, 80, 3, true);
    }

    private static void registerEntity(String name, Class<? extends Entity> cls, int id, int primaryEggColor, int secondaryEggColor)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), cls, name, id++, Main.instance, 80, 3, true, primaryEggColor, secondaryEggColor);
    }
    

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityVampire.class, RenderVampire.FACTORY);
    }

}