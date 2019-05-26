package vaw.mod.api;

public interface IWerewolf {
	
	public boolean isWerewolf();
	
	public void setWerewolf(boolean isWerewolf);
	
	public WerewolfType getTransform();
	
	public void setTransform(WerewolfType type);
	
	public void setHowlCooldown(int time);
	
	public int getHowlCooldown();
	
	public int combatTimer();
	
	public void setCombatTimer(int time);
	
	public void registerHit();
	
	public static enum WerewolfType {
		NONE, WOLF, WEREWOLF;
	}
}
