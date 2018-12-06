package main.java.cards;

import java.util.ArrayList;
import java.util.Iterator;

public class Deck implements Iterable<ArrayList<Card>> {
	private ArrayList<ArrayList<Card>> list;
	private int deckSize;
	Deck() {
		list = new ArrayList<>();
		deckSize = 0;
	}
	public int getDeckSize() {
		return deckSize;
	}
	public int getSize() {
		return list.size();
	}
	public Card get(int k) {
		return list.get(k).get(0);
	}
	public void addCard(Card card) {
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(card);
		deckSize++;
		list.add(cards);
	}
	void addCards(Card card, int amount) {
		ArrayList<Card> cards = new ArrayList<>();
		for (int i = 0; i < amount; i++)
			cards.add(card);
		list.add(cards);
		deckSize += amount;
	}
	public int findCard(String name) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).get(0).getName().equals(name))
				return i;
		return -1;
	}
	public int findCard(Card card) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).get(0).equals(card))
				return i;
		return -1;
	}
	public void addCards(Deck deck) {
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (ArrayList<Card> cards : list)
			for (Card card : cards)
				builder.append(card.getName()).append("\n");
		return builder.toString();
	}
	@Override
	public Iterator<ArrayList<Card>> iterator() {
		return new DeckIterator();
	}
	public class DeckIterator implements Iterator<ArrayList<Card>> {
		int counter;
		DeckIterator() {
			counter = 0;
		}
		@Override
		public boolean hasNext() {
			return counter < list.size();
		}
		@Override
		public ArrayList<Card> next() {
			return list.get(counter++);
		}
	}
}
