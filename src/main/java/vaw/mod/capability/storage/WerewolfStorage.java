package vaw.mod.capability.storage;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import vaw.mod.api.IWerewolf;

public class WerewolfStorage implements Capability.IStorage<IWerewolf> {

	@Override
	public NBTBase writeNBT(Capability<IWerewolf> capability, IWerewolf instance, EnumFacing side) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("combatTimer", instance.combatTimer());
		tag.setInteger("howlCooldown", instance.getHowlCooldown());
		tag.setInteger("transformType", instance.getTransform().ordinal());
		tag.setBoolean("isWerewolf", instance.isWerewolf());
		return tag;
	}

	@Override
	public void readNBT(Capability<IWerewolf> capability, IWerewolf instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound tag = (NBTTagCompound)nbt;
		instance.setCombatTimer(tag.getInteger("combatTimer"));
		instance.setHowlCooldown(tag.getInteger("howlCooldown"));
		instance.setTransform(IWerewolf.WerewolfType.values()[tag.getInteger("transformType")]);
		instance.setWerewolf(tag.getBoolean("isWerewolf"));
	}

}
