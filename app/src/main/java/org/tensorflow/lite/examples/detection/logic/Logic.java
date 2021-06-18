package org.tensorflow.lite.examples.detection.logic;


import java.util.LinkedList;

public class Logic {

    private Board board;
    String[] movingCards = new String[2];
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
            movingCards[0] = board.getWastePile().getFirst().getTitle();
            movingCards[1] = possibleFoundationMove(board.getWastePile().getFirst());
            return true;
        }

        //checking if tableau card can be moved to the foundation
        for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
            if (possibleFoundationMove(tableauColumn.getFirst()) != null) {
                movingCards[0] = tableauColumn.getFirst().getTitle();
                movingCards[1] = possibleFoundationMove(tableauColumn.getFirst());
                return true;
            }
        }
        //checking if a wastepile card can be moved to the tableau
        if (possibleTableauMove(board.getWastePile().getFirst()) != null) {
            movingCards[0] = board.getWastePile().getFirst().getTitle();
            movingCards[1] = possibleTableauMove(board.getWastePile().getFirst());
            return true;
        }
        //checking if a tableau card can be moved to another tableau card.
        for (Card tableauCard : board.getHighestColumnCard()) {
            if (possibleTableauMove(tableauCard) != null) {
                movingCards[0] = tableauCard.getTitle();
                movingCards[1] = possibleTableauMove(tableauCard);
                return true;
            }
        }
        return false;
    }

    //
    public String possibleFoundationMove(Card cardToMove) {

        //Skal tjekke om der kan lægges et Es
        for (Card foundationCard : board.getFoundationsAll()) {
            if (foundationCard.getSuit().equals(cardToMove.getSuit()) && foundationCard.getRank() == cardToMove.getRank() - 1) {
                return foundationCard.getTitle();
            }
        }
        return null;
    }


    public String possibleTableauMove(Card cardToMove) {

        if (cardToMove.getSuit().equals("h") || cardToMove.getSuit().equals("d")) {
            for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
                if ((tableauColumn.getFirst().getSuit().equals("s") || tableauColumn.getFirst().getSuit().equals("c")) && tableauColumn.getFirst().getRank() + 1 == cardToMove.getRank()) {
                    return tableauColumn.getFirst().getTitle();
                }
            }
        } else {
            for (LinkedList<Card> tableauColumn : board.getTableauAll()) {
                if ((tableauColumn.getFirst().getSuit().equals("h") || tableauColumn.getFirst().getSuit().equals("d")) && tableauColumn.getFirst().getRank() + 1 == cardToMove.getRank()) {
                    return tableauColumn.getFirst().getTitle();
                }
            }
        }
        return null;
    }

    public String[] getMovingCards() {

//        if (checkAceDeuce()) {
//            return movingCards;
//        }
        if (anyMove()) {
            return movingCards;
        }
        return movingCards;
    }

}
