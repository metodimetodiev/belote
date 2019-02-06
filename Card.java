package belot;

public class Card implements BeloteConstants {

	private int suit;
	private String name;
	private int value;

	public Card(int suit, String name, int value) {
		setSuit(suit);
		setName(name);
		setValue(value);
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(SUIT_NAMES[suit]);
		sb.append(" ").append(name);

		return sb.toString();
	}
}
