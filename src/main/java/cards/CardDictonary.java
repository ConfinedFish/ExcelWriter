package cards;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class CardDictonary implements Iterable<Card> {
	private ArrayList<Card> dictonary;
	private ArrayList<CardSet> cardSets;
	private ArrayList<Card> tokens;
	private ArrayList<Card> lands;

	public CardDictonary() {
		dictonary = new ArrayList<>();
		cardSets = new ArrayList<>();
		tokens = new ArrayList<>();
		lands = new ArrayList<>();
	}

	public void add(Card card) {
		dictonary.add(card);
	}
	
	public Card findCard(String cardname){
		return dictonary.stream().filter(card -> cardname.equalsIgnoreCase(card.getName())).findFirst().orElse(null);
	}
	public int findCard(Card card) {
		return Collections.binarySearch(getDictonary(), card, new CardComparator());
	}

	public void sort() {
		getDictonary().sort(new CardComparator());
	}

	@SafeVarargs
	public final ArrayList<Card> findAll(ArrayList<Card>... arrayLists) {
		ArrayList<Card> accumulator = new ArrayList<>(dictonary);

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
		return dictonary.get(k);
	}

	//TODO make this method return a copy not the real thing
	public ArrayList<Card> getDictonary() {
		return dictonary;
	}

	@Override
	public Iterator<Card> iterator() {
		return new CardDictonaryIterator();
	}

	public int size() {
		return dictonary.size();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Card card : dictonary)
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
			return counter < dictonary.size();
		}

		@Override
		public Card next() {
			return dictonary.get(counter++);
		}
	}
}
