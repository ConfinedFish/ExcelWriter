package main.java.cards;

import java.util.ArrayList;

public class CardSet{
	String block;
	ArrayList<Card> cards;
	String code;
	String name;
	String releaseDate;
	int totalSetSize;
	String type;
	public CardSet() {
	}
	public String getBlock() {
		return block;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public int getTotalSetSize() {
		return totalSetSize;
	}
	public String getType() {
		return type;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public void setTotalSetSize(int totalSetSize) {
		this.totalSetSize = totalSetSize;
	}
	public void setType(String type) {
		this.type = type;
	}
}
