package vaw.mod.renderer.entity.vampire;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import vaw.mod.entity.monster.vampire.EntityVampireBat;

public class RenderVampireBat extends RenderBat {

    private RenderVampireBat(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    public static class RenderFactory implements IRenderFactory<EntityVampireBat>{
        @Override
        public Render<? super EntityVampireBat> createRenderFor(RenderManager manager) {
            return new RenderVampireBat(manager);
        }
    }
}
