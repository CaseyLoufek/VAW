package vaw.mod.model;

import net.minecraft.client.model.ModelRenderer;

/**
 * ModelCow - Either Mojang or a mod author
 * Created using Tabula 6.0.0
 */
public class ModelAfricanAurochs extends ModelAurochs
{

    public ModelRenderer body;
    public ModelRenderer right_hind_leg;
    public ModelRenderer right_front_leg;
    public ModelRenderer left_hind_leg;
    public ModelRenderer left_front_leg;
    public ModelRenderer chest_right;
    public ModelRenderer chest_left;
    public ModelRenderer tail_middle;
    public ModelRenderer neck;
    public ModelRenderer saddle;
    public ModelRenderer body_2;
    public ModelRenderer tail_tuft;
    public ModelRenderer head;
    public ModelRenderer shape24;
    public ModelRenderer snout;
    public ModelRenderer ear_right;
    public ModelRenderer ear_left;
    public ModelRenderer horn_male_left;
    public ModelRenderer horn_male_right;
    public ModelRenderer left_horn_female;
    public ModelRenderer left_horn_female_1;
    public ModelRenderer right_horn_male;
    public ModelRenderer right_horn_male_1;

    public ModelAfricanAurochs() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.left_front_leg = new ModelRenderer(this, 47, 32);
        this.left_front_leg.mirror = true;
        this.left_front_leg.setRotationPoint(-2.9F, 11.0F, -5.5F);
        this.left_front_leg.addBox(-2.0F, 0.0F, -2.0F, 3, 13, 4, 0.0F);
        this.body = new ModelRenderer(this, 0, 16);
        this.body.setRotationPoint(1.0F, 7.0F, 1.51F);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 9, 24, 12, 0.0F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.right_front_leg = new ModelRenderer(this, 47, 32);
        this.right_front_leg.setRotationPoint(2.9F, 11.0F, -5.5F);
        this.right_front_leg.addBox(-2.0F, 0.0F, -2.0F, 3, 13, 4, 0.0F);
        this.right_hind_leg = new ModelRenderer(this, 47, 32);
        this.right_hind_leg.mirror = true;
        this.right_hind_leg.setRotationPoint(2.9F, 11.0F, 12.5F);
        this.right_hind_leg.addBox(-2.0F, 0.0F, -2.0F, 3, 13, 4, 0.0F);
        this.left_horn_female = new ModelRenderer(this, 43, 0);
        this.left_horn_female.setRotationPoint(4.0F, -3.14F, 1.38F);
        this.left_horn_female.addBox(-5.0F, -5.0F, -4.0F, 1, 6, 1, 0.0F);
        this.setRotateAngle(left_horn_female, 1.1344640137963142F, 0.0F, 0.0F);
        this.right_horn_male = new ModelRenderer(this, 43, 0);
        this.right_horn_male.setRotationPoint(-4.0F, -3.14F, 1.38F);
        this.right_horn_male.addBox(4.0F, -5.0F, -4.0F, 1, 6, 1, 0.0F);
        this.setRotateAngle(right_horn_male, 1.1344640137963142F, 0.0F, 0.0F);
        this.chest_left = new ModelRenderer(this, 48, 0);
        this.chest_left.setRotationPoint(-7.0F, 2.1F, 13.0F);
        this.chest_left.addBox(0.0F, 0.0F, 0.0F, 8, 8, 3, 0.0F);
        this.setRotateAngle(chest_left, 0.0F, 1.5707963267948966F, 0.0F);
        this.head = new ModelRenderer(this, 1, 0);
        this.head.setRotationPoint(0.0F, -1.7F, -2.9F);
        this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 8, 6, 0.0F);
        this.setRotateAngle(head, 0.5235987755982988F, 0.0F, 0.0F);
        this.chest_right = new ModelRenderer(this, 48, 12);
        this.chest_right.setRotationPoint(5.0F, 2.1F, 13.0F);
        this.chest_right.addBox(0.0F, 0.0F, 0.0F, 8, 8, 3, 0.0F);
        this.setRotateAngle(chest_right, 0.0F, 1.5707963267948966F, 0.0F);
        this.tail_tuft = new ModelRenderer(this, 16, 53);
        this.tail_tuft.setRotationPoint(-1.0F, -1.0F, 12.9F);
        this.tail_tuft.addBox(0.0F, 0.0F, 0.0F, 2, 2, 6, 0.0F);
        this.right_horn_male_1 = new ModelRenderer(this, 33, 0);
        this.right_horn_male_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right_horn_male_1.addBox(4.0F, -4.0F, -4.0F, 1, 5, 1, 0.0F);
        this.tail_middle = new ModelRenderer(this, 0, 53);
        this.tail_middle.setRotationPoint(-0.5F, 2.0F, 15.5F);
        this.tail_middle.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 13, 0.0F);
        this.setRotateAngle(tail_middle, -1.48352986419518F, 0.0F, 0.0F);
        this.horn_male_left = new ModelRenderer(this, 38, 0);
        this.horn_male_left.setRotationPoint(-5.7F, -5.7F, -1.1F);
        this.horn_male_left.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(horn_male_left, 0.0F, -0.4363323129985824F, -1.1344640137963142F);
        this.ear_left = new ModelRenderer(this, 23, 1);
        this.ear_left.mirror = true;
        this.ear_left.setRotationPoint(-6.2F, -2.8F, -1.1F);
        this.ear_left.addBox(1.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(ear_left, 0.0F, 0.0F, 0.08726646259971647F);
        this.saddle = new ModelRenderer(this, 71, 0);
        this.saddle.setRotationPoint(-5.5F, 1.9F, -2.0F);
        this.saddle.addBox(0.0F, 0.0F, 0.0F, 12, 8, 9, 0.0F);
        this.body_2 = new ModelRenderer(this, 47, 0);
        this.body_2.setRotationPoint(-5.5F, 1.2F, -8.5F);
        this.body_2.addBox(0.0F, 0.0F, 0.0F, 10, 13, 11, 0.0F);
        this.ear_right = new ModelRenderer(this, 23, 1);
        this.ear_right.setRotationPoint(1.2F, -2.5F, -1.1F);
        this.ear_right.addBox(1.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        this.setRotateAngle(ear_right, 0.0F, 0.0F, -0.08726646259971647F);
        this.shape24 = new ModelRenderer(this, 63, 50);
        this.shape24.setRotationPoint(-2.5F, -4.8F, -5.0F);
        this.shape24.addBox(0.0F, 0.0F, 0.0F, 5, 8, 8, 0.0F);
        this.snout = new ModelRenderer(this, 28, 7);
        this.snout.setRotationPoint(-2.5F, -1.8F, -10.0F);
        this.snout.addBox(0.0F, 0.0F, 0.0F, 5, 5, 4, 0.0F);
        this.horn_male_right = new ModelRenderer(this, 38, 0);
        this.horn_male_right.setRotationPoint(5.7F, -5.7F, -1.1F);
        this.horn_male_right.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(horn_male_right, 0.0F, 0.4363323129985824F, 1.1344640137963142F);
        this.left_horn_female_1 = new ModelRenderer(this, 33, 0);
        this.left_horn_female_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left_horn_female_1.addBox(-5.0F, -4.0F, -4.0F, 1, 5, 1, 0.0F);
        this.neck = new ModelRenderer(this, 63, 32);
        this.neck.setRotationPoint(-0.5F, 6.0F, -7.3F);
        this.neck.addBox(-2.5F, -5.0F, -5.0F, 5, 9, 7, 0.0F);
        this.setRotateAngle(neck, -0.2617993877991494F, 0.0F, 0.0F);
        this.left_hind_leg = new ModelRenderer(this, 47, 32);
        this.left_hind_leg.setRotationPoint(-2.9F, 11.0F, 12.5F);
        this.left_hind_leg.addBox(-2.0F, 0.0F, -2.0F, 3, 13, 4, 0.0F);
        this.horn_male_left.addChild(this.left_horn_female);
        this.horn_male_right.addChild(this.right_horn_male);
        this.neck.addChild(this.head);
        this.tail_middle.addChild(this.tail_tuft);
        this.right_horn_male.addChild(this.right_horn_male_1);
        this.head.addChild(this.horn_male_left);
        this.head.addChild(this.ear_left);
        this.head.addChild(this.ear_right);
        this.neck.addChild(this.shape24);
        this.head.addChild(this.snout);
        this.head.addChild(this.horn_male_right);
        this.left_horn_female.addChild(this.left_horn_female_1);
    }
    
}
