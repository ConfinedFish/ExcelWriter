package cards;

import java.util.ArrayList;

public class CardSet{
	private ArrayList<Card> cards;
	private String code;
	private String name;
	private String releaseDate;
	private boolean isPromo;
	
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
	
	public String getReleaseDate(){
		return releaseDate;
	}
	
	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}
	
	public boolean isPromo(){
		return isPromo;
	}
	
	public void setPromo(boolean promo){
		isPromo = promo;
	}
}
