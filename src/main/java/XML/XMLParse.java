package XML;

import cards.Card;
import cards.CardDictionary;
import cards.CardSet;
import cards.type.Color;
import cards.type.Rarity;
import cards.type.SubType;
import cards.type.SuperType;
import deckeditor.DeckEditor;
import deckeditor.Level;
import org.apache.commons.lang3.math.NumberUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this class is the main class where the data is imported. this file uses the extensible markup
 * language (.xml) format. the data comes from http://gatherer.wizards.com.
 * <p>
 * the xml will be scanned for specific values needed for each card using the following names:
 * - name
 * - set
 * - ability
 * - rarity
 * - type
 * - subtype
 * - power
 * - toughness
 * - loyalty
 * - converted_manacost
 * - manacost
 * - color_identity
 * - color
 * - artist
 * - flavor
 * - manacost
 * - legality_Block
 * - legality_Standard
 * - legality_Modern
 * - legality_Legacy
 * - legality_Vintage
 * - legality_Highlander
 * - legality_French_Commander
 * - legality_Tiny_Leaders_Commander
 * - legality_Leviathan_Commander
 * - legality_Commander
 * - legality_Peasant
 * - legality_Pauper
 */
public class XMLParse {
	//TODO add a progress counter to the loading
	private String pathToFile;
	private ArrayList<CardSet> setArrayList = new ArrayList<>();
	private CardDictionary dictonary = new CardDictionary();
	
	/**
	 * Constructor for the XMLParse object.
	 * @param pathToFile the path to the XML File containing the database of the cards.
	 */
	public XMLParse(String pathToFile) {
		this.pathToFile = pathToFile;
	}
	
	/**
	 * This method finds an element based on a tag. It takes an element, and uses that to find it's children and looks
	 * for the tag.
	 *
	 * Typically this is called when the element is "card". From there it grabs the children (the data for each card)
	 * and returns the string contained in that element
	 *
	 * @param tag A string containing the string to look for
	 * @param element the element containing the children.
	 * @return a string that contains whatever text was found or null if it was not found.
	 */
	private static String getTagValue(String tag, Element element) {
		String val = null;
		try {
			val = element.hasChildNodes() ? element.getElementsByTagName(tag).item(0).getFirstChild().getTextContent() : null;
		} catch (NullPointerException ignored) {
		}
		return val;
	}
	
	/**
	 * This where the file is read. It handles the opening and reading the file. XML is the file of choice here because
	 * Java contains built in tools to read XML files. No external libraries were needed to read from it.
	 */
	public void parse() {
		DeckEditor.println("Started loading from XML: " + pathToFile, Level.INFO);
		File xmlFile = new File(pathToFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbFactory.newDocumentBuilder();
			Document document = db.parse(xmlFile);
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xpath = xpf.newXPath();
			NodeList setNodeList =
					(NodeList) xpath.evaluate("mtg_carddatabase/sets/set", document, XPathConstants.NODESET);
			
			for (int i = 0; i < setNodeList.getLength(); i++) {
				setArrayList.add(getSetData(setNodeList.item(i)));
			}
			DeckEditor.println("Loaded " + setArrayList.size() + " sets from XML", Level.INFO);
			NodeList cardNodeList =
					(NodeList) xpath.evaluate("mtg_carddatabase/cards/card", document, XPathConstants.NODESET);
			for (int i = 0; i < cardNodeList.getLength(); i++) {
				Card card = getCardData(cardNodeList.item(i));
				if (card.getType().contains("Basic Land"))
					dictonary.getLands().add(card);
				else if (card.getName().contains("Token") || card.getType().contains("Token") || card.getType().contains("Emblem"))
					dictonary.getTokens().add(card);
				else
					dictonary.add(card);
			}
			DeckEditor.println("Loaded " + dictonary.size() + " cards from XML", Level.INFO);
			DeckEditor.println("Loaded " + dictonary.getTokens().size() + " tokens from XML", Level.INFO);
			DeckEditor.println("Loaded " + dictonary.getLands().size() + " lands from XML", Level.INFO);
		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			DeckEditor.printException(pathToFile, e);
		}
		sortCards(setArrayList, dictonary.getList());
		dictonary.setCardSets(setArrayList);
		dictonary.sort();

		DeckEditor.println("Finished loading from XML", Level.INFO);
	}
	
	/**
	 * This method is called when the cards are all loaded. In order to view cards by sets, they are organized
	 * @param sets the list of sets the cards will be organized into
	 * @param cards the list of total cards loaded.
	 */
	private void sortCards(ArrayList<CardSet> sets, ArrayList<Card> cards) {
		DeckEditor.println("Sorting Cards into sets", Level.INFO);
		for (CardSet set : sets) {
			if (set.getCards() == null) {
				set.setCards(new ArrayList<>());
			}
			for (Card card : cards) {
				if (card.getSubTypes() != null && card.getSuperTypes() != null)
					if (!card.getType().contains("Basic Land")) {
						try {
							if (card.getSet().getCode().equals(set.getCode()))
								set.getCards().add(card);
						} catch (Exception e) {
							DeckEditor.printException(set.getName(), e);
						}
					}
			}
		}
	}
	
	private Card getCardData(Node node) {
		Card card = new Card();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			try {
				//TODO orginize this to look pretty
				Element element = (Element) node;
				card.setName(getTagValue("name", element));
				card.setSet(findSet(getTagValue("set", element)));
				if (card.getSet() == null)
					DeckEditor.println("Unknown Set: " + getTagValue("set", element));
				card.setRarity(Rarity.findRarity(getTagValue("rarity", element)));
				String[] types = getTagValue("type", element) == null ? new String[0] :
						getTagValue("type", element).split(" — ");
				card.setType(getTagValue("type", element));
				card.setSuperTypes(SuperType.parseString(types[0]));
				card.setSubTypes(types.length == 1 ? new ArrayList<>() : SubType.parseString(types[1]));
				String power = getTagValue("power", element);
				Integer pwr = null;
				if (NumberUtils.isCreatable(power))
					try {
						pwr = Integer.parseInt(power);
					} catch (Exception ignored) {
					}
				card.setPower(pwr);
				String toughness = getTagValue("toughness", element);
				Integer tough = null;
				if (NumberUtils.isCreatable(toughness))
					try {
						tough = Integer.parseInt(toughness);
					} catch (Exception ignored) {
					}
				card.setToughness(tough);
				String loyalty = getTagValue("loyalty", element);
				Integer loyal = null;
				if (NumberUtils.isCreatable(loyalty))
					try {
						loyal = Integer.parseInt(loyalty);
					} catch (Exception ignored) {
					}
				card.setLoyalty(loyal);
				String cmc = getTagValue("converted_manacost", element);
				Integer convertedManaCost = null;
				if (NumberUtils.isCreatable(cmc))
					try {
						convertedManaCost = Integer.parseInt(cmc);
					} catch (Exception ignored) {
					}
				card.setConvertedManaCost(convertedManaCost);
				card.setManaCost(getTagValue("manacost", element));
				String color = getTagValue("color_identity", element);
				if (color == null){
					color = Color.C.toString();
				}
				card.setColor(Color.findColorArray(color));
				card.setArtist(getTagValue("artist", element));
				card.setAbility(getTagValue("ability", element));
				card.setFlavorText(getTagValue("flavor", element));
				card.setGeneratedMana(Color.findColorArray(getTagValue("generated_mana", element)));
				card.setSymbols();
			} catch (IllegalArgumentException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				DeckEditor.printException(card.getName(), e);
			}
		}
		return card;
	}
	
	private CardSet findSet(String code) {
		for (CardSet set : setArrayList)
			if (set.getCode().equalsIgnoreCase(code))
				return set;
		return null;
	}
	
	private CardSet getSetData(Node node) {
		CardSet set = new CardSet();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			set.setName(getTagValue("name", element));
			set.setCode(getTagValue("code", element));
			set.setDate(getTagValue("date", element));
			set.setIs_promo(Boolean.parseBoolean(getTagValue("is_promo", element)));
		}
		return set;
	}
	
	
	public ArrayList<CardSet> getSetArrayList() {
		return setArrayList;
	}
	
	public ArrayList<Card> getCardArrayList() {
		return dictonary.getList();
	}
	
	public CardDictionary getDictonary() {
		return dictonary;
	}
	
}
