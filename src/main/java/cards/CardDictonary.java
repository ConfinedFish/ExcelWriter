package main.java.cards;

import java.util.ArrayList;
import java.util.Iterator;

public class CardDictonary implements Iterable<Card> {
	private ArrayList<Card> list;
	public ArrayList<Card> getList() {
		return list;
	}
	private int size;
	public int getDeckSize() {
		return size;
	}
	public CardDictonary() {
		list = new ArrayList<>();
		size = 0;
	}
	public int size() {
		return list.size();
	}
	public Card get(int k) {
		return list.get(k);
	}
	public void addCard(Card card) {
		size++;
		list.add(card);
	}
	public int findCard(Card card) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getName().equals(card.getName()))
				return i;
		return -1;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Card card : list)
			builder.append(card.getName()).append("\n");
		return builder.toString();
	}
	@Override
	public Iterator<Card> iterator() {
		return new CardDictonaryIterator();
	}
	public class CardDictonaryIterator implements Iterator<Card> {
		int counter;
		CardDictonaryIterator() {
			counter = 0;
		}
		@Override
		public boolean hasNext() {
			return counter < list.size();
		}
		@Override
		public Card next() {
			return list.get(counter++);
		}
	}
}
