package test;

public class Legalities {
	//ADD FORMATS TO MAIN CLASS
	private boolean brawl;
	private boolean commander;
	private boolean frontier;
	private boolean future;
	private boolean legacy;
	private boolean modern;
	private boolean mtgo1v1;
	private boolean pauper;
	private boolean penny;
	private boolean standard;
	private boolean vintage;
	
	public Legalities(boolean brawl, boolean commander, boolean frontier, boolean future, boolean legacy,
					  boolean modern,
					  boolean mtgo1v1, boolean pauper, boolean penny, boolean standard, boolean vintage){
		this.brawl = brawl;
		this.commander = commander;
		this.frontier = frontier;
		this.future = future;
		this.legacy = legacy;
		this.modern = modern;
		this.mtgo1v1 = mtgo1v1;
		this.pauper = pauper;
		this.penny = penny;
		this.standard = standard;
		this.vintage = vintage;
	}
	
	public boolean Brawl(){
		return brawl;
	}
	
	public boolean Commander(){
		return commander;
	}
	
	public boolean Frontier(){
		return frontier;
	}
	
	public boolean Future(){
		return future;
	}
	
	public boolean Legacy(){
		return legacy;
	}
	
	public boolean Modern(){
		return modern;
	}
	
	public boolean Mtgo1v1(){
		return mtgo1v1;
	}
	
	public boolean Pauper(){
		return pauper;
	}
	
	public boolean Penny(){
		return penny;
	}
	
	public boolean Standard(){
		return standard;
	}
	
	public boolean Vintage(){
		return vintage;
	}
}
