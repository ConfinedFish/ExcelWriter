package test.SubType;

public enum PlanesWalkerSubType implements SubType{
	AJANI("Ajani"),
	AMINATOU("Aminatou"),
	ANGRATH("Angrath"),
	ARLINN("Arlinn"),
	ASHIOK("Ashiok"),
	AURRA("Aurra"), // Star Wars
	BOLAS("Bolas"),
	CHANDRA("Chandra"),
	DACK("Dack"),
	DARETTI("Daretti"),
	DOMRI("Domri"),
	DOOKU("Dooku"), // Star Wars
	DOVIN("Dovin"),
	ELSPETH("Elspeth"),
	ESTRID("Estrid"),
	FREYALISE("Freyalise"),
	GARRUK("Garruk"),
	GIDEON("Gideon"),
	HUATLI("Huatli"),
	JACE("Jace"),
	KARN("Karn"),
	KAYA("Kaya"),
	KIORA("Kiora"),
	KOTH("Koth"),
	LILIANA("Liliana"),
	NAHIRI("Nahiri"),
	NARSET("Narset"),
	NISSA("Nissa"),
	NIXILIS("Nixilis"),
	OBI_WAN("Obi-Wan"), // Star Wars
	RAL("Ral"),
	ROWAN("Rowan"),
	SAHEELI("Saheeli"),
	SAMUT("Samut"),
	SARKHAN("Sarkhan"),
	SIDIOUS("Sidious"), // Star Wars
	SORIN("Sorin"),
	TAMIYO("Tamiyo"),
	TEFERI("Teferi"),
	TEZZERET("Tezzeret"),
	TIBALT("Tibalt"),
	UGIN("Ugin"),
	URZA("Urza"), // Unstable
	VENSER("Venser"),
	VIVIEN("Vivien"),
	VRASKA("Vraska"),
	WILL("Will"),
	WINDGRACE("Windgrace"),
	XENAGOS("Xenagos"),
	YANGGU("Yanggu"),
	YANLING("Yanling"),
	YODA("Yoda"),  // Star Wars
	SNOKE("Snoke"), // Star Wars
	LUKE("Luke"), // Star Wars
	JAYA("Jaya");
	
	String desc;
	PlanesWalkerSubType(String desc){
		this.desc = desc;
	}
	
	@Override
	public String getDesc(){
		return desc;
	}
}
