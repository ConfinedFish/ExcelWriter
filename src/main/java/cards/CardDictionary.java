package cards;


import cards.type.Color;
import deckeditor.DeckEditor;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class CardDictionary implements Iterable<Card> {
	private ArrayList<Card> dictionary;
	private ArrayList<CardSet> cardSets;
	private ArrayList<Card> tokens;
	private ArrayList<Card> lands;

	public CardDictionary() {
		dictionary = new ArrayList<>();
		cardSets = new ArrayList<>();
		tokens = new ArrayList<>();
		lands = new ArrayList<>();
	}

	public void add(Card card) {
		dictionary.add(card);
	}
	
	public Card findCard(String cardName){
		return dictionary.stream().filter(card -> cardName.equalsIgnoreCase(card.getName())).findFirst().orElse(null);
	}
	
	public ArrayList<Card> findCards(String string){
		ArrayList<Card> cards = getDictionary();
		cards.removeIf(card -> !card.getName().toLowerCase().contains(string.toLowerCase()));
		cards.sort(new CardComparator());
		return new ArrayList<>(cards);
	}
	public ArrayList<Card> findCards(ArrayList<Color> colors, boolean matchExact, boolean matchMultiColor, boolean excludeColor){
		ArrayList<Card> cards = getDictionary();
		if (matchExact){
			for (Color color : colors){
				cards.removeIf(card -> !card.getColor().contains(color));
			}
		}
		return new ArrayList<>(cards);
	}
	
	public int findCard(Card card) {
		return Collections.binarySearch(getDictionary(), card, new CardComparator());
	}

	public void sort() {
		getDictionary().sort(new CardComparator());
	}

	@SafeVarargs
	public final ArrayList<Card> combineLists(ArrayList<Card>... arrayLists) {
		ArrayList<Card> accumulator = new ArrayList<>(dictionary);

		for (ArrayList<Card> varList : arrayLists) {
			if (varList != null && !varList.isEmpty()) {
				accumulator.retainAll(varList);
			}
		}
		return accumulator;
	}

	public CardSet findSet(String code) {
		for (CardSet set : cardSets)
			if (set.getCode().equalsIgnoreCase(code))
				return set;
		return null;
	}

	Card get(int k) {
		return dictionary.get(k);
	}

	public ArrayList<Card> getDictionary() {
		return new ArrayList<>(dictionary);
	}

	@Override
	public Iterator<Card> iterator() {
		return new CardDictonaryIterator();
	}

	public int size() {
		return dictionary.size();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Card card : dictionary)
			builder.append(card.getName()).append("\n");
		return builder.toString();
	}

	public ArrayList<CardSet> getCardSets() {
		return cardSets;
	}

	public void setCardSets(ArrayList<CardSet> cardSets) {
		this.cardSets = cardSets;
	}

	public ArrayList<Card> getTokens() {
		return tokens;
	}

	public ArrayList<Card> getLands() {
		return lands;
	}

	class CardDictonaryIterator implements Iterator<Card> {
		int counter;

		CardDictonaryIterator() {
			counter = 0;
		}

		@Override
		public boolean hasNext() {
			return counter < dictionary.size();
		}

		@Override
		public Card next() {
			return dictionary.get(counter++);
		}
	}
}
