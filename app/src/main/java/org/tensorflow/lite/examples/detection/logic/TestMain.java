package org.tensorflow.lite.examples.detection.logic;


import java.util.LinkedHashSet;
import java.util.LinkedList;

public class TestMain {
    public static LinkedHashSet<String> cardsDetected = new LinkedHashSet<String>();
    public static LinkedList<String> allCardsDetected = new LinkedList<String>();

//    public static void main(String[] args){
//        BoardSetup boardSetup;
//        Logic logic = new Logic();
//
//
//
//        allCardsDetected.add("9c");
//        allCardsDetected.add("2h");
//        allCardsDetected.add("5c");
//        allCardsDetected.add("7d");
//        allCardsDetected.add("3s");
//        allCardsDetected.add("4h");
//        allCardsDetected.add("3c");
//
//
//        cardsDetected.add("9c");
//        cardsDetected.add("2h");
//        cardsDetected.add("5c");
//        cardsDetected.add("7d");
//        cardsDetected.add("3s");
//        cardsDetected.add("4h");
//        cardsDetected.add("3c");
//
//
//        boardSetup = new BoardSetup(cardsDetected,allCardsDetected);
//        Board.getInstance().setupBoard(boardSetup.makeCards());
//
//        Board.getInstance().printTableau();
//        System.out.println("-------------------------");
//
//        Controller controller = new Controller();
//
//        Card[] movingCards = new Card[2];
//        for (int j = 0; j < 15; j++) {
//
//            movingCards = logic.getMovingCards();
//            for (int i = 0; i < 2; i++) {
//                if(movingCards[i] != null)
//                    System.out.println(movingCards[i].getTitle());
//
//            }
//            controller.updateBoard();
//        }
//    }
}

