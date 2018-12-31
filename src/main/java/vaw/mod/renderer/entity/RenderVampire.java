package vaw.mod.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vaw.mod.entity.monster.EntityVampire;
import vaw.mod.model.ModelVampire;
import vaw.mod.util.Config;

@SideOnly(Side.CLIENT)
public class RenderVampire extends RenderLiving<EntityVampire>
{
    public static final Factory FACTORY = new Factory();

    private static final ResourceLocation TEXTURES = new ResourceLocation("vaw:textures/entity/vampire/vampire.png");

    public RenderVampire(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelVampire(), 0.5F);
    }


    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    @Override
	protected void preRenderCallback(EntityVampire entitylivingbaseIn, float partialTickTime)
    {
        float f = 1.00F;

        
        //size variance
        //if (Config.sizeVariance == true) f *= 1.0F + ((-25.0F + entitylivingbaseIn.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()) / 40.0F);

        GlStateManager.scale(f, f, f);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	@Override
    protected ResourceLocation getEntityTexture(EntityVampire entity)
    {
        return TEXTURES;
    }
    
    public static class Factory implements IRenderFactory<EntityVampire> {

        @Override
        public Render<? super EntityVampire> createRenderFor(RenderManager manager) {
            return new RenderVampire(manager);
        }

    }

}