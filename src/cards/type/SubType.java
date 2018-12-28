package cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum SubType {
	Advisor, Aetherborn, Ajani, Alara, Ally, Aminatou, And, Angel, Angrath, Anteater, Antelope, Ape, Arcane, Archer,
	archon, Archon, Arkhos, Arlinn, Artificer, Ashiok, Assassin, Assembly, Assemblyworker, Atog, Aura, Aurochs, Autobot,
	Avatar, Azgol, azra, Azra, Baddest, Badger, Barbarian, Basilisk, Bat, Bear, Beast, Beaver, Beeble, Belenon,
	Berserker, Biggest, Bird, Blinkmoth, Boar, bolas, Bolas, Brainiac, Bringer, Brushwagg, Bureaucrat, Camarid, Camel,
	Caribou, Carrier, Cartouche, Cat, Centaur, cephalid, Cephalid, Chandra, Chicken, Child, Chimera, Citizen, Clamfolk,
	Cleric, Cockatrice, Conspiracy, Construct, contraption, Contraption, Cow, Coward, Crab, Crocodile, Curse, Cyborg,
	Cyclops, Dack, Daretti, Dauthi, Deer, Demon, Desert, deserter, Designer, Devil, Dinosaur, Djinn, Dominaria, Domri,
	Donkey, Dovin, Dragon, Drake, Dreadnought, Drone, druid, Druid, Dryad, Dungeon, Dwarf, Eaturecray, Efreet, Egg,
	Elder, Eldrazi, Elemental, Elephant, Elf, Elk, Elspeth, Elves, equilor, Equilor, Equipment, Ergamon, Estrid,
	Etiquette, Ever, Eye, Fabacin, Faerie, Ferret, Fish, Flagbearer, Forest, fortification, Fortification, Fox,
	Freyalise, Frog, Fungus, Gamer, Gargoyle, Garruk, Gate, Germ, Giant, Gideon, Gnome, Goat, goblin, Goblin, God,
	Golem, Gorgon, Graveborn, Gremlin, Griffin, Gus, Hag, Harpy, Hatificer, Head, Hellion, Hero, Hippo, hippogriff,
	Hippogriff, Homarid, Homunculus, Hormarid, Horror, Horse, Host, Hound, Huatli, Human, Hydra, Hyena, Igpay, Illusion,
	imp, Imp, Incarnation, Innistrad, Insect, Iquatana, Ir, Island, Jace, Jackal, Jaya, Jellyfish, Juggernaut, Kaldheim,
	kamigawa, Kamigawa, Kangaroo, Karn, Karsus, Kavu, Kaya, Kephalai, Killbot, Kinshala, Kiora, Kirin, Kithkin, Knight,
	Kobold, kolbahan, Kolbahan, Kor, Koth, Kraken, Kyneth, Lady, Lair, Lamia, Lammasu, Leech, Legend, Leviathan,
	Lhurgoyf, Licid, Liliana, lizard, Lizard, Lobster, Locus, Lorwyn, Luvion, Manticore, Master, Masticore, Meditation,
	Mercadia, Mercenary, Merfolk, Metathran, mime, Mime, Mine, Minion, Minotaur, Mirrodin, Moag, Mole, Monger, Mongoose,
	Mongseng, Monk, Monkey, Moonfolk, Mountain, mummy, Mummy, Muraganda, Mutant, Myr, Mystic, Naga, Nahiri, Narset,
	Nastiest, Nautilus, Nephilim, New, Nightmare, Nightstalker, ninja, Ninja, Nissa, Nixilis, Noggle, Nomad, Nymph,
	Octopus, of, Of, Ogre, Ooze, Or, Orb, Orc, Orgg, Ouphe, Ox, Oyster, pangolin, Pangolin, Paratrooper, Pegasus,
	Penguin, Pentavite, Pest, Phelddagrif, Phenomenon, Phoenix, Phyrexia, Pilot, pincher, Pirate, Plains, Plane, Plant,
	Power, Praetor, Prism, Processor, Proper, Pyrulea, Rabbit, Rabiah, Raccoon, ral, Ral, Rat, Rath, Ravnica, Realm,
	Rebel, Reflection, Regatha, Reveler, Rhino, Rigger, Rogue, Rowan, Rukh, s, Sable, Saga, Saheeli, salamander,
	Salamander, Samurai, Samut, Sand, Saproling, Sarkhan, Satyr, Scarecrow, Scariest, Scheme, Scientist, Scion,
	scorpion, Scorpion, Scout, See, Segovia, Serf, Serpent, Serra, Servo, Shade, Shadowmoor, Shaman, Shandalar,
	Shapeshifter, Sheep, ship, Ship, Shrine, Siren, Skeleton, Slith, Sliver, Slug, Snake, Soldier, Soltari, Sorin,
	Spawn, Specter, Spellshaper, sphinx, Sphinx, Spider, Spike, Spirit, Splinter, Sponge, Spy, Squid, Squirrel,
	Starfish, Surrakar, Survivor, Swamp, Tamiyo, teferi, Teferi, Tetravite, Tezzeret, Thalakos, The, Thopter, Thrull,
	Tibalt, Tower, Townsfolk, Trap, Treasure, Treefolk, Tribal, Trilobite, triskelavite, Troll, Turtle, U2019s, Ugin,
	Ulgrotha, Unicorn, Urza, Valla, Vampire, Vampyre, Vanguard, Vedalken, vehicle, Vehicle, Venser, Viashino, Villain,
	Vivien, Volver, Vraska, Vryn, Waiter, Wall, Warrior, Weird, Werewolf, Whale, wildfire, Wildfire, Will, Windgrace,
	Wizard, Wolf, Wolverine, Wombat, Worker, Worm, Wraith, Wrestler, Wurm, Xenagos, Xerex, yanggu, Yanggu, Yanling,
	Yeti, Zendikar, Zombie, Zubera;
	public static final ArrayList<String> errorSubTypes = new ArrayList<>();
	public static ArrayList<SubType> parseString(String string) {
		ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList(string.trim().split("\\W+")));
		ArrayList<SubType> subTypes = new ArrayList<>();
		for (String strings : stringArrayList) {
			if (strings != null && strings.length() > 0)
				try {
					subTypes.add(valueOf(strings));
				} catch (Exception e) {
					if (!errorSubTypes.contains(strings))
						errorSubTypes.add(strings);
				}
		}
		return subTypes;
	}
}
