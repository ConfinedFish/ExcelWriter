package main.java.cards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FilenameUtils;

import main.java.deckeditor.DeckEditor;
import main.java.json.Jason;

public class Deck implements Iterable<ArrayList<Card>>{
	public class DeckIterator implements Iterator<ArrayList<Card>>{
		int counter;
		DeckIterator() {
			counter = 0;
		}
		@Override
		public boolean hasNext() {
			return counter < list.size();
		}
		@Override
		public ArrayList<Card> next() {
			return list.get(counter++);
		}
	}
	public static Deck loadDeckFromFile(File file) throws InvalidFormatException, FileNotFoundException {
		Deck deck = new Deck();
		if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("dec")) {
			throw new InvalidFormatException();
		}
		Scanner scanner = new Scanner(file);
		scanner.useDelimiter(" ");
		int numCard = 0;
		String cardName = "";
		while (scanner.hasNext()) {
			if (scanner.hasNextInt()) {
				numCard = scanner.nextInt();
			} else {
				cardName = scanner.nextLine().replaceFirst(" ", "");
			}
			int loc = Jason.dictonary.findCard(cardName);
			if (loc == -1) {
				DeckEditor.println("Card not found " + cardName);
			} else {
				deck.addCards(Jason.dictonary.get(Jason.dictonary.findCard(cardName)), numCard);
			}
		}
		scanner.close();
		return deck;
	}
	private int deckSize;
	private ArrayList<ArrayList<Card>> list;
	public Deck() {
		list = new ArrayList<>();
		deckSize = 0;
	}
	public void addCard(Card card) {
		ArrayList<Card> cards = new ArrayList<>();
		cards.add(card);
		deckSize++;
		list.add(cards);
	}
	void addCards(Card card, int amount) {
		ArrayList<Card> cards = new ArrayList<>();
		for (int i = 0; i < amount; i++)
			cards.add(card);
		list.add(cards);
		deckSize += amount;
	}
	public void addCards(Deck deck) {
	}
	public int findCard(Card card) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).get(0).equals(card))
				return i;
		return -1;
	}
	public int findCard(String name) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).get(0).getName().equals(name))
				return i;
		return -1;
	}
	public Card get(int k) {
		return list.get(k).get(0);
	}
	public int getDeckSize() {
		return deckSize;
	}
	public int getSize() {
		return list.size();
	}
	@Override
	public Iterator<ArrayList<Card>> iterator() {
		return new DeckIterator();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (ArrayList<Card> cards : list)
			for (Card card : cards)
				builder.append(card.getName()).append("\n");
		return builder.toString();
	}
}
