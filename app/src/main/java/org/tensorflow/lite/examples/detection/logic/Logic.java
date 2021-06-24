package org.tensorflow.lite.examples.detection.logic;


import java.util.LinkedList;

/**
 * Gruppe medlemmer:
 * Christian Merithz Uhrenfeldt Nielsen - s195480
 * David Lukas Mikkelsen - s147197
 * Johan Jens Kryger Larsen - s195491
 * Mohammad Tawrat Nafiu Uddin - s184174
 * Sander Eg Albeck Johansen - s195453
 */
public class Logic {

    private Board board;
    private static int columnFrom;
    private static int columnTo;
    private static Card[] movingCards = new Card[2];
    private static String validMove = "";
    private int noMoveCounter = 0;
    //    String[] cardSuits = {"S", "C", "H", "D"};

    public Logic() {
        this.board = Board.getInstance();
    }


    public boolean anyMove() {

        // TODO: 21/06/2021 Ryk konge move og lav at den checker for et win 

        //check if there are any face down cards in the tableau
        for (int i = 0; i < 7; i++) {
            if (!board.getTableauAll().get(i).isEmpty()) {
                if (board.getTableauAll().get(i).getFirst().getRank() == 0) {
                    columnFrom = i;
                    movingCards[0] = new Card("0", 0, "Flip");
                    movingCards[1] = new Card("0", 0, "Flip");
                    validMove = "flipCard";
                    noMoveCounter = 0;
                    return true;
                }
            }
        }

        //Check if wastepile is empty then draw a card
        if (board.getWastePile().isEmpty()) {
            movingCards[0] = new Card("0", 0, "Draw");
            movingCards[1] = new Card("0", 0, "Draw");
            validMove = "draw";
            noMoveCounter = 0;
            return true;
        }


        //checking if a wastepile card can be moved to the foundation
        if (possibleFoundationMove(board.getWastePile().getFirst()) != null) {
            movingCards[0] = board.getWastePile().getFirst();
            movingCards[1] = possibleFoundationMove(board.getWastePile().getFirst());
            validMove = "wastepileToFoundation";
            noMoveCounter = 0;
            return true;
        }

        //checking if tableau card can be moved to the foundation
        for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
            if (!tableauColumn.isEmpty()) {
                if (possibleFoundationMove(tableauColumn.getFirst()) != null) {
                    movingCards[0] = tableauColumn.getFirst();
                    movingCards[1] = possibleFoundationMove(tableauColumn.getFirst());
                    validMove = "tableauToFoundation";
                    noMoveCounter = 0;
                    return true;
                }
            }
        }
        //checking if a wastepile card can be moved to the tableau
        if (possibleTableauMove(board.getWastePile().getFirst()) != null) {
            movingCards[0] = board.getWastePile().getFirst();
            movingCards[1] = possibleTableauMove(board.getWastePile().getFirst());
            validMove = "wastepileToTableau";
            noMoveCounter = 0;
            return true;
        }
        //checking if a tableau card can be moved to another tableau card.
        for (Card tableauCard : board.getHighestColumnCard()) {
            if (possibleTableauMove(tableauCard) != null) {
                movingCards[0] = tableauCard;
                movingCards[1] = possibleTableauMove(tableauCard);
                validMove = "tableauToTableau";
                noMoveCounter = 0;
                return true;
            }
        }

        if (board.getDrawPile().isEmpty()) {
            movingCards[0] = new Card("0", 0, "Reset");
            movingCards[1] = new Card("0", 0, "Reset");
            validMove = "resetDrawpile";
            return true;
        }


        movingCards[0] = new Card("0", 0, "Draw");
        movingCards[1] = new Card("0", 0, "Draw");
        validMove = "draw";
        noMoveCounter++;
        if (noMoveCounter > board.getDrawPile().size()) {
            return false;
        }

        return true;

    }

    //
    public Card possibleFoundationMove(Card cardToMove) {

        //Skal tjekke om der kan lægges et Es
        for (Card foundationCard : board.getFoundationsAll()) {
            if (foundationCard.getSuit().equals(cardToMove.getSuit()) && foundationCard.getRank() == cardToMove.getRank() - 1) {
                return foundationCard;
            }
        }
        return null;
    }


    public Card possibleTableauMove(Card cardToMove) {

        if (cardToMove.getRank() == 13) {
            for (int i = 0; i < 7; i++) {
                if (!board.getTableauAll().get(i).isEmpty()) {
                    if (board.getTableauAll().get(i).getLast().getTitle().equals(cardToMove.getTitle())) {
                        return null;
                    }
                }
            }
            for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
                if (tableauColumn.isEmpty()) {
                    return new Card("emptytableau", 0, "tableau");
                }
            }
        }

        if (cardToMove.getSuit().equals("h") || cardToMove.getSuit().equals("d")) {
            for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
                if (!tableauColumn.isEmpty())
                    if ((tableauColumn.getFirst().getSuit().equals("s") || tableauColumn.getFirst().getSuit().equals("c")) && tableauColumn.getFirst().getRank() == cardToMove.getRank() + 1) {
                        return tableauColumn.getFirst();
                    }
            }
        } else {
            for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
                if (!tableauColumn.isEmpty())
                    if ((tableauColumn.getFirst().getSuit().equals("h") || tableauColumn.getFirst().getSuit().equals("d")) && tableauColumn.getFirst().getRank() == cardToMove.getRank() + 1) {
                        return tableauColumn.getFirst();
                    }
            }
        }
        return null;
    }

    public void boardInfo() {


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < board.getTableauAll().get(i).size(); j++) {
                if (movingCards[0] == board.getTableauAll().get(i).get(j)) {
                    columnFrom = i;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            if (movingCards[1].getSuit().equals("emptytableau") && board.getTableauAll().get(i).isEmpty()) {
                columnTo = i;
            }
            for (int j = 0; j < board.getTableauAll().get(i).size(); j++) {
                if (movingCards[1] == board.getTableauAll().get(i).get(j)) {
                    columnTo = i;
                }
            }
        }

    }

    public boolean isWon() {
        for (Card foundationCard : board.getFoundationsAll()) {
            if (!(foundationCard.getRank() == 13)) {
                return false;
            }
        }
        return true;
    }

    public Card[] getMovingCards() {


//        if (checkAceDeuce()) {
//            return movingCards;
//        }
        if (anyMove()) {
            boardInfo();
            return movingCards;
        }

        movingCards[0] = new Card("", 0, "lost");
        movingCards[1] = new Card("", 0, "lost");
        // TODO: 21/06/2021 Returns lost when there is no other posible move:
        return movingCards;
    }

    public int getColumnFrom() {
        return columnFrom;
    }

    public int getColumnTo() {
        return columnTo;
    }

    public String getValidMove() {
        return validMove;
    }
}
