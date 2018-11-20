package cards;

import cards.type.Color;
import cards.type.Type;

public class Card {
    private String name, desc, manaCost;
    private Type type, subtype;
    private Color colorIdentity;
    private int power, toughness;
    private int cmc;
	public Card(String name, String desc, String manaCost, int cmc, Type type, Type subtype, Color colorIdentity, int power, int toughness) {
		this.name = name;
		this.desc = desc;
		this.manaCost = manaCost;
		this.type = type;
		this.subtype = subtype;
		this.colorIdentity = colorIdentity;
		this.power = power;
		this.toughness = toughness;
		this.cmc = cmc;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getManaCost() {
		return manaCost;
	}
	
	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getSubtype() {
		return subtype;
	}
	
	public void setSubtype(Type subtype) {
		this.subtype = subtype;
	}
	
	public Color getColorIdentity() {
		return colorIdentity;
	}
	
	public void setColorIdentity(Color colorIdentity) {
		this.colorIdentity = colorIdentity;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public int getToughness() {
		return toughness;
	}
	
	public void setToughness(int toughness) {
		this.toughness = toughness;
	}
	
	public int getCmc() {
		return cmc;
	}
	
	public void setCmc(int cmc) {
		this.cmc = cmc;
	}
    public String getName(){
        return name;
    }
    public void setName(String name) {
		this.name = name;
	}
    public boolean equals(Card card){
        return name.equals(card.getName());
    }
}
