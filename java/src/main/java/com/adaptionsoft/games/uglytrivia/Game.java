package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.bean.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList<Player> players;
    Player currentPlayer;

    LinkedList<PopQuestion> popQuestions = new LinkedList();
    LinkedList<ScienceQuestion> scienceQuestions = new LinkedList();
    LinkedList<SportsQuestion> sportsQuestions = new LinkedList();
    LinkedList<RockQuestion> rockQuestions = new LinkedList();

    boolean isGettingOutOfPenaltyBox;

    public Game(Player player1, Player player2) {
        players = new ArrayList();
        this.add(player1);
        this.add(player2);
        this.currentPlayer = player1;

        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(new PopQuestion("Pop Question " + i));
            scienceQuestions.addLast(new ScienceQuestion("Science Question " + i));
            sportsQuestions.addLast(new SportsQuestion("Sports Question " + i));
            rockQuestions.addLast(new RockQuestion("Rock Question " + i));
        }
    }

    public Game(Player player1, Player player2, Player player3) {
        this(player1,player2);
        this.add(player3);
    }

    public Game(Player player1, Player player2, Player player3, Player player4) {
        this(player1,player2,player3);
        this.add(player4);
    }

    public Game(Player player1, Player player2, Player player3, Player player4, Player player5) {
        this(player1,player2,player3,player4);
        this.add(player5);
    }

    public Game(Player player1, Player player2, Player player3, Player player4, Player player5, Player player6) {
        this(player1,player2,player3,player4,player5);
        this.add(player6);
    }


    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    private final boolean add(Player newPlayer) {

        players.add(newPlayer);

        System.out.println(newPlayer.getName() + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                movePlayerAndAskQuestion(roll);
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            movePlayerAndAskQuestion(roll);
        }

    }

    private void movePlayerAndAskQuestion(int roll) {
        currentPlayer.moveForward(roll);

        System.out.println(currentPlayer.getName()
                + "'s new location is "
                + currentPlayer.getPlace());
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.removeFirst().getTitle());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst().getTitle());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst().getTitle());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst().getTitle());
    }


    private String currentCategory() {
        Player player = currentPlayer;
        if (player.getPlace() == 0) return "Pop";
        if (player.getPlace() == 4) return "Pop";
        if (player.getPlace() == 8) return "Pop";
        if (player.getPlace() == 1) return "Science";
        if (player.getPlace() == 5) return "Science";
        if (player.getPlace() == 9) return "Science";
        if (player.getPlace() == 2) return "Sports";
        if (player.getPlace() == 6) return "Sports";
        if (player.getPlace() == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
				nextPlayer();
				currentPlayer.addCoinInPurse();
                System.out.println(currentPlayer.getName()
                        + " now has "
                        + currentPlayer.getPurse()
                        + " Gold Coins.");

                boolean winner = didPlayerWin();

                return winner;
            } else {
				nextPlayer();
				return true;
            }


        } else {

            System.out.println("Answer was corrent!!!!");
            currentPlayer.addCoinInPurse();
            System.out.println(currentPlayer.getName()
                    + " now has "
                    + currentPlayer.getPurse()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
			nextPlayer();

			return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
		currentPlayer.placeInPernaltyBox();

		nextPlayer();
		return true;
    }

	private void nextPlayer() {
    	if(currentPlayer == null) {
    		throw new IllegalStateException("Current player is not defined");
		}

    	int currentPlayerIndex = players.indexOf(currentPlayer);
    	if(currentPlayerIndex == players.size() - 1) {
    		currentPlayer = players.get(0);
		} else {
			currentPlayer = players.get(currentPlayerIndex + 1);
		}
	}


	private boolean didPlayerWin() {
        return !(currentPlayer.getPurse() == 6);
    }
}
