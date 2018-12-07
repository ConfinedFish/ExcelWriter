package main.java.cards.type;

import java.util.ArrayList;
import java.util.Arrays;

public enum SubType {
	Advisor, Aetherborn, Ajani, Alara, Ally, Aminatou, And, Angel, Angrath, Anteater, Antelope, Ape, Arcane, Archer,
	archon, Arkhos, Arlinn, Artificer, Ashiok, Assassin, Assembly, Assemblyworker, Atog, Aura, Aurochs, Avatar, Azgol,
	azra, Badger, Barbarian, Basilisk, Bat, Bear, Beast, Beaver, Beeble, Belenon, Berserker, Bird, Blinkmoth, Boar,
	bolas, Brainiac, Bringer, Brushwagg, Bureaucrat, Camarid, Camel, Caribou, Carrier, Cartouche, Cat, Centaur,
	cephalid, Chandra, Chicken, Child, Chimera, Citizen, Clamfolk, Cleric, Cockatrice, Conspiracy, Construct,
	contraption, Cow, Coward, Crab, Crocodile, Curse, Cyborg, Cyclops, Dack, Daretti, Dauthi, Deer, Demon, Desert,
	deserter, Designer, Devil, Dinosaur, Djinn, Dominaria, Domri, Donkey, Dovin, Dragon, Drake, Dreadnought, Drone,
	druid, Dryad, Dwarf, Eaturecray, Efreet, Egg, Elder, Eldrazi, Elemental, Elephant, Elf, Elk, Elspeth, Elves,
	equilor, Equipment, Ergamon, Estrid, Etiquette, Ever, Eye, Fabacin, Faerie, Ferret, Fish, Flagbearer, Forest,
	fortification, Fox, Freyalise, Frog, Fungus, Gamer, Gargoyle, Garruk, Gate, Germ, Giant, Gideon, Gnome, Goat,
	goblin, God, Golem, Gorgon, Graveborn, Gremlin, Griffin, Gus, Hag, Harpy, Hatificer, Head, Hellion, Hero, Hippo,
	hippogriff, Homarid, Homunculus, Hormarid, Horror, Horse, Host, Hound, Huatli, Human, Hydra, Hyena, Igpay, Illusion,
	imp, Incarnation, Innistrad, Insect, Iquatana, Ir, Island, Jace, Jackal, Jaya, Jellyfish, Juggernaut, Kaldheim,
	kamigawa, Kangaroo, Karn, Karsus, Kavu, Kaya, Kephalai, Killbot, Kinshala, Kiora, Kirin, Kithkin, Knight, Kobold,
	kolbahan, Kor, Koth, Kraken, Kyneth, Lady, Lair, Lamia, Lammasu, Leech, Legend, Leviathan, Lhurgoyf, Licid, Liliana,
	lizard, Lobster, Locus, Lorwyn, Luvion, Manticore, Masticore, Meditation, Mercadia, Mercenary, Merfolk, Metathran,
	mime, Mine, Minion, Minotaur, Mirrodin, Moag, Mole, Monger, Mongoose, Mongseng, Monk, Monkey, Moonfolk, Mountain,
	mummy, Muraganda, Mutant, Myr, Mystic, Naga, Nahiri, Narset, Nautilus, Nephilim, New, Nightmare, Nightstalker,
	ninja, Nissa, Nixilis, Noggle, Nomad, Nymph, Octopus, Of, Ogre, Ooze, Or, Orb, Orc, Orgg, Ouphe, Ox, Oyster,
	pangolin, Paratrooper, Pegasus, Penguin, Pentavite, Pest, Phelddagrif, Phenomenon, Phoenix, Phyrexia, Pilot,
	pincher, Pirate, Plains, Plane, Plant, Power, Praetor, Prism, Processor, Proper, Pyrulea, Rabbit, Rabiah, Raccoon,
	ral, Rat, Rath, Ravnica, Realm, Rebel, Reflection, Regatha, Rhino, Rigger, Rogue, Rowan, Sable, Saga, Saheeli,
	salamander, Samurai, Samut, Sand, Saproling, Sarkhan, Satyr, Scarecrow, Scariest, Scheme, Scientist, Scion,
	scorpion, Scout, See, Segovia, Serf, Serpent, Serra, Shade, Shadowmoor, Shaman, Shandalar, Shapeshifter, Sheep,
	ship, Shrine, Siren, Skeleton, Slith, Sliver, Slug, Snake, Soldier, Soltari, Sorin, Spawn, Specter, Spellshaper,
	sphinx, Spider, Spike, Spirit, Splinter, Sponge, Spy, Squid, Squirrel, Starfish, Surrakar, Survivor, Swamp, Tamiyo,
	teferi, Tetravite, Tezzeret, Thalakos, Thopter, Thrull, Tibalt, Tower, Townsfolk, Trap, Treefolk, Tribal, Trilobite,
	triskelavite, Troll, Turtle, U2019s, Ugin, Ulgrotha, Unicorn, Urza, Valla, Vampire, Vampyre, Vanguard, Vedalken,
	vehicle, Venser, Viashino, Villain, Vivien, Volver, Vraska, Vryn, Waiter, Wall, Warrior, Weird, Werewolf, Whale,
	wildfire, Will, Windgrace, Wizard, Wolf, Wolverine, Wombat, Worker, Worm, Wraith, Wrestler, Wurm, Xenagos, Xerex,
	yanggu, Yanling, Yeti, Zendikar, Zombie, Zubera, Cephalid, Imp, Goblin, Druid, Lizard, Scorpion, s, Vehicle, Sphinx,
	Azra, Archon, Ninja, Hippogriff, Teferi, Bolas, Ral, Salamander, Pangolin, Treasure, Fortification, Yanggu, Autobot,
	Equilor, Kamigawa, Wildfire, Dungeon, Master, Servo, Rukh, Mime, Kolbahan, Reveler, of, The, Biggest, Baddest,
	Nastiest, Mummy, Ship, and, or, Contraption;
	public static ArrayList<String> errorSubTypes = new ArrayList<String>();
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
