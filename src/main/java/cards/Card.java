package main.java.cards;

import java.util.ArrayList;

import main.java.cards.type.Color;
import main.java.cards.type.Format;
import main.java.cards.type.Rarity;
import main.java.cards.type.SubType;
import main.java.cards.type.SuperType;

public class Card{
	private ArrayList<Color> colorIdentity;
	private Double convertedManaCost;
	private Boolean isReserved;
	private ArrayList<Format> legalities;
	private String name, originalText, manaCost, printings, artist, borderColor, UUID;
	private String power, toughness;
	private Rarity rarity;
	private ArrayList<SubType> subtypes;
	private ArrayList<SuperType> supertypes;
	public Card() {
	}
	public String getArtist() {
		return artist;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public ArrayList<Color> getColorIdentity() {
		return colorIdentity;
	}
	public Double getConvertedManaCost() {
		return convertedManaCost;
	}
	public Boolean getIsReserved() {
		return isReserved;
	}
	public ArrayList<Format> getLegalities() {
		return legalities;
	}
	public String getManaCost() {
		return manaCost;
	}
	public String getName() {
		return name;
	}
	public String getOriginalText() {
		return originalText;
	}
	public String getPower() {
		return power;
	}
	public String getPrintings() {
		return printings;
	}
	public Rarity getRarity() {
		return rarity;
	}
	public ArrayList<SubType> getSubtypes() {
		return subtypes;
	}
	public ArrayList<SuperType> getSupertypes() {
		return supertypes;
	}
	public String getToughness() {
		return toughness;
	}
	public String getUUID() {
		return UUID;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public void setColorIdentity(ArrayList<Color> colorIdentity) {
		this.colorIdentity = colorIdentity;
	}
	public void setConvertedManaCost(Double convertedManaCost) {
		this.convertedManaCost = convertedManaCost;
	}
	public void setIsReserved(Boolean isReserved) {
		this.isReserved = isReserved;
	}
	public void setLegalities(ArrayList<Format> legalities) {
		this.legalities = legalities;
	}
	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public void setPrintings(String printings) {
		this.printings = printings;
	}
	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}
	public void setSubtypes(ArrayList<SubType> subtypes) {
		this.subtypes = subtypes;
	}
	public void setSupertypes(ArrayList<SuperType> supertypes) {
		this.supertypes = supertypes;
	}
	public void setToughness(String toughness) {
		this.toughness = toughness;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
}
