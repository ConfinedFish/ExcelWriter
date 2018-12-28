package cards;

import cards.type.*;

import java.util.ArrayList;

public class Card{
	private Boolean isReserved;
	private String name;
	private String ability;
	private String manaCost;
	private String printings;
	private String artist;
	private String flavorText;
	private String generatedMana;
	private Integer power;
	private Integer toughness;
	private Integer loyelty;
	private Integer number;
	private Integer convertedManaCost;
	private Rarity rarity;
	private ArrayList<SubType> subtypes;
	private ArrayList<Format> legalities;
	private ArrayList<Color> colorIdentity;
	
	
	public ArrayList<Color> getColorIdentity(){
		return colorIdentity;
	}
	
	public void setColorIdentity(ArrayList<Color> colorIdentity){
		this.colorIdentity = colorIdentity;
	}
	
	public Boolean getReserved(){
		return isReserved;
	}
	
	public void setReserved(Boolean reserved){
		isReserved = reserved;
	}
	
	public ArrayList<Format> getLegalities(){
		return legalities;
	}
	
	public void setLegalities(ArrayList<Format> legalities){
		this.legalities = legalities;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getAbility(){
		return ability;
	}
	
	public void setAbility(String ability){
		this.ability = ability;
	}
	
	public String getManaCost(){
		return manaCost;
	}
	
	public void setManaCost(String manaCost){
		this.manaCost = manaCost;
	}
	
	public String getPrintings(){
		return printings;
	}
	
	public void setPrintings(String printings){
		this.printings = printings;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public void setArtist(String artist){
		this.artist = artist;
	}
	
	public Integer getPower(){
		return power;
	}
	
	public void setPower(Integer power){
		this.power = power;
	}
	
	public Integer getToughness(){
		return toughness;
	}
	
	public void setToughness(Integer toughness){
		this.toughness = toughness;
	}
	
	public Integer getLoyelty(){
		return loyelty;
	}
	
	public void setLoyelty(Integer loyelty){
		this.loyelty = loyelty;
	}
	
	public Integer getNumber(){
		return number;
	}
	
	public void setNumber(Integer number){
		this.number = number;
	}
	
	public Rarity getRarity(){
		return rarity;
	}
	
	public void setRarity(Rarity rarity){
		this.rarity = rarity;
	}
	
	public ArrayList<SubType> getSubtypes(){
		return subtypes;
	}
	
	public void setSubtypes(ArrayList<SubType> subtypes){
		this.subtypes = subtypes;
	}
	
	public ArrayList<SuperType> getSupertypes(){
		return supertypes;
	}
	
	public void setSupertypes(ArrayList<SuperType> supertypes){
		this.supertypes = supertypes;
	}
	
	public CardSet getSet(){
		return set;
	}
	
	public void setSet(CardSet set){
		this.set = set;
	}
	
	private ArrayList<SuperType> supertypes;
	
	private CardSet set;
	
	public Card(){
	}
	
	public String getGeneratedMana(){
		return generatedMana;
	}
	
	public void setGeneratedMana(String generatedMana){
		this.generatedMana = generatedMana;
	}
	
	public String getFlavorText(){
		return flavorText;
	}
	
	public void setFlavorText(String flavorText){
		this.flavorText = flavorText;
	}
	
	public Integer getConvertedManaCost(){
		return convertedManaCost;
	}
	
	public void setConvertedManaCost(Integer convertedManaCost){
		this.convertedManaCost = convertedManaCost;
	}
}
