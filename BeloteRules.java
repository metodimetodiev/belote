package belot;

import java.util.List;
import java.util.Scanner;

public class BeloteRules implements BeloteConstants {

    private int dealer = 2;
    String chooaseACard = "";
    Scanner putCard = new Scanner(System.in);
    int whoPlayesCard = whoTurnIsItToCheckMethod();
    Card cardLoser = null;

    public int raiseHelper(int temp) {
        temp++;
        if (temp == 4) {
            temp = 0;
        }
        return temp;
    }

    private int whoTurnIsItToCheckMethod() {
        dealer++;
        if (dealer == 4) {
            dealer = 0;
        }
        return dealer;
    }

    public int[] trump(Player[] players) {
        int changeIndex = 0;
        int[] teamOneAndTeamTwoPoint = new int[2];
        int teamOneScore = 0;
        int teamTwoScore = 0;
        int score = 0;
        Card cardTemp = null;
        Card cardTempToSeeTurn = null;


        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            trumpValues(players[i].getHand());
        }
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            System.out.println("Start next round");
            if (i != 0) {
                whoPlayesCard = changeIndex;
                if (whoPlayesCard % 2 == 0) {
                    teamOneScore += score;
                } else {
                    teamTwoScore += score;
                }

            }
            cardTemp = seeWhatCardIsInPlayAndPutCardHelper(players[whoPlayesCard], whoPlayesCard);
            whoPlayesCard = raiseHelper(whoPlayesCard);
            changeIndex = 0;
            for (int j = 0; j < 3; j++) {
                cardTempToSeeTurn = playingTrumpCardMethod(players, whoPlayesCard, cardTemp);
                if (whoPlayesCard % 2 == 0) {
                    teamOneScore += beloteCheck(cardTemp, players, whoPlayesCard, cardTempToSeeTurn);
                } else {
                    teamTwoScore += beloteCheck(cardTemp, players, whoPlayesCard, cardTempToSeeTurn);
                }
                System.out.println("Card that takes for now " + cardTempToSeeTurn);
                if (cardTempToSeeTurn.getSuit() == cardTemp.getSuit()) {

                    if (cardTempToSeeTurn.getValue() > cardTemp.getValue()) {
                        cardTemp = cardTempToSeeTurn;
                        changeIndex = whoPlayesCard;
                    }
                }


                whoPlayesCard = raiseHelper(whoPlayesCard);
                score += cardLoserCheck();
                score += cardTempToSeeTurn.getValue();
            }
            cardTemp = cardTempToSeeTurn;
        }
        teamOneAndTeamTwoPoint[0] = teamOneScore;
        teamOneAndTeamTwoPoint[1] = teamTwoScore;
        return teamOneAndTeamTwoPoint;
    }

    public int[] notTrump(Player[] players) {
        int changeIndex = 0;
        int[] teamOneAndTeamTwoPoint = new int[2];
        int teamOneScore = 0;
        int teamTwoScore = 0;
        int score = 0;
        Card cardTemp = null;
        Card cardTempToSeeTurn = null;

        for (int i = 0; i < CARDS_IN_HAND; i++) {
            System.out.println("Start next round");
            if (i != 0) {

                whoPlayesCard = changeIndex;
                if (whoPlayesCard % 2 == 0) {
                    teamOneScore += score;
                } else {
                    teamTwoScore += score;
                }

            }
            cardTemp = seeWhatCardIsInPlayAndPutCardHelper(players[whoPlayesCard], whoPlayesCard);
            whoPlayesCard = raiseHelper(whoPlayesCard);
            changeIndex = 0;
            for (int j = 0; j < 3; j++) {
                cardTempToSeeTurn = playingNoTrumpCardMethod(players, whoPlayesCard, cardTemp);
                System.out.println("Card that takes for now " + cardTempToSeeTurn);
                if (cardTempToSeeTurn.getSuit() == cardTemp.getSuit() &&
                        cardTempToSeeTurn.getValue() > cardTemp.getValue()) {
                    cardTemp = cardTempToSeeTurn;
                    changeIndex = whoPlayesCard;
                }
                whoPlayesCard = raiseHelper(whoPlayesCard);
                score += cardLoserCheck();
                score += cardTempToSeeTurn.getValue();
            }

            cardTemp = cardTempToSeeTurn;
        }
        teamOneAndTeamTwoPoint[0] = teamOneScore;
        teamOneAndTeamTwoPoint[1] = teamTwoScore;
        return teamOneAndTeamTwoPoint;
    }

    public int[] suits(Player[] players, int whatSuit) {
        int changeIndex = 0;
        int[] teamOneAndTeamTwoPoint = new int[2];
        int teamOneScore = 0;
        int teamTwoScore = 0;
        int score = 0;
        Card cardTemp = null;
        Card cardTempToSeeTurn = null;


        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (players[i].getHand().get(i).getSuit() == whatSuit) {
                suitsTrumpValues(players[i].getHand().get(i));
            }
        }
        for (int i = 0; i < CARDS_IN_HAND; i++) {
            System.out.println("Start next round");
            if (i != 0) {

                whoPlayesCard = changeIndex;
                if (whoPlayesCard % 2 == 0) {
                    teamOneScore += score;
                } else {
                    teamTwoScore += score;
                }

            }
            cardTemp = seeWhatCardIsInPlayAndPutCardHelper(players[whoPlayesCard], whoPlayesCard);
            whoPlayesCard = raiseHelper(whoPlayesCard);
            changeIndex = 0;
            for (int j = 0; j < 3; j++) {
                if (cardTemp.getSuit() == whatSuit) {
                    cardTempToSeeTurn = playingTrumpCardMethod(players, whoPlayesCard, cardTemp);
                } else {
                    cardTempToSeeTurn = suitHelper(players[whoPlayesCard], whatSuit, score, cardTemp);
                    if (cardTempToSeeTurn == null) {
                        cardTempToSeeTurn = playingNoTrumpCardMethod(players, whoPlayesCard, cardTemp);
                    }
                }
                System.out.println("Card that takes for now " + cardTempToSeeTurn);
                if (cardTempToSeeTurn.getSuit() == cardTemp.getSuit() &&
                        cardTempToSeeTurn.getValue() > cardTemp.getValue()) {
                    cardTemp = cardTempToSeeTurn;
                    changeIndex = whoPlayesCard;
                }
                whoPlayesCard = raiseHelper(whoPlayesCard);
                score += cardLoserCheck();
                score += cardTempToSeeTurn.getValue();
            }

            cardTemp = cardTempToSeeTurn;
        }
        teamOneAndTeamTwoPoint[0] = teamOneScore;
        teamOneAndTeamTwoPoint[1] = teamTwoScore;
        return teamOneAndTeamTwoPoint;
    }

    public Card seeWhatCardIsInPlayAndPutCardHelper(Player player, int whoPlayesCard) {
        Card cardIntPlay = null;
        if (whoPlayesCard != 2) {
            cardIntPlay = player.putDownCard(player.getHand().get(0));
            System.out.printf("Player[%d] plays card %s", whoPlayesCard, cardIntPlay);
            System.out.println();
        } else {
            System.out.println("Chooase a card from your card are" + player.getHand());
            System.out.println();
            chooaseACard = putCard.nextLine();
            cardIntPlay = player.putDownCard(player.getHand().get(Integer.parseInt(chooaseACard)));
        }
        return cardIntPlay;
    }

    public Card trumpHelper(Card cardIntPlay, int playerWhoPutsDownCard, Player player) {
        int temp = 0;

        System.out.println("Card that takes for now " + cardIntPlay);
        System.out.println();
        for (int i = 0; i < player.getHand().size(); i++) {
            if (cardIntPlay.getSuit() == player.getHand().get(i).getSuit()) {
                if (cardIntPlay.getValue() < player.getHand().get(i).getValue()) {
                    System.out.printf("Player[%d] play1s card %s", playerWhoPutsDownCard, player.getHand().get(i));
                    System.out.println();
                    cardIntPlay = player.putDownCard(player.getHand().get(i));
                    break;
                }
                temp = i;
            }
            if (i == player.getHand().size() - 1) {
                cardIntPlay = indexCheck(temp, player);
            }
        }
        return cardIntPlay;
    }

    public Card indexCheck(int temp, Player player) {
        Card cardInPlay = null;
        System.out.printf("Player[%d] plays card %s", temp, player.getHand().get(temp));
        System.out.println();
        cardInPlay = player.putDownCard(player.getHand().get(temp));
        return cardInPlay;
    }


    public Card playingTrumpCardMethod(Player[] players, int whoPlayesCard, Card cardTemp) {
        Card cardTempToSeeTurn = null;
        if (whoPlayesCard == 2) {
            cardTempToSeeTurn = seeWhatCardIsInPlayAndPutCardHelper(players[whoPlayesCard], whoPlayesCard);
        } else {
            cardTempToSeeTurn = trumpHelper(cardTemp, whoPlayesCard, players[whoPlayesCard]);
        }
        return cardTempToSeeTurn;
    }

    public int cardLoserCheck() {
        int score = 0;
        if (cardLoser != null) {
            score = cardLoser.getValue();
            cardLoser = null;
        }
        return score;
    }

    public List<Card> trumpValues(List<Card> hand) {
        for (int i = 0; i < TRUMP_VALUES.length; i++) {
            for (int j = 0; j < hand.size(); j++) {
                if (RANK_NAMES[i].equals(hand.get(j).getName())) {
                    hand.get(j).setValue(TRUMP_VALUES[i]);
                }
            }
        }
        return hand;
    }


    public Card playingNoTrumpCardMethod(Player[] players, int whoPlayesCard, Card cardTemp) {
        Card cardTempToSeeTurn = null;
        if (whoPlayesCard == 2) {
            cardTempToSeeTurn = seeWhatCardIsInPlayAndPutCardHelper(players[whoPlayesCard], whoPlayesCard);
        } else {
            cardTempToSeeTurn = noTtrumpHelper(cardTemp, whoPlayesCard, players[whoPlayesCard]);
        }
        return cardTempToSeeTurn;
    }

    public Card noTtrumpHelper(Card cardIntPlay, int playerWhoPutsDownCard, Player player) {
        int temp = 0;
        int index = 0;
        System.out.println("Card that takes for now " + cardIntPlay);
        System.out.println();
        for (int i = 0; i < player.getHand().size() - index; i++) {
            if (cardIntPlay.getSuit() == player.getHand().get(i).getSuit()) {
                System.out.printf("Player[%d] play1s card %s", playerWhoPutsDownCard, player.getHand().get(i));
                System.out.println();
                cardIntPlay = player.putDownCard(player.getHand().get(i));
                break;
            }
            if (i == player.getHand().size() - 1) {
                cardIntPlay = indexCheck(temp, player);

            }
        }
        return cardIntPlay;
    }


    public Card suitHelper(Player player, int whatSuit, int score, Card cardTemp) {
        Card cardTempToSeeTurn = null;
        for (int k = 0; k < player.getHand().size(); k++) {
            if (player.getHand().get(k).getSuit() == whatSuit) {
                cardTempToSeeTurn = player.getHand().get(k);
                cardLoser = cardTemp;
                score += cardLoserCheck();
                break;
            }
        }
        return cardTempToSeeTurn;
    }

    public Card suitsTrumpValues(Card hand) {
        for (int i = 0; i < TRUMP_VALUES.length; i++) {
            if (RANK_NAMES[i].equals(hand.getName())) {
                hand.setValue(TRUMP_VALUES[i]);
            }
        }
        return hand;
    }

    public int beloteCheck(Card cardInPlay, Player[] players, int whoPlayesCard, Card card) {
        int temp = 0;
        for (int i = 0; i < players[whoPlayesCard].getHand().size(); i++) {
            if (card.getSuit() == cardInPlay.getSuit()) {
                if (players[whoPlayesCard].getHand().get(i).getName().equals(RANK_NAMES[5]) && card.getName().equals(RANK_NAMES[6])) {
                    temp = 20;
                }
                if (players[whoPlayesCard].getHand().get(i).getName().equals(RANK_NAMES[6]) && card.getName().equals(RANK_NAMES[5])) {
                    temp = 20;
                }
            }
        }
        return temp;
    }


}
