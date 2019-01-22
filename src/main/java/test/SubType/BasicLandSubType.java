package test.SubType;

public enum BasicLandSubType implements SubType{
	FOREST("Forest"),
	ISLAND("Island"),
	MOUNTAIN("Mountain"),
	PLAINS("Plains"),
	SWAMP("Swamp");
	
	String desc;
	BasicLandSubType(String desc){
		this.desc = desc;
	}
	
	@Override
	public String getDesc(){
		return desc;
	}
}
