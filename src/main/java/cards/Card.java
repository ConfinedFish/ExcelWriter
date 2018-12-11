package main.java.cards;

import java.util.ArrayList;

import main.java.cards.type.Color;
import main.java.cards.type.Format;
import main.java.cards.type.SubType;
import main.java.cards.type.SuperType;

public class Card {
	private String name, originalText, manaCost, printings, rarity;
	private ArrayList<Format> legalities;
	private ArrayList<SuperType> supertypes;
	private ArrayList<SubType> subtypes;
	private ArrayList<Color> colorIdentity;
	private String power, toughness;
	private Double convertedManaCost;
	private String type;
	public Card() {	}
	public String getName() {
		return name;
	}
	public String getOriginalText() {
		return originalText;
	}
	public String getManaCost() {
		return manaCost;
	}
	public String getPrintings() {
		return printings;
	}
	public String getRarity() {
		return rarity;
	}
	public ArrayList<Format> getLegalities() {
		return legalities;
	}
	public ArrayList<SuperType> getSupertypes() {
		return supertypes;
	}
	public ArrayList<SubType> getSubtypes() {
		return subtypes;
	}
	public ArrayList<Color> getColorIdentity() {
		return colorIdentity;
	}
	public String getPower() {
		return power;
	}
	public String getToughness() {
		return toughness;
	}
	public Double getConvertedManaCost() {
		return convertedManaCost;
	}
	public String getType() {
		return type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}
	public void setPrintings(String printings) {
		this.printings = printings;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public void setLegalities(ArrayList<Format> legalities) {
		this.legalities = legalities;
	}
	public void setSupertypes(ArrayList<SuperType> supertypes) {
		this.supertypes = supertypes;
	}
	public void setSubtypes(ArrayList<SubType> subtypes) {
		this.subtypes = subtypes;
	}
	public void setColorIdentity(ArrayList<Color> colorIdentity) {
		this.colorIdentity = colorIdentity;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public void setToughness(String toughness) {
		this.toughness = toughness;
	}
	public void setConvertedManaCost(Double convertedManaCost) {
		this.convertedManaCost = convertedManaCost;
	}
	public void setType(String type) {
		this.type = type;
	}
	}
