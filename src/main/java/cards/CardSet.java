package cards;

import java.util.ArrayList;

public class CardSet{
	private ArrayList<Card> cards;
	private String code;
	private String name;
	private String date;
	private boolean is_promo;
	
	public CardSet(){
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	public void setCards(ArrayList<Card> cards){
		this.cards = cards;
	}
	
	public String getCode(){
		return code;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public boolean is_promo(){
		return is_promo;
	}
	
	public void setIs_promo(boolean is_promo){
		this.is_promo = is_promo;
	}
}
