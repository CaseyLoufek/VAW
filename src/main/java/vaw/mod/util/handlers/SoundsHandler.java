package vaw.mod.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vaw.mod.util.Reference;

public class SoundsHandler 
{

	public static SoundEvent AMBIENT_ZEBRA;
	public static SoundEvent HURT_ZEBRA;
	public static SoundEvent DEATH_ZEBRA;
	public static SoundEvent ANGRY_ZEBRA;

	public static SoundEvent AMBIENT_DUCK;
	public static SoundEvent HURT_DUCK;
	public static SoundEvent DEATH_DUCK;
	public static SoundEvent ANGRY_DUCK;
	
	public static void registerSounds()
	{
		AMBIENT_ZEBRA = registerSound("mob.zebra.ambient");
		HURT_ZEBRA = registerSound("mob.zebra.hurt");
		ANGRY_ZEBRA = registerSound("mob.zebra.angry");
		DEATH_ZEBRA = registerSound("mob.zebra.death");

		AMBIENT_DUCK = registerSound("mob.duck.ambient");
		HURT_DUCK = registerSound("mob.duck.hurt");
		ANGRY_DUCK = registerSound("mob.duck.angry");
		DEATH_DUCK = registerSound("mob.duck.death");
	}
	
	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(Reference.MODID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
