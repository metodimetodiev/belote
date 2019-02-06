package belot;

import java.util.List;

public class Player implements BeloteConstants {

	private String name;
	private List<Card> hand;
	private Deck deck = new Deck();

	public Player(String name, List<Card> hand) {
		setName(name);
		this.hand = hand;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public List<Card> getHand() {
		return hand;
	}

	public Card addCard(Deck deck) {
		Card card = deck.getCardFromDeck();

		hand.add(card);

		return card;
	}

	public Card putDownCard(Card card) {
		for (int i = 0; i < hand.size(); i++) {

			if (hand.get(i) == card) {
				hand.remove(hand.get(i));
				break;
			}
		}

		return card;
	}

}
