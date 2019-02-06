package belot;

public interface BeloteConstants {
	public final static int NUMBER_OF_PLAYERS = 4;
	public final static int CARDS_IN_DECK = 32;
	public final static int CARDS_IN_HAND = 8;

	// multipliers for trump
	public final static int UNKNOWN = 0;
	public final static int CLUBS = 1;
	public final static int DIAMONDS = 2;
	public final static int HEARTS = 3;
	public final static int SPADES = 4;
	public final static int NOTHING = 5;
	public final static int EVERYTHING = 6;
	public final static int[] CLUBS_POSITION = { 0, 1, 2, 3, 4, 5, 6 };
	public final static String[] SUIT_NAMES = { "", "clubs", "diamonds", "hearts", "spades" };

	public final static int[] multipliersForTrump = { 0, 1, 2, 3, 4, 5, 6 };
	// standard values of cards
	public final static int SEVEN = 0;
	public final static int EIGHT = 1;
	public final static int NINE = 2;
	public final static int TEN = 3;
	public final static int JACK = 4;
	public final static int QUEEN = 5;
	public final static int KING = 6;
	public final static int ACE = 7;
	public final static int[] NOT_TRUMP_VALUES = { 0, 0, 0, 10, 2, 3, 4, 11 };
	public final static int[] TRUMP_VALUES = { 0, 0, 14, 10, 20, 3, 4, 11 };

	public final static String[] TRUMP_NAMES = { "clubs", "diamonds", "hearts", "spades", "noTrump", "trump", "pass" };
	public final static String[] RANK_NAMES = { "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace" };

	// having four of the same card bonuses
	public final static int[] ALL_FOUR = { JACK, NINE, ACE, TEN, KING, QUEEN };
	public final static int[] ALL_FOUR_BONUSES = { 200, 150, 100, 100, 100, 100 };

	// scoring bonuses
	public final static int BELOT_BONUS = 20;
	public final static int LAST_TRICK_BONUS = 10;
	public final static int ALL_TRICKS_BONUS = 100;
	public final static int TIERCE_BONUS = 20;
	public final static int QUART_BONUS = 50;
	public final static int QUINT_BONUS = 100;

	// the 9s and Js change values depending if they are trump
	public final static int NINE_OF_TRUMP_VALUE = 14;
	public final static int NINE_OF_NONTRUMP_VALUE = 0;
	public final static int JACK_OF_TRUMP_VALUE = 20;
	public final static int JACK_OF_NONTRUMP_VALUE = 2;

	// it would be better to use a boolean value for these two!
	public final static int USE_TRUMP = 10;
	public final static int DONT_USE_TRUMP = 11;

}
