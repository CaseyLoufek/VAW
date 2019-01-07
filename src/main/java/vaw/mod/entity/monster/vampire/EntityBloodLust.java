package vaw.mod.entity.monster.vampire;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityBloodLust extends EntityVampireMaster {

    public EntityBloodLust(World worldIn) {
        super(worldIn);
        wander.setExecutionChance(10);
    }

    public void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(11.0);
    }

    public boolean attackEntityAsMob(Entity entityIn)
    {
        return entityIn.attackEntityFrom(DamageSource.causeFireballDamage(new EntityLargeFireball(this.world), entityIn), (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()) && super.attackEntityAsMob(entityIn);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BAT_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BAT_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) { return SoundEvents.ENTITY_BAT_HURT; }

    protected float getSoundPitch()
    {
        return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F;
    }

    void check(){
        if(getAttackTarget() == null){
            this.turnToBL();
        }
    }

    void turnToBL() {
        EntityVampireMaster master = new EntityVampireMaster(this.world);
        master.copyLocationAndAnglesFrom(this);
        master.setPosition(master.posX, master.posY, master.posY);
        master.setNoAI(this.isAIDisabled());
        master.setHealth(getMaxHealth()-getHealth()<6?getMaxHealth():getHealth()+6);
        if (this.hasCustomName()) {
            master.setCustomNameTag(this.getCustomNameTag());
            master.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
        }
        this.world.removeEntity(this);
        this.world.spawnEntity(master);
    }
}
