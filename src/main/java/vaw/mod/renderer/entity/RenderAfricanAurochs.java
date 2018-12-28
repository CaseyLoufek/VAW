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
import vaw.mod.entity.passive.EntityAurochs;
import vaw.mod.model.ModelAfricanAurochs;
import vaw.mod.util.Config;

@SideOnly(Side.CLIENT)
public class RenderAfricanAurochs extends RenderLiving<EntityAurochs>
{
    public static final Factory FACTORY = new Factory();

    private static final ResourceLocation COW_TEXTURES = new ResourceLocation("faunaandecology:textures/entity/cow/aurochs_african_female.png");
    private static final ResourceLocation BULL_TEXTURES = new ResourceLocation("faunaandecology:textures/entity/cow/aurochs_african_male.png");
    private static final ResourceLocation CALF_TEXTURES = new ResourceLocation("faunaandecology:textures/entity/cow/aurochs_juvenile.png");

    public RenderAfricanAurochs(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelAfricanAurochs(), 0.5F);
    }


    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    @Override
	protected void preRenderCallback(EntityAurochs entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.95F;

        //larger males
		if (Config.enableSexes == true)
		{
	        if (entitylivingbaseIn.getSex() == 1)	f *= 1.10F;
        }

        //child scaling
        if (entitylivingbaseIn.isChild())
        {
        	f = 0.7f;
        	if (Config.smoothGrowth == true) f += 0.3F * entitylivingbaseIn.getGrowthSize();        	
        }
        
        //size variance
        if (Config.sizeVariance == true) f *= 1.0F + ((-25.0F + entitylivingbaseIn.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()) / 40.0F);

        GlStateManager.scale(f, f, f);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
	@Override
    protected ResourceLocation getEntityTexture(EntityAurochs entity)
    {
        if (entity.isChild())
        {
            return CALF_TEXTURES;
        }

		if (Config.enableSexes == true)
		{
	        if (entity.getSex() == 1) return BULL_TEXTURES;
        }
        return COW_TEXTURES;
    }
    
    public static class Factory implements IRenderFactory<EntityAurochs> {

        @Override
        public Render<? super EntityAurochs> createRenderFor(RenderManager manager) {
            return new RenderAfricanAurochs(manager);
        }

    }
}