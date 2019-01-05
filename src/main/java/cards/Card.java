package cards;

import cards.type.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Card {
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
	private Integer convertedManaCost;
	private String typeString;
	private Rarity rarity;
	private ArrayList<Color> generatedMana;
	private ArrayList<SubType> subTypes;
	private ArrayList<SuperType> superTypes;
	private ArrayList<Format> legalities;
	private ArrayList<Color> colorIdentity;
	private ArrayList<Color> color;
	private ArrayList<ArrayList<Color>> symbols;
	private CardSet set;

	public Card() {
	}

	public String getSymbols() {
		StringBuilder stringWithSymbols = new StringBuilder();
		for (ArrayList<Color> list : symbols)
			if (list.contains(Color.C))
				stringWithSymbols.append("[").append(list.size()).append("]");
			else if (!list.isEmpty())
				stringWithSymbols.append("[").append(list.get(0)).append("]");
		return stringWithSymbols.toString();
	}

	public void setSymbols() {
		symbols = new ArrayList<>();
		String tempManaCost = manaCost;
		Pattern pattern = Pattern.compile("\\{(.*?)}");
		Matcher matcher = pattern.matcher(tempManaCost);
		while (matcher.find()) {
			ArrayList<Color> mana;
			String match = matcher.group(1);
			mana = Color.findColorArray(match);
			symbols.add(mana);
		}
	}

	public String getType() {
		return typeString;
	}

	public void setType(String typeString) {
		legendary = typeString.contains("Legendary");
		this.typeString = typeString;
	}

	public Boolean getLegendary() {
		return legendary;
	}

	public void setLegendary(Boolean legendary) {
		this.legendary = legendary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getToughness() {
		return toughness;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}

	public Integer getConvertedManaCost() {
		return convertedManaCost;
	}

	public void setConvertedManaCost(Integer convertedManaCost) {
		this.convertedManaCost = convertedManaCost;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public ArrayList<Color> getGeneratedMana() {
		return generatedMana;
	}

	public void setGeneratedMana(ArrayList<Color> generatedMana) {
		this.generatedMana = generatedMana;
	}

	public ArrayList<SubType> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(ArrayList<SubType> subTypes) {
		this.subTypes = subTypes;
	}

	public ArrayList<SuperType> getSuperTypes() {
		return superTypes;
	}

	public void setSuperTypes(ArrayList<SuperType> superTypes) {
		this.superTypes = superTypes;
	}

	public ArrayList<Format> getLegalities() {
		return legalities;
	}

	public void setLegalities(ArrayList<Format> legalities) {
		this.legalities = legalities;
	}

	public ArrayList<Color> getColorIdentity() {
		return colorIdentity;
	}

	public void setColorIdentity(ArrayList<Color> colorIdentity) {
		this.colorIdentity = colorIdentity;
	}

	public ArrayList<Color> getColor() {
		return color;
	}

	public void setColor(ArrayList<Color> color) {
		this.color = color;
	}

	public CardSet getSet() {
		return set;
	}

	public void setSet(CardSet set) {
		this.set = set;
	}

	//TODO make this better
	public String toString() {
		StringBuilder builder = new StringBuilder();
		ArrayList<String> colnames = new ArrayList<>();
		Method[] meths = Card.class.getDeclaredMethods();
		for (Method meth : meths)
			colnames.add(meth.getName());
		ArrayList<Method> methods = new ArrayList<>();
		ArrayList<Object> values = new ArrayList<>();
		for (String name : colnames) {
			try {
				if (name.startsWith("get") || name.startsWith("is"))
					methods.add(Card.class.getMethod(name));
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		builder.append("\n");
		for (Method meth : methods) {
			try {
				builder.append("\t").append(meth.getName()).append(" : ")
						.append(meth.invoke(this).toString() == null ? "null" : meth.invoke(this).toString() + "\n");
			} catch (IllegalAccessException | NullPointerException | InvocationTargetException e) {
//				e.printStackTrace();
			}
		}
		return builder.toString();
	}


}
