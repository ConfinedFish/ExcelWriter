package test.SubType;

public enum EnchantmentSubType{
	AURA("Aura"),
	CARTOUCHE("Cartouche"),
	CURSE("Curse"),
	SAGA("Saga"),
	SHRINE("Shrine");
	
	String desc;
	EnchantmentSubType(String desc){
		this.desc = desc;
	}
}
