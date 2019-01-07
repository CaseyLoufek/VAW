package vaw.mod.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vaw.mod.Main;
import vaw.mod.entity.monster.vampire.EntityBloodLust;
import vaw.mod.entity.monster.vampire.EntityVampire;
import vaw.mod.entity.monster.vampire.EntityVampireBat;
import vaw.mod.entity.monster.vampire.EntityVampireMaster;
import vaw.mod.renderer.entity.vampire.RenderBloodLust;
import vaw.mod.renderer.entity.vampire.RenderVampire;
import vaw.mod.renderer.entity.vampire.RenderVampireBat;
import vaw.mod.renderer.entity.vampire.RenderVampireMaster;
import vaw.mod.util.Reference;

public class EntityInit {

    private static int entityId = 0;

    public static void init() {
        registerEntity("vampire", EntityVampire.class, entityId++, 1116416,  4073990);
        registerEntity("bat_vamp", EntityVampireBat.class, entityId++);
        registerEntity("master_vamp", EntityVampireMaster.class, entityId++, 1116416,  4073990);
        registerEntity("blood_lust", EntityBloodLust.class, entityId++, 1116416,  4073990);
    }

    private static void registerEntity(String name, Class<? extends Entity> cls, int id) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, name), cls, name, id++, Main.instance, 80, 3, true);
    }

    private static void registerEntity(String name, Class<? extends Entity> cls, int id, int primaryEggColor, int secondaryEggColor) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, name), cls, name, id++, Main.instance, 80, 3, true, primaryEggColor, secondaryEggColor);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityVampire.class, new RenderVampire.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityVampireBat.class, new RenderVampireBat.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityVampireMaster.class, new RenderVampireMaster.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityBloodLust.class, new RenderBloodLust.RenderFactory());
    }

}
