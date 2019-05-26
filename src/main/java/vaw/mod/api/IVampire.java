package vaw.mod.api;

public interface IVampire {

	public boolean isVampire();

	public void setVampire(boolean isVampire);

	public VampireType getTransform();

	public void setTransform(VampireType type);
	
	public static enum VampireType {
		NONE, BAT, BLOODBEAST;
	}
}
