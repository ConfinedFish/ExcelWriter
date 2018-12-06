package main.java.cards;

import java.util.ArrayList;

public class CardSet {
	ArrayList<Card> cards;
	String name, code, releaseDate, type, block;
	int totalSetSize;
	public CardSet() {
	}
	public CardSet(ArrayList<Card> cards, String name, String code, String releaseDate, String type, String block, int totalSize) {
		super();
		this.cards = cards;
		this.name = name;
		this.code = code;
		this.releaseDate = releaseDate;
		this.type = type;
		this.block = block;
		this.totalSetSize = totalSize;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public int getTotalSize() {
		return totalSetSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSetSize = totalSize;
	}
}
