package main.java.cards;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import main.java.cards.type.Format;
import main.java.deckeditor.DeckEditor;

public class CardDictonary implements Iterable<Card>{
	public class CardDictonaryIterator implements Iterator<Card>{
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
	private ArrayList<Card> list;
	private int size;
	public CardDictonary() {
		list = new ArrayList<>();
		size = 0;
	}
	public void add(Card card) {
		size++;
		list.add(card);
	}
	public boolean contains(Card c) {
		return list.contains(c);
	}
	public int findCard(String cardName) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getName().contains(cardName))
				return i;
		return -1;
	}
	public ArrayList<Card> findCards(String searchName) {
		ArrayList<Card> cardsContainingText = new ArrayList<>();
		for (Card c : list)
			if (c.getName().toLowerCase().contains(searchName.toLowerCase()))
				cardsContainingText.add(c);
		return cardsContainingText;
	}
	public ArrayList<Card> findCards(Format value) {
		ArrayList<Card> cardsContainingText = new ArrayList<>();
		for (Card c : list)
			for (Format f : c.getLegalities())
				if (f.equals(value))
					if (!cardsContainingText.contains(c))
						cardsContainingText.add(c);
		return cardsContainingText;
	}
	public ArrayList<Card> findCards(Enum<?> value) {
		ArrayList<Card> cardsContainingText = new ArrayList<>();
		Field[] fields = Card.class.getDeclaredFields();
		for (Field f : fields)
			f.getName();
		return cardsContainingText;
	}
	public ArrayList<Card> findAll(@SuppressWarnings("unchecked") ArrayList<Card>... arrayLists) {
		ArrayList<Card> accumulator = list;
//		accumulator.forEach((c) -> DeckEditor.println(c.getName()));
		for (ArrayList<Card> list : arrayLists) {
			accumulator.retainAll(list);
		}
		return accumulator;
	}
	public ArrayList<Card> findDoup() {
		ArrayList<Card> doups = new ArrayList<>();
		Set<Card> cardSet = new TreeSet<>(new CardComparator());
		for (Card c : list) {
			if (!cardSet.add(c))
				doups.add(c);
		}
		return doups;
	}
	public Card get(int k) {
		return list.get(k);
	}
	public int getDeckSize() {
		return size;
	}
	public ArrayList<Card> getList() {
		return list;
	}
	@Override
	public Iterator<Card> iterator() {
		return new CardDictonaryIterator();
	}
	public void remove(Card card) {
		size--;
		list.remove(card);
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
	public int size() {
		return list.size();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Card card : list)
			builder.append(card.getName()).append("\n");
		return builder.toString();
	}
}
