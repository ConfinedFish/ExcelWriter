package test.Types;

import test.Card;
import test.SubType.PlanesWalkerSubType;

public interface Plainswalker extends Card{
	Integer getLoyalty();
	PlanesWalkerSubType getType();
	
}
