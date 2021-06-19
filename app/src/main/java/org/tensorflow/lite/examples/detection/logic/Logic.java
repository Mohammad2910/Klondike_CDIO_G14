package org.tensorflow.lite.examples.detection.logic;


import java.util.LinkedList;

public class Logic {

    private Board board;
    private static int columnFrom;
    private static int columnTo;
    private static Card[] movingCards = new Card[2];
    private static String validMove = "";

    //    String[] cardSuits = {"S", "C", "H", "D"};

    public Logic() {
        this.board = Board.getInstance();
    }



    public boolean anyMove() {

        // TODO: 20/06/2021 Den går i loop hvis den kan rykke mellem 2 kort f.eks. 2h kan rykke mellem 3s og 3c så den rykker frem og tilbage konstant.
        //kan måske løses ved at sige at den ikke skal rykke et kort hvis den allerede ligger på et ikke facedown kort.

        //check if there are any face down cards in the tableau
        for (int i = 0; i < 7; i++) {
            if(!board.getTableauAll().get(i).isEmpty()){
                if (board.getTableauAll().get(i).getFirst().getRank() == 0) {
                    columnFrom = i;
                    movingCards[0] = new Card("0",0,"Flip");
                    movingCards[1] = new Card("0",0,"Flip");
                    validMove ="flipCard";
                    return true;
                }
            }
        }

        //Check if wastepile is empty then draw a card
        if(board.getWastePile().isEmpty()){
            movingCards[0] = new Card("0",0,"Draw");
            movingCards[1] = new Card("0",0,"Draw");
            validMove = "draw";
            return true;
        }


        //checking if a wastepile card can be moved to the foundation
        if (possibleFoundationMove(board.getWastePile().getFirst()) != null) {
            movingCards[0] = board.getWastePile().getFirst();
            movingCards[1] = possibleFoundationMove(board.getWastePile().getFirst());
            validMove = "wastepileToFoundation";
            return true;
        }

        //checking if tableau card can be moved to the foundation
        for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
            if(!tableauColumn.isEmpty()){
                if (possibleFoundationMove(tableauColumn.getFirst()) != null) {
                    movingCards[0] = tableauColumn.getFirst();
                    movingCards[1] = possibleFoundationMove(tableauColumn.getFirst());
                    validMove ="tableauToFoundation";
                    return true;
                }
            }
        }
        //checking if a wastepile card can be moved to the tableau
        if (possibleTableauMove(board.getWastePile().getFirst()) != null) {
            movingCards[0] = board.getWastePile().getFirst();
            movingCards[1] = possibleTableauMove(board.getWastePile().getFirst());
            validMove = "wastepileToTableau";
            return true;
        }
        //checking if a tableau card can be moved to another tableau card.
        for (Card tableauCard : board.getHighestColumnCard()) {
            if (possibleTableauMove(tableauCard) != null) {
                movingCards[0] = tableauCard;
                movingCards[1] = possibleTableauMove(tableauCard);
                validMove = "tableauToTableau";
                return true;
            }
        }
        validMove = "";
        return false;
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
        if (cardToMove.getSuit().equals("h") || cardToMove.getSuit().equals("d")) {
            for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
                if(!tableauColumn.isEmpty())
                    if ((tableauColumn.getFirst().getSuit().equals("s") || tableauColumn.getFirst().getSuit().equals("c")) && tableauColumn.getFirst().getRank() == cardToMove.getRank() +1) {
                        return tableauColumn.getFirst();
                    }
            }
        } else {
            for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
                if(!tableauColumn.isEmpty())
                    if ((tableauColumn.getFirst().getSuit().equals("h") || tableauColumn.getFirst().getSuit().equals("d")) && tableauColumn.getFirst().getRank() == cardToMove.getRank() +1) {
                        return tableauColumn.getFirst();
                    }
            }
        }
        return null;
    }

    public void boardInfo(){


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < board.getTableauAll().get(i).size(); j++) {
                if (movingCards[0] == board.getTableauAll().get(i).get(j)){
                    columnFrom = i;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < board.getTableauAll().get(i).size(); j++) {
                if (movingCards[1] == board.getTableauAll().get(i).get(j)){
                    columnTo = i;
                }
            }
        }

    }

    public Card[] getMovingCards() {


//        if (checkAceDeuce()) {
//            return movingCards;
//        }
        if (anyMove()) {
            boardInfo();
            return movingCards;
        }
        movingCards[0] = null;
        movingCards[1] = null;

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
