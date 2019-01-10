package gui;

import cards.Card;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CardView extends JDialog {
	private Card card;
	private final JPanel contentPanel = new JPanel();

	public CardView(Card card) {
		setTitle(card.getName());
		this.card = card;
		setBounds(100, 100, 450, 300);
		drawUI();
	}

	private void drawUI() {
		setBounds(100, 100, 559, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 528, 291);
		contentPanel.add(panel);
		panel.setLayout(null);
		JLabel CardName = new JLabel("Card Name: ");
		CardName.setBounds(0, 0, 167, 14);
		panel.add(CardName);
		
		JLabel NameField = new JLabel(card.getName());
		NameField.setBounds(172, 0, 326, 14);
		panel.add(NameField);
		
		JLabel ManaCost = new JLabel("Mana Cost:");
		ManaCost.setBounds(0, 19, 167, 14);
		panel.add(ManaCost);
		
		JLabel ManaCostField = new JLabel(card.getSymbols());
		ManaCostField.setBounds(172, 19, 326, 14);
		panel.add(ManaCostField);
		
		JLabel Ability = new JLabel("Ability:");
		Ability.setBounds(0, 38, 167, 14);
		panel.add(Ability);
		
		JLabel AbilityField = new JLabel(card.getAbility());
		AbilityField.setBounds(172, 38, 326, 14);
		panel.add(AbilityField);
		
		JLabel Type = new JLabel("Type:");
		Type.setBounds(0, 57, 167, 14);
		panel.add(Type);
		
		JLabel TypeField = new JLabel(card.getType());
		TypeField.setBounds(172, 57, 326, 14);
		panel.add(TypeField);
		
		JLabel Rarity = new JLabel("Rarity");
		Rarity.setBounds(0, 76, 167, 14);
		panel.add(Rarity);
		
		JLabel RarityField = new JLabel(card.getRarity().toString());
		RarityField.setBounds(172, 76, 326, 14);
		panel.add(RarityField);
		
		if (card.getFlavorText() != null && !card.getFlavorText().isEmpty()){
			JLabel FlavorText = new JLabel("Flavor Text");
			FlavorText.setBounds(0, 95, 167, 14);
			panel.add(FlavorText);
			
			JLabel FlavorTextField = new JLabel(card.getFlavorText());
			FlavorTextField.setBounds(172, 95, 326, 14);
			panel.add(FlavorTextField);
		}
		
		JLabel Artist = new JLabel("Artist");
		Artist.setBounds(0, 114, 167, 14);
		panel.add(Artist);
		
		JLabel ArtistField = new JLabel(card.getArtist());
		ArtistField.setBounds(172, 114, 326, 14);
		panel.add(ArtistField);
		
		
		
		if (!card.getPower().isEmpty() && !card.getToughness().isEmpty()){
			JLabel PT = new JLabel("P/T");
			PT.setBounds(0, 133, 167, 14);
			panel.add(PT);
			
			JLabel PTField = new JLabel(card.getPower() + "/" + card.getToughness());
			PTField.setBounds(172, 133, 326, 14);
			panel.add(PTField);
			
			
		}
		JLabel Loyalty = new JLabel("Loyalty");
		Loyalty.setBounds(0, 152, 167, 15);
		panel.add(Loyalty);
		
		JLabel LoyaltyField = new JLabel("{field}");
		LoyaltyField.setBounds(172, 152, 326, 15);
		panel.add(LoyaltyField);
	}
}
