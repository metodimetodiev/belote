package belot;

import java.util.Arrays;
import java.util.Collections;

public class Deck implements BeloteConstants {

	private Card[] deck;
	private int position = 0;

	public Deck() {
		deck = new Card[CARDS_IN_DECK];
		int cardNumber = 0;
		int cardIndex = 0;

		for (int i = CLUBS; i <= SPADES; i++) {
			for (int j = SEVEN; j <= ACE; j++) {
				deck[cardNumber] = new Card(i, RANK_NAMES[j], NOT_TRUMP_VALUES[j]);
				cardNumber++;
				cardIndex++;
			}
			cardIndex += 3;
		}

		shuffle();
	}

	public Card getCardFromDeck() {
		Card card = deck[position];

		if (position < 32) {
			position++;
		}

		return card;
	}

	public void shuffle() {
		Card card;
		int temp;

		for (int i = 0; i < deck.length; i++) {
			temp = (int) (Math.random() * deck.length);

			card = deck[i];
			deck[i] = deck[temp];
			deck[temp] = card;
		}
	}
}
