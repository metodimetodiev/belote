package belot;

import java.util.Random;

public class Gaming implements BeloteConstants {

	BeloteRules beloteRules = new BeloteRules();
	int toSeeWhoSaysIt = 0;

	public int whoTurnIsIt(int dealer) {
		dealer++;
		if (dealer == 4) {
			dealer = 1;
		}
		return dealer;
	}

	public void dealThreeCards(Player players, Deck deck) {
		int temp1 = 0;

		while (temp1 < 3) {
			players.addCard(deck);
			temp1++;
		}

	}

	public void dealTwoCards(Player players, Deck deck) {
		int temp1 = 0;

		while (temp1 < 2) {
			players.addCard(deck);
			temp1++;
		}

	}

	public String callTrump() {
		Random rand = new Random();
		String bid;
		int i = rand.nextInt(6);
		bid = TRUMP_NAMES[i];
		return bid;
	}

	public int[] returnBid(String bid, Player[] players, int whoSaitIt) {
		int[] toSee = new int[2];
		int temp = 0;
		int[] toSeeWhichTeamSaid = new int[3];
		int tempForSaying = 0;
		int toSeeWhatPosition = 1;

		if (bid.equals(TRUMP_NAMES[0])) {
			toSee = beloteRules.suits(players, whoSaitIt);
		}

		if (bid.equals(TRUMP_NAMES[1])) {
			toSee = beloteRules.suits(players, whoSaitIt);
		}

		if (bid.equals(TRUMP_NAMES[2])) {
			toSee = beloteRules.suits(players, whoSaitIt);
		}

		if (bid.equals(TRUMP_NAMES[3])) {
			toSee = beloteRules.suits(players, whoSaitIt);

		}

		if (bid.equals(TRUMP_NAMES[4])) {
			toSee = beloteRules.notTrump(players);

		}

		if (bid.equals(TRUMP_NAMES[5])) {
			toSee = beloteRules.trump(players);
		}
		toSeeWhichTeamSaid[0] = toSee[0];
		toSeeWhichTeamSaid[1] = toSee[1];
		toSeeWhichTeamSaid[2] = whoSaitIt;

		return toSeeWhichTeamSaid;
	}

	public Card cardInPlay(Player[] players, int temp) {
		Card card = players[temp].getHand().get(0);
		return players[temp].putDownCard(card);
	}

	public int getCardPoint(Card card) {
		return card.getValue();
	}

	public int calculateScores(int scores) {
		return scores / 10;
	}

	public int scores(int team1, int team2) {
		int temp = 0;
		if (Math.max(team1, team2) >= 151) {
			System.out.printf("team1 points: %d\n", team1);
			System.out.printf("team2 points: %d", team2);
			temp = 1;
		}
		return temp;
	}
}
