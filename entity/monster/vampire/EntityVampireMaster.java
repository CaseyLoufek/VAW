package vaw.mod.entity.monster.vampire;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityVampireMaster extends EntityVampire {

    public EntityVampireMaster(World worldIn) {
        super(worldIn);
    }

    public void applyEntityAttributes(){
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0);
    }

    void check(){
        if(getHealth() < 6){
            this.turnToBL();
        }
        else if (this.ticksExisted - this.lastCheck > 60000) {
            this.lastCheck = this.ticksExisted;
            if (this.rand.nextInt(2) == 0) {
                this.turn();
            }
        }
    }

    void turnToBL() {
        EntityBloodLust bloodLust = new EntityBloodLust(this.world);
        bloodLust.copyLocationAndAnglesFrom(this);
        bloodLust.setPosition(bloodLust.posX, bloodLust.posY, bloodLust.posY);
        bloodLust.setNoAI(this.isAIDisabled());
        bloodLust.setHealth(this.getHealth());
        if (this.hasCustomName()) {
            bloodLust.setCustomNameTag(this.getCustomNameTag());
            bloodLust.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
        }
        this.world.removeEntity(this);
        this.world.spawnEntity(bloodLust);
    }
}
