package test.Types;

import cards.CardSet;
import cards.type.Rarity;
import test.*;

public class Creature implements Card{
	private boolean legendary;
	private String name;
	private Ability ability;
	private Integer convertedManaCost;
	private CardType cardType;
	private Rarity rarity;
	private CardSet set;
	private Symbols symbols;
	private Legalities legalities;
	
	public Creature(boolean legendary, String name, Ability ability, Integer convertedManaCost, CardType cardType,
					Rarity rarity, CardSet set, Symbols symbols, Legalities legalities){
		this.legendary = legendary;
		this.name = name;
		this.ability = ability;
		this.convertedManaCost = convertedManaCost;
		this.cardType = cardType;
		this.rarity = rarity;
		this.set = set;
		this.symbols = symbols;
		this.legalities = legalities;
	}
	
	@Override
	public boolean isLegendary(){
		return legendary;
	}
	
	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public Ability getAbility(){
		return ability;
	}
	
	@Override
	public Integer getConvertedManaCost(){
		return convertedManaCost;
	}
	
	@Override
	public CardType getCardType(){
		return cardType;
	}
	
	@Override
	public Rarity getRarity(){
		return rarity;
	}
	
	@Override
	public CardSet getSet(){
		return set;
	}
	
	@Override
	public Symbols getSymbols(){
		return symbols;
	}
	
	@Override
	public Legalities getLegalities(){
		return legalities;
	}
}
