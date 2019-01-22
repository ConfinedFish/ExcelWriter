package test;

import cards.type.SubType;
import cards.type.SuperType;

public class CardType {
	private SuperType superType;
	private SubType subType;
	private String type;
	
	public CardType(SuperType superType, SubType subType, String type){
		this.superType = superType;
		this.subType = subType;
		this.type = type;
	}
	
	public SuperType getSuperType(){
		return superType;
	}
	
	public SubType getSubType(){
		return subType;
	}
	
	public String getType(){
		return type;
	}
}
