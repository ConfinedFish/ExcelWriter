package cards;


import deckeditor.DeckEditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class CardDictonary implements Iterable<Card>{
	private ArrayList<Card> dictonary;
	
	class CardDictonaryIterator implements Iterator<Card>{
		int counter;
		
		CardDictonaryIterator(){
			counter = 0;
		}
		
		@Override
		public boolean hasNext(){
			return counter < dictonary.size();
		}
		
		@Override
		public Card next(){
			return dictonary.get(counter++);
		}
	}
	
	private final CardDictonary insance;
	
	public CardDictonary getInstance(){
		return insance;
	}
	
	private int size;
	
	public CardDictonary(){
		insance = this;
		dictonary = new ArrayList<>();
		size = 0;
	}
	
	public void add(Card card){
		size++;
		dictonary.add(card);
	}
	
	public boolean contains(Card c){
		return dictonary.contains(c);
	}
	
	int findCard(String cardName){
		for (int i = 0; i < dictonary.size(); i++)
			if (dictonary.get(i).getName().contains(cardName))
				return i;
		return -1;
	}
	
	public ArrayList<Card> findCards(String searchName){
		ArrayList<Card> cardsContainingText = new ArrayList<>();
		for (Card c : dictonary)
			if (c.getName().toLowerCase().contains(searchName.toLowerCase()))
				cardsContainingText.add(c);
		return cardsContainingText;
	}
	
	@SafeVarargs
	public final ArrayList<Card> findAll(ArrayList<Card>... arrayLists){
		ArrayList<Card> accumulator = new ArrayList<>(dictonary);
		
		for (ArrayList<Card> varList : arrayLists){
			if (varList != null && !varList.isEmpty()){
				accumulator.retainAll(varList);
			}
		}
		DeckEditor.println("Accumulator");
		accumulator.forEach((c) -> DeckEditor.print(c.getName() + ", "));
		return accumulator;
	}
	
	public Card get(int k){
		return dictonary.get(k);
	}
	
	public ArrayList<Card> getDictonary(){
		return dictonary;
	}
	
	@Override
	public Iterator<Card> iterator(){
		return new CardDictonaryIterator();
	}
	
	public void remove(Card card){
		size--;
		dictonary.remove(card);
	}
	
	public int removeDoup(){
		ArrayList<Card> listOfNonDoups = new ArrayList<>();
		ArrayList<String> names = new ArrayList<>();
		dictonary.forEach(e -> names.add(e.getName()));
		HashSet<String> set = new HashSet<>(names);
		names.clear();
		names.addAll(set);
		ArrayList<String> basicLands = new ArrayList<>(Arrays.asList("Forest", "Plains", "Swamp", "Island", "Mountain", "Wastes"));
		for (Card c : dictonary){
			if (basicLands.contains(c.getName())){
				names.remove(c.getName());
			}
			if (names.contains(c.getName())){
				listOfNonDoups.add(c);
				names.remove(c.getName());
			}
		}
		int listbefore = dictonary.size(), listafter = listOfNonDoups.size();
		dictonary = listOfNonDoups;
		return listbefore - listafter;
	}
	
	public int size(){
		return dictonary.size();
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for (Card card : dictonary)
			builder.append(card.getName()).append("\n");
		return builder.toString();
	}
}
