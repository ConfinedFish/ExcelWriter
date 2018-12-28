package XML;

import cards.Card;
import cards.CardSet;
import deckeditor.DeckEditor;
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

public class XMLParse{
	private String pathToFile;
	private ArrayList<CardSet> setArrayList = new ArrayList<>();
	private ArrayList<Card> cardArrayList = new ArrayList<>();
	
	public XMLParse(String pathtoFile){
		this.pathToFile = pathtoFile;
		parse();
	}
	
	private void parse(){
		File xmlFile = new File(pathToFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder db = dbFactory.newDocumentBuilder();
			Document document = db.parse(xmlFile);
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xpath = xpf.newXPath();
			NodeList setNodeList = (NodeList) xpath.evaluate("mtg_carddatabase/sets/set", document, XPathConstants.NODESET);
			
			for (int i = 0; i < setNodeList.getLength(); i++){
				setArrayList.add(getSetData(setNodeList.item(i)));
			}
			setArrayList.forEach(set -> DeckEditor.println(set.isPromo()));
			NodeList cardNodeList = (NodeList) xpath.evaluate("mtg_carddatabase/cards/card", document, XPathConstants.NODESET);
			for (int i = 0; i < cardNodeList.getLength(); i++){
//				DeckEditor.println(cardNodeList.item(i).getNodeName());
				cardArrayList.add(getCardData(cardNodeList.item(i)));
			}
//			cardArrayList.forEach(card -> DeckEditor.println(card.getSet().getName()));
		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e){
			DeckEditor.println(e.getMessage());
		}
		
	}
	
	private Card getCardData(Node node){
		Card card = new Card();
		if (node.getNodeType() == Node.ELEMENT_NODE){
			Element element = (Element) node;
			card.setName(getTagValue("name", element));
			card.setSet(findSet(getTagValue("set", element)));
			card.
		}
		return card;
	}
	
	private CardSet findSet(String code){
		for (CardSet set : setArrayList)
			if (set.getCode().equalsIgnoreCase(code))
				return set;
		return null;
	}
	
	private CardSet getSetData(Node node){
		CardSet set = new CardSet();
		if (node.getNodeType() == Node.ELEMENT_NODE){
			Element element = (Element) node;
			set.setName(getTagValue("name", element));
			set.setCode(getTagValue("code", element));
			set.setReleaseDate(getTagValue("date", element));
			set.setPromo(Boolean.parseBoolean(getTagValue("is_promo", element).toLowerCase()));
		}
		return set;
	}
	
	private static String getTagValue(String tag, Element element){
		return element.getElementsByTagName(tag).item(0).getFirstChild().getNodeValue();
	}
	
	public ArrayList<CardSet> getSetArrayList(){
		return setArrayList;
	}
}
