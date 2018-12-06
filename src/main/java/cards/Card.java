package main.java.cards;

import java.util.ArrayList;

import main.java.cards.type.Color;
import main.java.cards.type.Format;
import main.java.cards.type.SubType;
import main.java.cards.type.SuperType;

public class Card {
	private String name, originalText, manaCost, printings, rarity;
	private ArrayList<Format> legalities;
	private ArrayList<SuperType> types;
	private ArrayList<SubType> subtypes;
	private ArrayList<Color> colorIdentity;
	private Integer power, toughness;
	private Double convertedManaCost;
	public Card(String name, String desc, String manaCost, Double cmc, ArrayList<SuperType> superType,
			ArrayList<SubType> subtype, ArrayList<Color> colorIdentity, Integer power, Integer toughness) {
		this.name = name;
		this.originalText = desc;
		this.manaCost = manaCost;
		this.types = superType;
		this.subtypes = subtype;
		this.colorIdentity = colorIdentity;
		this.power = power;
		this.toughness = toughness;
		this.convertedManaCost = cmc;
	}
	public Card() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return originalText;
	}
	public void setDesc(String desc) {
		this.originalText = desc;
	}
	public String getManaCost() {
		return manaCost;
	}
	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}
	public ArrayList<SuperType> getSuperType() {
		return types;
	}
	public void setSuperType(ArrayList<SuperType> superType) {
		this.types = superType;
	}
	public ArrayList<SubType> getSubtype() {
		return subtypes;
	}
	public void setSubtype(ArrayList<SubType> subtype) {
		this.subtypes = subtype;
	}
	public ArrayList<Color> getColorIdentity() {
		return colorIdentity;
	}
	public void setColorIdentity(ArrayList<Color> colorIdentity) {
		this.colorIdentity = colorIdentity;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public Integer getToughness() {
		return toughness;
	}
	public void setToughness(Integer toughness) {
		this.toughness = toughness;
	}
	public Double getCMC() {
		return convertedManaCost;
	}
	public void setCmc(Double cmc) {
		this.convertedManaCost = cmc;
	}
	public ArrayList<Format> getLegalities() {
		return legalities;
	}
	public void setLegalities(ArrayList<Format> legalities) {
		this.legalities = legalities;
	}
	public String getPrintings() {
		return printings;
	}
	public void setPrintings(String printings) {
		this.printings = printings;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
}
