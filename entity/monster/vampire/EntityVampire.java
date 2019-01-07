package vaw.mod.entity.monster.vampire;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vaw.mod.init.ItemInit;

import javax.annotation.Nonnull;

@SuppressWarnings("Duplicates")
public class EntityVampire extends EntityMob {

    int lastCheck = 0;
    EntityAIWander wander;

    public EntityVampire(World worldIn) {
        super(worldIn);
        wander = new EntityAIWander(this, this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
        this.tasks.addTask(0, wander);
    }

    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount) {
        if (source == DamageSource.OUT_OF_WORLD || source.isCreativePlayer() || source == DamageSource.IN_FIRE || source == DamageSource.ON_FIRE) return super.attackEntityFrom(source, amount);
        if (source.getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) source.getTrueSource();
            Item item = player.getHeldItemMainhand().getItem();
            if (item.getUnlocalizedName().contains("wood") || item.getUnlocalizedName().contains("wood")) {
                this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                return super.attackEntityFrom(source, amount + (item == ItemInit.STAKE_WOOD ? 5 : 4));
            }
        }
        return !(getHealth()<=1) && super.attackEntityFrom(source, amount);
    }

    public void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.VINDICATION_ILLAGER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.VINDICATION_ILLAGER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_VINDICATION_ILLAGER_HURT;
    }

    public void onLivingUpdate() {
        if (!this.world.isRemote) {
            this.check();
            if (this.world.isDaytime()) {
                float f = this.getBrightness();
                if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ))) {
                    boolean flag = true;
                    ItemStack itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                    if (!itemstack.isEmpty()) {
                        if (itemstack.isItemStackDamageable()) {
                            itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

                            if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                                this.renderBrokenItemStack(itemstack);
                                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
                            }
                        }
                        flag = false;
                    }
                    if (flag) {
                        this.attackEntityFrom(DamageSource.IN_FIRE, 1);
                    }
                }
            }
        }
        super.onLivingUpdate();
    }

    void turn() {
        EntityVampireBat bat = new EntityVampireBat(this.world);
        bat.copyLocationAndAnglesFrom(this);
        bat.setPosition(bat.posX, bat.posY, bat.posY);
        bat.setNoAI(this.isAIDisabled());
        bat.setHealth(this.getHealth()>bat.getMaxHealth()?bat.getMaxHealth():this.getHealth());
        if (this.hasCustomName()) {
            bat.setCustomNameTag(this.getCustomNameTag());
            bat.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
        }
        if(this.rand.nextInt(2) == 0){
            bat.stayBat = true;
        }
        this.world.removeEntity(this);
        this.world.spawnEntity(bat);
    }

    void check(){
        if (this.getHealth() < 6) {
            this.turn();
        } else if (this.ticksExisted - this.lastCheck > 60000) {
            this.lastCheck = this.ticksExisted;
            if (this.rand.nextInt(2) == 0) {
                this.turn();
            }
        }
    }
}
