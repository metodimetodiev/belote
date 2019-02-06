package belot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main implements BeloteConstants {

    public static void main(String[] args) {
        BeloteRules beloteRules = new BeloteRules();
        int[] teamOneAndTeamTwoScores = new int[2];
        Gaming game = new Gaming();
        Main main = new Main();
        Player[] players = new Player[4];
        Scanner scan = new Scanner(System.in);
        int teamOne = 0;
        int teamTwo = 0;

        players = main.createPlayers().clone();
        System.out.println("Choose your name:");
        String username = scan.nextLine();
        players[3].setName(username);


        do {
            Deck deck = new Deck();
            String board = String.format("        %s\n%s              %s\n        %s", players[0].getName(),
                    players[1].getName(), players[3].getName(), players[2].getName());
            System.out.println(board);
            int whoTurn = game.whoTurnIsIt(0);

            main.dealFiveCards(players, deck);

            System.out.println(main.printStatus(players));
            teamOneAndTeamTwoScores = main.chooseAnonces(players, deck, whoTurn);
            teamOne += teamOneAndTeamTwoScores[0];
            teamTwo += teamOneAndTeamTwoScores[1];
        } while (Math.max(teamOne, teamTwo) <= 1510);
        System.out.printf("TeamOne points %d   TeamTwo points %d", teamOne, teamTwo);
    }


    public Player[] createPlayers() {
        Player[] players = new Player[4];

        List<Card> hand0 = new ArrayList<Card>();
        List<Card> hand1 = new ArrayList<Card>();
        List<Card> hand2 = new ArrayList<Card>();
        List<Card> hand3 = new ArrayList<Card>();

        Player one = new Player("One", hand0);
        Player two = new Player("Two", hand1);
        Player three = new Player("Three", hand2);
        Player four = new Player("Four", hand3);

        players[0] = one;
        players[1] = two;
        players[2] = three;
        players[3] = four;

        return players;
    }

    public String printStatus(Player[] players) {
        StringBuilder sb = new StringBuilder();

        sb.append("\nYour cards are: \n").append(players[2].getHand());

        return sb.toString();
    }

    public void dealFiveCards(Player[] players, Deck deck) {
        Gaming game = new Gaming();

        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            game.dealThreeCards(players[i], deck);
        }

        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            game.dealTwoCards(players[i], deck);
        }
    }

    public int[] chooseAnonces(Player[] players, Deck deck, int whoTurn) {
        Deck deck1 = new Deck();
        int temp = 0;
        String help = "";
        Scanner scan = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        Gaming game = new Gaming();
        int[] teamOneAndTeamTwoPoints = new int[3];
        int[] soutTeamPoint = new int[2];

        List<String> anonces = new ArrayList<>();
        anonces = Arrays.asList(RANK_NAMES);
        String player1 = "";
        String player2 = "";
        String player4 = "";
        String anonce = "";
        int toSee = 0;
        System.out.println("Choose anonce: \n"
                + "clubs, diamonds, hearts, spades, noTrump, trump, pass");

        while (toSee < 2) {
            toSee = 0;
            anonce = scan.nextLine();
            temp = howHigh(anonce);
            help = anonce;
            if (anonce.equals("pass")) {
                toSee++;
            }
            if (howHigh(game.callTrump()) > temp) {
                player1 = game.callTrump();
                temp = howHigh(game.callTrump());
                help = player1;
            } else {
                player1 = "pass";
                toSee++;
            }
            if (howHigh(game.callTrump()) > temp) {
                player2 = game.callTrump();
                temp = howHigh(game.callTrump());
                help = player2;
                toSee++;
            } else {
                player2 = "pass";
                toSee++;
            }
            if (howHigh(game.callTrump()) > temp) {
                player4 = game.callTrump();
                temp = howHigh(game.callTrump());
                help = player4;
                toSee++;
            } else {
                player4 = "pass";
                toSee++;
            }
            if (player1.equals("pass") && player2.equals("pass") && player4.equals("pass") && !anonce.equals("pass")) {
                break;
            }
            if (player1.equals("pass") && player2.equals("pass") && !player4.equals("pass") && anonce.equals("pass")) {
                break;
            }
            if (player1.equals("pass") && !player2.equals("pass") && player4.equals("pass") && anonce.equals("pass")) {
                break;
            }
            if (!player1.equals("pass") && player2.equals("pass") && player4.equals("pass") && anonce.equals("pass")) {
                break;
            }

        }

        sb.append("You said:\n").append(anonce);
        sb.append("\nPlayer ").append(game.whoTurnIsIt(whoTurn)).append(" said:\n").append(player1);
        whoTurn++;
        sb.append("\nPlayer ").append(game.whoTurnIsIt(whoTurn)).append(" said:\n").append(player2);
        whoTurn++;
        sb.append("\nPlayer ").append(game.whoTurnIsIt(whoTurn)).append(" said:\n").append(player4);

        System.out.println(sb.toString());
        if (toSee == 4) {
            whoTurn++;
            cleanHand(players);
            System.out.println(players[2].getHand());
        } else {
            if (toSee == 3) {
                for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
                    game.dealThreeCards(players[i], deck1);
                }

                for (int j = 0; j < NUMBER_OF_PLAYERS; j++) {
                    /*
                    if (j % 2 == 0) {
                        teamOneAndTeamTwoPoints[0] = sayAnonces(players, j);
                        teamOneAndTeamTwoPoints[0] = fourCardAnonce(players, j);
                    } else {
                        teamOneAndTeamTwoPoints[1] = sayAnonces(players, j);
                        teamOneAndTeamTwoPoints[1] = fourCardAnonce(players, j);
                    }
                    */
                }
                teamOneAndTeamTwoPoints = game.returnBid(help, players, whoTurn);
            }
        }
        if (teamOneAndTeamTwoPoints[2] % 2 == 0) {
            if (teamOneAndTeamTwoPoints[0] > teamOneAndTeamTwoPoints[1]) {
                soutTeamPoint[0] += teamOneAndTeamTwoPoints[0];
                soutTeamPoint[1] += teamOneAndTeamTwoPoints[1];
            } else {
                soutTeamPoint[0] += 0;
                soutTeamPoint[1] += teamOneAndTeamTwoPoints[0] + teamOneAndTeamTwoPoints[1];
            }
        } else {
            if (teamOneAndTeamTwoPoints[0] < teamOneAndTeamTwoPoints[1]) {
                soutTeamPoint[0] += teamOneAndTeamTwoPoints[0];
                soutTeamPoint[1] += teamOneAndTeamTwoPoints[1];
            } else {
                soutTeamPoint[0] += teamOneAndTeamTwoPoints[0] + teamOneAndTeamTwoPoints[1];
                soutTeamPoint[1] += 0;
            }
        }

        return soutTeamPoint;
    }

    public void cleanHand(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            players[i].getHand().clear();
        }
    }

    public int sayAnonces(Player[] players, int i) {
        int anoncePoint = 0;
        for (int j = -1; j < players[i].getHand().size(); ) {
            j++;
            for (int k = j + 1; k < players[i].getHand().size(); k++) {
                if (players[i].getHand().get(j).getSuit() ==
                        players[j].getHand().get(j).getSuit()) {
                    if (RANK_NAMES[j] == RANK_NAMES[k - 1]) {
                        anoncePoint++;
                        j++;
                    }
                }
            }
        }
        if (anoncePoint != 0) {
            System.out.printf("Player[%d] says %d !!", i, anoncePoint);
        }
        return anoncePoint * 10;
    }


    public int fourCardAnonce(Player[] players, int i) {
        int temp = 0;
        for (int j = 0; j < players[i].getHand().size() - 3; j++) {
            temp = 0;
            for (int k = j + 1; k < players[i].getHand().size(); k++) {
                if (players[i].getHand().get(j).getValue() ==
                        players[i].getHand().get(k).getValue()) {
                    temp++;
                }
                if (temp == 4) {
                    temp = 100;
                    System.out.printf("Player[%d] says 100!!", i);
                    break;
                }
            }
        }
        return temp;
    }

    public int howHigh(String bid) {
        int temp = 0;
        for (int i = 0; i < TRUMP_NAMES.length; i++) {
            if (bid.equals(TRUMP_NAMES[i])) {
                temp = i;
                break;
            }
        }
        return temp;
    }
}
