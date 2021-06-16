package org.tensorflow.lite.examples.detection.logic;

public class Logic {

    Board board;
    String[] movingCards = new String[2];
    String[] cardSuits = {"S", "C", "H", "D"};

    public Logic(Board board) {
        this.board = board;

    }


    public boolean checkAceDeuce() {

//
//        for (Card card:board.getFirstCard()) {
//            for (int i = 0; i < 4; i++) {
//                if (card.getTitle().equals(cardSuits[i] + "1")){
//                    movingCards[0] = cardSuits[i] + "1";
//                    movingCards[1] = "Foundation";
//                    return true;
//                }
//            }
//        }
//        for (Card card:board.getFirstCard()) {
//            for (int i = 0; i < 4; i++) {
//                if (card.getTitle().equals(cardSuits[i] + "2")){
//                    movingCards[0] = cardSuits[i] + "2";
//                    for (int j = 0; j < 4; j++) {
//                        if((cardSuits[i] + "1").equals(board.getFoundations().get(j).getTitle())){
//                            movingCards[1] = cardSuits[i] + "1";
//                            return true;
//                        }
//                    }
//                    for (Card card1:board.getFirstCard()) {
//                        if((cardSuits[i] + "1").equals(card1.getTitle())){
//                            movingCards[1] = cardSuits[i] + "1";
//                            return true;
//                        }
//                    }
//                }
//            }
//        }

        return false;

    }

    public boolean anyMove() {

        //checking if a wastepile card can be moved to the foundation
        if (possibleFoundationMove(board.getWastepileCard().getTitle()) != null) {
            movingCards[0] = board.getWastepileCard().getTitle();
            movingCards[1] = possibleFoundationMove(board.getWastepileCard().getTitle());
            return true;
        }

        //checking if tableau card can be moved to the foundation
        for (Card tableauCard : board.getFirstCard()) {
            if (possibleFoundationMove(tableauCard.getTitle()) != null) {
                movingCards[0] = tableauCard.getTitle();
                movingCards[1] = possibleFoundationMove(tableauCard.getTitle());
                return true;
            }
        }
        //checking if a wastepile card can be moved to the tableau
        if (possibleTableauMove(board.getWastepileCard().getTitle()) != null) {
            movingCards[0] = board.getWastepileCard().getTitle();
            movingCards[1] = possibleTableauMove(board.getWastepileCard().getTitle());
            return true;
        }
        //checking if a tableau card can be moved to another tableau card.
        for (Card tableauCard : board.getLastCards()) {
            if (possibleTableauMove(tableauCard.getTitle()) != null) {
                movingCards[0] = tableauCard.getTitle();
                movingCards[1] = possibleTableauMove(tableauCard.getTitle());
                return true;
            }
        }

        return false;
    }

    public String possibleFoundationMove(String cardToMove) {


        if (cardToMove.charAt(1) == '1' && cardToMove.length() == 2) {
            for (Card foundationCards : board.getFoundations()) {
                if (foundationCards.getTitle() == "null") {
                    return "emptyFoundation";
                }
            }
        }
        int cardNumber;
        if (cardToMove.length() == 3) {
            cardNumber = Integer.parseInt(String.valueOf(cardToMove.charAt(1)) + String.valueOf(cardToMove.charAt(2)));
        } else {
            cardNumber = Integer.parseInt(String.valueOf(cardToMove.charAt(1)));
        }
        for (Card foundationCards : board.getFoundations()) {
            if (foundationCards.getTitle().equals(String.valueOf(cardToMove.charAt(0)) + (cardNumber - 1))) {

                return foundationCards.getTitle();
            }
        }

        return null;
    }

    public String possibleTableauMove(String cardToMove) {
        String color;
        int cardNumber;

        if (cardToMove.contains("S") || cardToMove.contains("C")) {
            color = "black";
        } else {
            color = "red";
        }

        cardNumber = Integer.parseInt(String.valueOf(cardToMove.charAt(1)));

        if (color.equals("red")) {
            for (Card tableauCards : board.getFirstCard()) {
                if (tableauCards.getTitle().equals("S" + (cardNumber + 1)) || tableauCards.getTitle().equals("C" + (cardNumber + 1))) {
                    return tableauCards.getTitle();
                }
            }
        }
        if (color.equals("black")) {
            for (Card tableauCards : board.getFirstCard()) {
                if (tableauCards.getTitle().equals("H" + (cardNumber + 1)) || tableauCards.getTitle().equals("D" + (cardNumber + 1))) {
                    return tableauCards.getTitle();
                }
            }
        }
        return null;
    }


    public String[] getMovingCards() {

        if (checkAceDeuce()) {
            return movingCards;
        }
        if (anyMove()) {
            return movingCards;
        }


        return movingCards;
    }
}
