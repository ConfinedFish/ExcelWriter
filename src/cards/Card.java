package cards;

import cards.type.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Card{
	private Boolean legendary;
	private String name;
	private String ability;
	private String manaCost;
	private String artist;
	private String flavorText;
	private String power;
	private String toughness;
	private String number;
	private String loyalty;
	private String convertedManaCost;
	private String typeString;
	private Rarity rarity;
	private ArrayList<Color> generatedMana;
	private ArrayList<SubType> subTypes;
	private ArrayList<SuperType> superTypes;
	private ArrayList<Format> legalities;
	private ArrayList<Color> colorIdentity;
	private ArrayList<Color> color;
	private CardSet set;
	
	public Card(){
	}
	
	public String getType(){
		return typeString;
	}
	
	public void setType(String typeString){
		this.typeString = typeString;
	}
	
	public Boolean getLegendary(){
		return legendary;
	}
	
	public void setLegendary(Boolean legendary){
		this.legendary = legendary;
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
	
	public String getArtist(){
		return artist;
	}
	
	public void setArtist(String artist){
		this.artist = artist;
	}
	
	public String getFlavorText(){
		return flavorText;
	}
	
	public void setFlavorText(String flavorText){
		this.flavorText = flavorText;
	}
	
	public String getPower(){
		return power;
	}
	
	public void setPower(String power){
		this.power = power;
	}
	
	public String getToughness(){
		return toughness;
	}
	
	public void setToughness(String toughness){
		this.toughness = toughness;
	}
	
	public String getNumber(){
		return number;
	}
	
	public void setNumber(String number){
		this.number = number;
	}
	
	public String getLoyalty(){
		return loyalty;
	}
	
	public void setLoyalty(String loyalty){
		this.loyalty = loyalty;
	}
	
	public String getConvertedManaCost(){
		return convertedManaCost;
	}
	
	public void setConvertedManaCost(String convertedManaCost){
		this.convertedManaCost = convertedManaCost;
	}
	
	public Rarity getRarity(){
		return rarity;
	}
	
	public void setRarity(Rarity rarity){
		this.rarity = rarity;
	}
	
	public ArrayList<Color> getGeneratedMana(){
		return generatedMana;
	}
	
	public void setGeneratedMana(ArrayList<Color> generatedMana){
		this.generatedMana = generatedMana;
	}
	
	public ArrayList<SubType> getSubTypes(){
		return subTypes;
	}
	
	public void setSubTypes(ArrayList<SubType> subTypes){
		this.subTypes = subTypes;
	}
	
	public ArrayList<SuperType> getSuperTypes(){
		return superTypes;
	}
	
	public void setSuperTypes(ArrayList<SuperType> superTypes){
		this.superTypes = superTypes;
	}
	
	public ArrayList<Format> getLegalities(){
		return legalities;
	}
	
	public void setLegalities(ArrayList<Format> legalities){
		this.legalities = legalities;
	}
	
	public ArrayList<Color> getColorIdentity(){
		return colorIdentity;
	}
	
	public void setColorIdentity(ArrayList<Color> colorIdentity){
		this.colorIdentity = colorIdentity;
	}
	
	public ArrayList<Color> getColor(){
		return color;
	}
	
	public void setColor(ArrayList<Color> color){
		this.color = color;
	}
	
	public CardSet getSet(){
		return set;
	}
	
	public void setSet(CardSet set){
		this.set = set;
	}
	//TODO make this better
	public String toString(){
		StringBuilder builder = new StringBuilder();
		ArrayList<String> colnames = new ArrayList<>();
		Method[] meths = Card.class.getDeclaredMethods();
		for (Method meth : meths)
			colnames.add(meth.getName());
		ArrayList<Method> methods = new ArrayList<>();
		ArrayList<Object> values = new ArrayList<>();
		for (String name : colnames){
			try{
				if (name.startsWith("get") || name.startsWith("is"))
					methods.add(Card.class.getMethod(name));
			} catch (NoSuchMethodException e){
				e.printStackTrace();
			}
		}
		builder.append("\n");
		for (Method meth : methods){
			try{
				builder.append("\t").append(meth.getName()).append(" : ")
						.append(meth.invoke(this).toString() == null ? "null" : meth.invoke(this).toString() + "\n");
			} catch (IllegalAccessException | NullPointerException | InvocationTargetException e){
//				e.printStackTrace();
			}
		}
		return builder.toString();
	}
}
