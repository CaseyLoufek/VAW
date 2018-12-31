package vaw.mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelCow - Either Mojang or a mod author
 * Created using Tabula 6.0.0
 */
public class ModelVampire extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer RightArm;
    public ModelRenderer LeftArm;
    public ModelRenderer ConjoinedArms;
    public ModelRenderer LeftLeg;
    public ModelRenderer Body;
    public ModelRenderer Clothing;
    public ModelRenderer RightLeg;
    public ModelRenderer Nose;

    public ModelVampire() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.LeftArm = new ModelRenderer(this, 44, 22);
        this.LeftArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.LeftArm.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(LeftArm, -0.7499679795819634F, 0.0F, 0.0F);
        this.Clothing = new ModelRenderer(this, 0, 38);
        this.Clothing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Clothing.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.Nose = new ModelRenderer(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.Nose.addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F);
        this.RightArm = new ModelRenderer(this, 44, 22);
        this.RightArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.RightArm.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.setRotateAngle(RightArm, -0.7499679795819634F, 0.0F, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 22);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightLeg = new ModelRenderer(this, 0, 22);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.ConjoinedArms = new ModelRenderer(this, 40, 38);
        this.ConjoinedArms.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.ConjoinedArms.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(ConjoinedArms, -0.7499679795819634F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 16, 20);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.Head.addChild(this.Nose);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.LeftArm.render(f5);
        this.Clothing.render(f5);
        this.RightArm.render(f5);
        this.LeftLeg.render(f5);
        this.RightLeg.render(f5);
        this.ConjoinedArms.render(f5);
        this.Body.render(f5);
        this.Head.render(f5);
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
