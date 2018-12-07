package main.java.cards;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
	@Override
	public int compare(Card arg0, Card arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}
}
