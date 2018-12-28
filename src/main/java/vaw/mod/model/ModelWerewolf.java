package vaw.mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import vaw.mod.util.Config;

/**
 * ModelCow - Either Mojang or a mod author
 * Created using Tabula 6.0.0
 */
public class ModelWerewolf extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer Snout;
    public ModelRenderer Mouth;
    public ModelRenderer Ear1;
    public ModelRenderer Ear2;
    public ModelRenderer Body2;
    public ModelRenderer Tail1;

    public ModelWerewolf() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftArm = new ModelRenderer(this, 10, 43);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(5.5F, 1.5F, 0.0F);
        this.LeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Tail1 = new ModelRenderer(this, 0, 43);
        this.Tail1.setRotationPoint(0.0F, 7.5F, 2.0F);
        this.Tail1.addBox(-1.5F, 0.0F, -1.0F, 3, 10, 2, 0.0F);
        this.setRotateAngle(Tail1, 0.7853981633974483F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 10, 43);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Mouth = new ModelRenderer(this, 46, 8);
        this.Mouth.setRotationPoint(0.0F, -1.0F, -5.0F);
        this.Mouth.addBox(-2.0F, 0.0F, -5.0F, 4, 1, 5, 0.0F);
        this.setRotateAngle(Mouth, 0.2617993877991494F, 0.0F, 0.0F);
        this.Body1 = new ModelRenderer(this, 0, 16);
        this.Body1.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.Body1.addBox(-5.0F, 0.0F, -3.5F, 10, 6, 7, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -0.5F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -5.0F, 8, 8, 8, 0.0F);
        this.Body2 = new ModelRenderer(this, 0, 29);
        this.Body2.setRotationPoint(0.0F, 4.5F, 0.0F);
        this.Body2.addBox(-4.0F, 0.0F, -2.5F, 8, 9, 5, 0.0F);
        this.Snout = new ModelRenderer(this, 46, 0);
        this.Snout.setRotationPoint(0.0F, -1.0F, -5.0F);
        this.Snout.addBox(-2.0F, -3.0F, -5.0F, 4, 3, 5, 0.0F);
        this.RightArm = new ModelRenderer(this, 10, 43);
        this.RightArm.setRotationPoint(-5.5F, 1.5F, 0.0F);
        this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightLeg = new ModelRenderer(this, 10, 43);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Ear1 = new ModelRenderer(this, 40, 0);
        this.Ear1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Ear1.addBox(-3.5F, -10.0F, 0.0F, 3, 2, 2, 0.0F);
        this.Ear2 = new ModelRenderer(this, 40, 0);
        this.Ear2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Ear2.addBox(0.5F, -10.0F, 0.0F, 3, 2, 2, 0.0F);
        this.Body2.addChild(this.Tail1);
        this.Head.addChild(this.Mouth);
        this.Body1.addChild(this.Body2);
        this.Head.addChild(this.Snout);
        this.Head.addChild(this.Ear1);
        this.Head.addChild(this.Ear2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.LeftArm.render(f5);
        this.LeftLeg.render(f5);
        this.Body1.render(f5);
        this.Head.render(f5);
        this.RightArm.render(f5);
        this.RightLeg.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
