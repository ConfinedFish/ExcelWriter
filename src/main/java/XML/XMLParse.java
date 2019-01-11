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

	public XMLParse(String pathtoFile) {
		this.pathToFile = pathtoFile;
	}

	private static String getTagValue(String tag, Element element) {
		String returnval = null;
		try {
			returnval = element.hasChildNodes() ?
					element.getElementsByTagName(tag).item(0).getFirstChild().getTextContent() : null;
		} catch (NullPointerException ignored) {
		}
		return returnval;
	}

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
		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			DeckEditor.printException(pathToFile, e);
		}
		sortCards(setArrayList, dictonary.getDictionary());
		dictonary.setCardSets(setArrayList);
		dictonary.sort();
		DeckEditor.println("Finished loading from XML", Level.INFO);
	}

	private void sortCards(ArrayList<CardSet> sets, ArrayList<Card> cards) {
		DeckEditor.println("Sorting Cards into sets", Level.INFO);
		for (CardSet set : sets) {
			if (set.getCards() == null) {
				set.setCards(new ArrayList<>());
			}
			for (Card card : cards) {
				if (card.getSubTypes() != null && card.getSuperTypes() != null)
					if (!card.getType().contains("Basic Land"))
						if (card.getSet().getCode().equals(set.getCode()))
							set.getCards().add(card);
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
				card.setRarity(Rarity.findRarity(getTagValue("rarity", element)));
				String[] types = getTagValue("type", element) == null ? new String[0] :
						getTagValue("type", element).split(" — ");
				card.setType(getTagValue("type", element));
				card.setSuperTypes(SuperType.parseString(types[0]));
				card.setSubTypes(types.length == 1 ? new ArrayList<>() : SubType.parseString(types[1]));
				card.setPower(getTagValue("power", element));
				card.setToughness(getTagValue("toughness", element));
				card.setLoyalty(getTagValue("loyalty", element));
				String cmc = getTagValue("converted_manacost", element);
				Integer integer = null;
				if (NumberUtils.isCreatable(cmc))
					try {
						integer = Integer.parseInt(cmc);
					} catch (Exception ignored) {
					}
				card.setConvertedManaCost(integer);
				card.setManaCost(getTagValue("manacost", element));
				card.setColorIdentity(Color.findColorArray(getTagValue("color_identity", element)));
				card.setColor(Color.findColorArray(getTagValue("color", element)));
				card.setArtist(getTagValue("artist", element));
				card.setNumber(getTagValue("number", element));
				card.setAbility(getTagValue("ability", element));
				card.setFlavorText(getTagValue("flavor", element));
				card.setGeneratedMana(Color.findColorArray(getTagValue("manacost", element)));
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
		return dictonary.getDictionary();
	}

	public CardDictionary getDictonary() {
		return dictonary;
	}

}
