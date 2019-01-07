package vaw.mod.renderer.entity.vampire;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import vaw.mod.entity.monster.vampire.EntityVampire;
import vaw.mod.entity.monster.vampire.EntityVampireMaster;
import vaw.mod.model.ModelVampire;
import vaw.mod.util.Reference;

import javax.annotation.Nullable;

public class RenderVampireMaster extends RenderVampire {

    RenderVampireMaster(RenderManager renderManagerIn, ModelBase model) {
        super(renderManagerIn, model);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nullable EntityVampire entity) {
        return new ResourceLocation(Reference.MODID + ":textures/entity/vampire/master.png");
    }

    public static class RenderFactory implements IRenderFactory<EntityVampireMaster> {
        @Override
        public Render<? super EntityVampireMaster> createRenderFor(RenderManager manager) {
            return new RenderVampireMaster(manager, new ModelVampire());
        }
    }
}
