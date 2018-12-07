package main.java.cards;

import java.util.ArrayList;

public class CardSet {
	String name;
	int totalSetSize;
	String code;
	String releaseDate;
	String type;
	String block;
	ArrayList<Card> cards;
	public CardSet() {	}
	
	public String getName() {
		return name;
	}
	public int getTotalSetSize() {
		return totalSetSize;
	}
	public String getCode() {
		return code;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public String getType() {
		return type;
	}
	public String getBlock() {
		return block;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTotalSetSize(int totalSetSize) {
		this.totalSetSize = totalSetSize;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
}
