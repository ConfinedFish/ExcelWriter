package cards;

import cards.type.Color;
import cards.type.SubType;
import cards.type.SuperType;

import java.util.ArrayList;

public class Card {
    private String name, desc, manaCost;
    private ArrayList<SuperType> superType;
    private ArrayList<SubType> subtype;
    private ArrayList<Color> colorIdentity;
    private Integer power, toughness;
    private Double cmc;
	public Card(String name, String desc, String manaCost, Double cmc, ArrayList<SuperType> superType, ArrayList<SubType> subtype, ArrayList<Color> colorIdentity, Integer power, Integer toughness) {
		this.name = name;
		this.desc = desc;
		this.manaCost = manaCost;
		this.superType = superType;
		this.subtype = subtype;
		this.colorIdentity = colorIdentity;
		this.power = power;
		this.toughness = toughness;
		this.cmc = cmc;
	}
	public String getDesc() { return desc; }
	public String getManaCost() { return manaCost; }
	public ArrayList<SuperType> getSuperType() { return superType; }
	public ArrayList<SubType> getSubtype() { return subtype; }
	public ArrayList<Color> getColorIdentity() { return colorIdentity; }
	public Integer getPower() { return power; }
	public void setPower(Integer power) { this.power = power; }
	public Integer getToughness() { return toughness; }
	public Double getCMC() { return cmc; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean equals(Card card){ return name.equals(card.getName());}
}
