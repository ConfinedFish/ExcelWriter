package main.java.cards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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
	public void add(Card card) {
		size++;
		list.add(card);
	}
	public void remove(Card card) {
		size--;
		list.remove(card);
	}
	public int findCard(String cardName) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getName().equals(cardName))
				return i;
		return -1;
	}
	public ArrayList<Card> findDoup(){
		ArrayList<Card> doups = new ArrayList<>();
		Set<Card> cardSet = new TreeSet<Card>(new CardComparator());
		for (Card c : list) {
			if (!cardSet.add(c))
				doups.add(c);
		}
		return doups;
	}
	public int removeDoup() {
		ArrayList<Card> a = new ArrayList<>();
		HashSet<String> set = new HashSet<>();
		ArrayList<String> names = new ArrayList<>();
		for (Card c : list) {
			names.add(c.getName());
		}
		set.addAll(names);
		names.clear();
		names.addAll(set);
		for (Card c : list) {
			if (names.contains(c.getName())) {
				a.add(c);
				names.remove(c.getName());
			}
		}
		int listbefore = list.size(), listafter = a.size();
		list = a;
		return listbefore - listafter;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Card card : list)
			builder.append(card.getName()).append("\n");
		return builder.toString();
	}
	public boolean contains(Card c) {
		return list.contains(c);
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
