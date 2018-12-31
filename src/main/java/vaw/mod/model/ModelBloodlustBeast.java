package vaw.mod.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelCow - Either Mojang or a mod author
 * Created using Tabula 6.0.0
 */
public class ModelBloodlustBeast extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer RightEar;
    public ModelRenderer LeftEar;
    public ModelRenderer RightLeg1;
    public ModelRenderer LeftLeg1;
    public ModelRenderer RightWing1;
    public ModelRenderer LeftWing1;
    public ModelRenderer RightWing2;
    public ModelRenderer LeftWing2;

    public ModelBloodlustBeast() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.LeftWing1 = new ModelRenderer(this, 20, 16);
        this.LeftWing1.mirror = true;
        this.LeftWing1.setRotationPoint(3.0F, 7.0F, 0.0F);
        this.LeftWing1.addBox(0.0F, -7.5F, 0.0F, 10, 15, 1, 0.0F);
        this.setRotateAngle(LeftWing1, 0.0F, -0.8726646259971648F, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.Head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.0F);
        this.LeftLeg1 = new ModelRenderer(this, 0, 14);
        this.LeftLeg1.setRotationPoint(2.0F, 12.0F, -1.0F);
        this.LeftLeg1.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.RightLeg1 = new ModelRenderer(this, 0, 14);
        this.RightLeg1.setRotationPoint(-2.0F, 12.0F, -1.0F);
        this.RightLeg1.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.LeftEar = new ModelRenderer(this, 18, 0);
        this.LeftEar.setRotationPoint(2.5F, -3.0F, -2.5F);
        this.LeftEar.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 1, 0.0F);
        this.LeftWing2 = new ModelRenderer(this, 42, 16);
        this.LeftWing2.mirror = true;
        this.LeftWing2.setRotationPoint(10.0F, 0.0F, 0.0F);
        this.LeftWing2.addBox(0.0F, -7.5F, 0.0F, 10, 15, 1, 0.0F);
        this.setRotateAngle(LeftWing2, 0.0F, 1.3962634015954636F, 0.0F);
        this.RightWing1 = new ModelRenderer(this, 20, 16);
        this.RightWing1.setRotationPoint(-3.0F, 7.0F, 0.0F);
        this.RightWing1.addBox(-10.0F, -7.5F, 0.0F, 10, 15, 1, 0.0F);
        this.setRotateAngle(RightWing1, 0.0F, 0.8726646259971648F, 0.0F);
        this.Body = new ModelRenderer(this, 38, 0);
        this.Body.setRotationPoint(0.0F, 2.0F, 0.0F);
        this.Body.addBox(-3.5F, 2.0F, -4.0F, 7, 10, 6, 0.0F);
        this.RightEar = new ModelRenderer(this, 18, 0);
        this.RightEar.setRotationPoint(-2.5F, -3.0F, -2.5F);
        this.RightEar.addBox(-1.5F, -5.0F, 0.0F, 3, 5, 1, 0.0F);
        this.RightWing2 = new ModelRenderer(this, 42, 16);
        this.RightWing2.setRotationPoint(-10.0F, 0.0F, 0.0F);
        this.RightWing2.addBox(-10.0F, -7.5F, 0.0F, 10, 15, 1, 0.0F);
        this.setRotateAngle(RightWing2, 0.0F, -1.3962634015954636F, 0.0F);
        this.Body.addChild(this.LeftWing1);
        this.Body.addChild(this.LeftLeg1);
        this.Body.addChild(this.RightLeg1);
        this.Head.addChild(this.LeftEar);
        this.LeftWing1.addChild(this.LeftWing2);
        this.Body.addChild(this.RightWing1);
        this.Head.addChild(this.RightEar);
        this.RightWing1.addChild(this.RightWing2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Head.render(f5);
        this.Body.render(f5);
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
