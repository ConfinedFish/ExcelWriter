package cards;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class CardDictonary implements Iterable<Card> {
	private final CardDictonary insance;
	private ArrayList<Card> dictonary;
	private ArrayList<CardSet> cardSets;

	public CardDictonary() {
		insance = this;
		dictonary = new ArrayList<>();
		cardSets = new ArrayList<>();
	}

	public CardDictonary getInstance() {
		return insance;
	}

	public void add(Card card) {
		dictonary.add(card);
	}

	public boolean contains(Card c) {
		return dictonary.stream().anyMatch(o -> o.getName().equals(c.getName()));
	}

	int findCard(String cardName) {
		for (int i = 0; i < dictonary.size(); i++)
			if (dictonary.get(i).getName().contains(cardName))
				return i;
		return -1;
	}

	public ArrayList<Card> findCards(String searchName) {
		ArrayList<Card> cardsContainingText = new ArrayList<>();
		for (Card c : dictonary)
			if (c.getName().toLowerCase().contains(searchName.toLowerCase()))
				cardsContainingText.add(c);
		return cardsContainingText;
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

	public void remove(Card card) {
		dictonary.remove(card);
	}

	//TODO find out why this isnt working
	public int removeDoup() {
		ArrayList<Card> listofDoups = getDictonary();
		HashSet<Card> set = new HashSet<>(listofDoups);
		listofDoups.clear();
		listofDoups.addAll(set);
//		ArrayList<String> basicLands =
//				new ArrayList<>(Arrays.asList("Forest", "Plains", "Swamp", "Island", "Mountain", "Wastes"));
//		for (Card c : dictonary) {
//			if (basicLands.contains(c.getName())) {
//				names.remove(c.getName());
//			}
//			if (names.contains(c.getName())) {
//				listOfNonDoups.add(c);
//				names.remove(c.getName());
//			}
//		}
		int listbefore = dictonary.size();
		dictonary = listofDoups;
		return listbefore - listofDoups.size();
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
