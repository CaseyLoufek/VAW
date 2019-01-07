package vaw.mod.renderer.entity.vampire;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vaw.mod.entity.monster.vampire.EntityVampire;
import vaw.mod.model.ModelVampire;
import vaw.mod.util.Reference;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderVampire extends RenderLiving<EntityVampire>
{
    RenderVampire(RenderManager renderManagerIn, ModelBase model) {
        super(renderManagerIn, model, 0.5F);
    }

	@Override
    protected ResourceLocation getEntityTexture(@Nullable EntityVampire entity) {
        return new ResourceLocation(Reference.MODID + ":textures/entity/vampire/vampire.png");
    }
    
    public static class RenderFactory implements IRenderFactory<EntityVampire> {
        @Override
        public Render<? super EntityVampire> createRenderFor(RenderManager manager) {
            return new RenderVampire(manager, new ModelVampire());
        }
    }
}
