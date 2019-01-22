package test;

import cards.CardSet;
import cards.type.Rarity;

public interface Card {
	boolean isLegendary();
	String getName();
	Ability getAbility();
	Integer getConvertedManaCost();
	CardType getCardType();
	Rarity getRarity();
	Legalities getLegalities();
	CardSet getSet();
	Symbols getSymbols();
	String toString();
}
