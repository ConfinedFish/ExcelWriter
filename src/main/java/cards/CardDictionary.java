package cards;


import cards.type.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

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
	
	public Card findCard(String cardName, CardSet set) {
		return dictionary.stream().filter(card -> cardName.equalsIgnoreCase(card.getName()) && card.getSet().equals(set)).findFirst().orElse(null);
	}
	
	public ArrayList<Card> findCards(String string) {
		ArrayList<Card> cards = getList();
		cards.removeIf(card -> !card.getName().toLowerCase().contains(string.toLowerCase()));
		cards.sort(new CardComparator());
		return new ArrayList<>(cards);
	}
	
	public int findCard(Card card) {
		return Collections.binarySearch(getList(), card, new CardComparator());
	}
	
	public void sort() {
		getList().sort(new CardComparator());
	}
	public ArrayList<Card> findCards(ArrayList<Color> colors, boolean matchExact, boolean matchMultiColor, boolean excludeColor) {
		Collection<Card> copyOfDictionary = getList();
		ArrayList<Card> remove = new ArrayList<>();
		Iterator<Card> iterator = copyOfDictionary.iterator();
		//match exact case
		if (matchExact) {
			while (iterator.hasNext()) {
				Card c = iterator.next();
				ArrayList<ArrayList<Color>> symbols = c.getSymbolList();
				//remove cards that dont have the symbols in them
				if (symbols != null && !symbols.isEmpty() && c.getColor() != null && !c.getColor().contains(Color.C)) {
					for (ArrayList<Color> colorList : symbols) {
						for (Color color : colorList) {
							if (!colors.contains(color)) {
								if (!color.equals(Color.C)) {
									if (!remove.contains(c)) {
										remove.add(c);
									}
								}
							}
						}
					}
					//remove cards that are not of color identity
					for (Color color : c.getColor())
						if (!colors.contains(color))
							if (!remove.contains(c))
								remove.add(c);
				} else {
					remove.add(c);
				}
				
			}
			//multicolor case
		} else if (matchMultiColor) {
			while (iterator.hasNext()) {
				Card c = iterator.next();
				ArrayList<ArrayList<Color>> symbols = c.getSymbolList();
				if (symbols != null && !symbols.isEmpty() && c.getColor() != null && !c.getColor().contains(Color.C)) {
					for (ArrayList<Color> colorList : symbols) {
						for (Color color : colorList) {
							if (!colors.contains(color)) {
								if (!color.equals(Color.C)) {
									if (!remove.contains(c)) {
										remove.add(c);
									}
								}
							}
						}
					}
					for (Color color : c.getColor())
						if (!colors.contains(color))
							if (!remove.contains(c))
								remove.add(c);
				} else {
					remove.add(c);
				}
				
			}
			//handle no check boxes checked
		} else if (excludeColor) {
			while (iterator.hasNext()) {
				Card c = iterator.next();
				ArrayList<ArrayList<Color>> symbols = c.getSymbolList();
				if (symbols != null && !symbols.isEmpty() && c.getColor() != null && !c.getColor().contains(Color.C)) {
					for (ArrayList<Color> colorList : symbols) {
						for (Color color : colorList) {
							if (!colors.contains(color)) {
								if (!color.equals(Color.C)) {
									if (!remove.contains(c)) {
										remove.add(c);
									}
								}
							}
						}
					}
					for (Color color : c.getColor())
						if(!colors.contains(color))
							if(!remove.contains(c))
								remove.add(c);
				} else {
					remove.add(c);
				}
				
			}
		} else {
			while (iterator.hasNext()) {
				Card c = iterator.next();
				ArrayList<ArrayList<Color>> symbols = c.getSymbolList();
				if (symbols != null && !symbols.isEmpty() && c.getColor() != null && !c.getColor().contains(Color.C)) {
					for (ArrayList<Color> colorList : symbols) {
						for (Color color : colorList) {
							if (!colors.contains(color)) {
								if (!color.equals(Color.C)) {
									if (!remove.contains(c)) {
										remove.add(c);
									}
								}
							}
						}
					}
					for (Color color : c.getColor())
						if (!colors.contains(color))
							if (!remove.contains(c))
								remove.add(c);
				} else {
					remove.add(c);
				}
				
			}
		}
		copyOfDictionary.removeAll(remove);
		return new ArrayList<>(copyOfDictionary);
	}
	
	public ArrayList<Card> findAll(Predicate<Card> predicate){
		ArrayList<Card> accumulator = new ArrayList<>();
		for (Card card : dictionary){
			if(predicate.test(card)) {
				accumulator.add(card);
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
	
	public ArrayList<Card> getList() {
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
