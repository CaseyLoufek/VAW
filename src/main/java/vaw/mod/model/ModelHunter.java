package vaw.mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelCow - Either Mojang or a mod author
 * Created using Tabula 6.0.0
 */
public class ModelHunter extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Hat;
    public ModelRenderer Body;
    public ModelRenderer CoatLayer;
    public ModelRenderer MiddleClosedArm;
    public ModelRenderer RightOpenArm;
    public ModelRenderer LeftOpenArm;
    public ModelRenderer RightLeg;
    public ModelRenderer LeftLeg;
    public ModelRenderer Nose;
    public ModelRenderer Hood;
    public ModelRenderer RightClosedArm;
    public ModelRenderer LeftClosedArm;

    public ModelHunter() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.RightLeg = new ModelRenderer(this, 0, 18);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Hood = new ModelRenderer(this, 10, 41);
        this.Hood.setRotationPoint(0.0F, -10.0F, 4.0F);
        this.Hood.addBox(-3.0F, 0.0F, 0.0F, 6, 8, 7, 0.0F);
        this.setRotateAngle(Hood, -0.3490658503988659F, 0.0F, 0.0F);
        this.Nose = new ModelRenderer(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -3.0F, -4.0F);
        this.Nose.addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2, 0.0F);
        this.CoatLayer = new ModelRenderer(this, 36, 40);
        this.CoatLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CoatLayer.addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F);
        this.RightOpenArm = new ModelRenderer(this, 44, 18);
        this.RightOpenArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.RightOpenArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.RightClosedArm = new ModelRenderer(this, 0, 34);
        this.RightClosedArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightClosedArm.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.LeftLeg = new ModelRenderer(this, 0, 18);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.MiddleClosedArm = new ModelRenderer(this, 0, 56);
        this.MiddleClosedArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleClosedArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleClosedArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.Body = new ModelRenderer(this, 16, 18);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.LeftClosedArm = new ModelRenderer(this, 0, 34);
        this.LeftClosedArm.mirror = true;
        this.LeftClosedArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftClosedArm.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.Hat = new ModelRenderer(this, 32, 0);
        this.Hat.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.25F);
        this.LeftOpenArm = new ModelRenderer(this, 44, 18);
        this.LeftOpenArm.mirror = true;
        this.LeftOpenArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LeftOpenArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.Head.addChild(this.Hood);
        this.Head.addChild(this.Nose);
        this.MiddleClosedArm.addChild(this.RightClosedArm);
        this.MiddleClosedArm.addChild(this.LeftClosedArm);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.RightLeg.render(f5);
        this.CoatLayer.render(f5);
        this.RightOpenArm.render(f5);
        this.Head.render(f5);
        this.LeftLeg.render(f5);
        this.MiddleClosedArm.render(f5);
        this.Body.render(f5);
        this.Hat.render(f5);
        this.LeftOpenArm.render(f5);
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
