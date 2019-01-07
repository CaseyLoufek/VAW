package vaw.mod.entity.monster.vampire;

import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vaw.mod.init.ItemInit;

import javax.annotation.Nonnull;

@SuppressWarnings("Duplicates")
public class EntityVampireBat extends EntityBat {

    private int lastCheck = 0;
    boolean stayBat = false;

    public EntityVampireBat(World worldIn) {
        super(worldIn);
    }

    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount) {
        if (source == DamageSource.OUT_OF_WORLD || source.isCreativePlayer() || source == DamageSource.IN_FIRE || source == DamageSource.ON_FIRE) return super.attackEntityFrom(source, amount);
        if (source.getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) source.getTrueSource();
            Item item = player.getHeldItem(player.getActiveHand()).getItem();
            if (item.getUnlocalizedName().contains("wood")) {
                this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
                return super.attackEntityFrom(source, amount + (item == ItemInit.STAKE_WOOD ? 5 : 4));
            }
        }
        return !(getHealth()<=1) && super.attackEntityFrom(source, amount);
    }

    public void onLivingUpdate() {
        if (!this.world.isRemote) {
            if (this.ticksExisted - this.lastCheck > 60000) {
                this.lastCheck = this.ticksExisted;
                if (this.rand.nextInt(2) == 0) {
                    this.turnToVamp();
                }
            }
            if (this.world.isDaytime()) {
                float f = this.getBrightness();
                if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ))) {
                    this.attackEntityFrom(DamageSource.IN_FIRE, 1);
                }
            }
        }
        super.onLivingUpdate();
    }

    private void turnToVamp() {
        EntityVampire vamp = new EntityVampire(this.world);
        vamp.copyLocationAndAnglesFrom(this);
        vamp.setPosition(vamp.posX, vamp.posY, vamp.posY);
        vamp.setNoAI(this.isAIDisabled());
        vamp.setHealth(getMaxHealth()-getHealth()<6?getMaxHealth():getHealth()+6);
        if (this.hasCustomName()) {
            vamp.setCustomNameTag(this.getCustomNameTag());
            vamp.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
        }
        this.world.removeEntity(this);
        this.world.spawnEntity(vamp);
    }
}
