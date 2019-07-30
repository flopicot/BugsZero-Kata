package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.bean.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    ArrayList<Player> players = new ArrayList();
    Player currentPlayer;

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    boolean isGettingOutOfPenaltyBox;

    public Game(List<Player> players) {
    	if (players == null || players.isEmpty() || players.size() < 2) {
    		throw new IllegalArgumentException("RTFGM");
		}

    	players.stream().forEach(player -> this.add(player));

    	this.currentPlayer = players.get(0);

        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    private boolean add(Player newPlayer) {

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
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
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
