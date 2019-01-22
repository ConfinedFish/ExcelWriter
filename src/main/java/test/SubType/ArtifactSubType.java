package test.SubType;

public enum ArtifactSubType implements SubType{
	CLUE("Clue"),
	CONTRAPTION("Contraption"),
	EQUIPMENT("Equipment"),
	FORTIFICATION("Fortification"),
	TREASURE("Treasure"),
	VEHICLE("Vehicle");
	
	String desc;
	ArtifactSubType(String desc){
		this.desc = desc;
	}
	
	@Override
	public String getDesc(){
		return desc;
	}
}
