package main.java.cards;

import java.util.ArrayList;
import java.util.Iterator;

public class Library implements Iterable<Card>{
	private ArrayList<Card> list;
	private int deckSize;
	public Library(){
		list = new ArrayList<>();
		deckSize = 0;
	}
	public int getLibrarySize(){
		return list.size();
	}
	public Card get(int k){
		return list.get(k);
	}
	public void addCard(Card card){
		deckSize++;
		list.add(card);
	}
	public int findCard(Card card){
		for (int i =0; i < list.size(); i++)
			if (list.get(i).getName().equals(card.getName()))
				return i;
		return -1;
	}
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(Card card : list)
			builder.append(card.getName()).append("\n");
		return builder.toString();
	}
	@Override
	public Iterator<Card> iterator() {
		return new LibraryIterator();
	}
	public class LibraryIterator implements Iterator<Card>{
		int counter;
		LibraryIterator(){
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
