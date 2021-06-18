package org.tensorflow.lite.examples.detection.logic;


import java.util.LinkedList;

public class Logic {

    private Board board;
    private int columnFrom;
    private int columnTo;
    private Card[] movingCards = new Card[2];
    private String validMove = "";

    //    String[] cardSuits = {"S", "C", "H", "D"};

    public Logic() {
        this.board = Board.getInstance();
    }


//    public boolean checkAceDeuce() {


////        for (Card card:board.getFirstCard()) {
////            for (int i = 0; i < 4; i++) {
////                if (card.getTitle().equals(cardSuits[i] + "1")){
////                    movingCards[0] = cardSuits[i] + "1";
////                    movingCards[1] = "Foundation";
////                    return true;
////                }
////            }
////        }
////        for (Card card:board.getFirstCard()) {
////            for (int i = 0; i < 4; i++) {
////                if (card.getTitle().equals(cardSuits[i] + "2")){
////                    movingCards[0] = cardSuits[i] + "2";
////                    for (int j = 0; j < 4; j++) {
////                        if((cardSuits[i] + "1").equals(board.getFoundations().get(j).getTitle())){
////                            movingCards[1] = cardSuits[i] + "1";
////                            return true;
////                        }
////                    }
////                    for (Card card1:board.getFirstCard()) {
////                        if((cardSuits[i] + "1").equals(card1.getTitle())){
////                            movingCards[1] = cardSuits[i] + "1";
////                            return true;
////                        }
////                    }
////                }
////            }
////        }
//
//        return false;
//
//    }

    public boolean anyMove() {

        //checking if a wastepile card can be moved to the foundation
        if (possibleFoundationMove(board.getWastePile().getFirst()) != null) {
            movingCards[0] = board.getWastePile().getFirst();
            movingCards[1] = possibleFoundationMove(board.getWastePile().getFirst());
            validMove = "wastepileToFoundation";
            return true;
        }

        //checking if tableau card can be moved to the foundation
        for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
            if (possibleFoundationMove(tableauColumn.getFirst()) != null) {
                movingCards[0] = tableauColumn.getFirst();
                movingCards[1] = possibleFoundationMove(tableauColumn.getFirst());
                validMove ="tableauToFoundation";
                return true;
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
        // TODO: 18-06-2021 Tilføj ryk af konge til tom plads
        if (cardToMove.getSuit().equals("h") || cardToMove.getSuit().equals("d")) {
            for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
                if ((tableauColumn.getFirst().getSuit().equals("s") || tableauColumn.getFirst().getSuit().equals("c")) && tableauColumn.getFirst().getRank() + 1 == cardToMove.getRank()) {
                    return tableauColumn.getFirst();
                }
            }
        } else {
            for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
                if ((tableauColumn.getFirst().getSuit().equals("h") || tableauColumn.getFirst().getSuit().equals("d")) && tableauColumn.getFirst().getRank() + 1 == cardToMove.getRank()) {
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
