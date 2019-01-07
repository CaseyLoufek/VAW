package vaw.mod.renderer.entity.vampire;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import vaw.mod.entity.monster.vampire.EntityVampire;
import vaw.mod.entity.monster.vampire.EntityVampireMaster;
import vaw.mod.model.ModelBloodLust;
import vaw.mod.util.Reference;

import javax.annotation.Nullable;

public class RenderBloodLust extends RenderVampireMaster {

    private RenderBloodLust(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelBloodLust());
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nullable EntityVampire entity) {
        return new ResourceLocation(Reference.MODID + ":textures/entity/vampire/blood_lust.png");
    }

    public static class RenderFactory implements IRenderFactory<EntityVampireMaster> {
        @Override
        public Render<? super EntityVampireMaster> createRenderFor(RenderManager manager) {
            return new RenderBloodLust(manager);
        }
    }
}
