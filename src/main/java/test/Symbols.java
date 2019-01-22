package test;

import cards.type.Color;

import java.util.HashMap;

public class Symbols {
	private ColorIdentity colorIdentity;
	private HashMap<Color, Integer> symbols;
	private String symbolString;
	
	public Symbols(ColorIdentity colorIdentity, HashMap<Color, Integer> symbols, String symbolString){
		this.colorIdentity = colorIdentity;
		this.symbols = symbols;
		this.symbolString = symbolString;
	}
	
	public ColorIdentity getColorIdentity(){
		return colorIdentity;
	}
	
	public HashMap<Color, Integer> getSymbols(){
		return symbols;
	}
	
	public String getSymbolString(){
		return symbolString;
	}
}
