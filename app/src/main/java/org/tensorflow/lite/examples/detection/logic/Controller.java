package org.tensorflow.lite.examples.detection.logic;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.tensorflow.lite.examples.detection.CameraActivity;
import org.tensorflow.lite.examples.detection.DetectorActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;


public class Controller {
    private Board board = Board.getInstance();
    private Logic logic = new Logic();
    private BoardSetup boardSetup;
    private CameraActivity cameraActivity;
    private SharedPreferences prefs;
    private String validMove;
    private boolean firstRun = true;

    public Controller(CameraActivity cameraActivity) {
        this.cameraActivity = cameraActivity;
        prefs = PreferenceManager.getDefaultSharedPreferences(cameraActivity);
    }


    public void initBoardSetup(LinkedHashSet<String> cardsDetected, LinkedList<String> allCardsDetected) {
        boardSetup = new BoardSetup();
        Board.getInstance().setupBoard(boardSetup.makeCards(cardsDetected, allCardsDetected));
    }

    public Card scanNewCard(LinkedHashSet<String> cardsDetected, LinkedList<String> allCardsDetected){

        // TODO: 21/06/2021 lav måske til en metode der gælder for all mængder af kort
        Card returnCard = new Card("",0,"");
        ArrayList<Integer> cardOccurence = new ArrayList<Integer>();
        for (String card : cardsDetected) {
            cardOccurence.add(Collections.frequency(allCardsDetected, card));
        }
        int maxOfCard;
        int[] indexOfFinalCards = new int[1];

        for (int i = 0; i < 1; i++) {
            maxOfCard = Collections.max(cardOccurence);
            indexOfFinalCards[i] = cardOccurence.indexOf(maxOfCard);
            cardOccurence.set(indexOfFinalCards[i], 0);
        }
        String[] finalCards = new String[1];
        Arrays.sort(indexOfFinalCards);
        ArrayList<String> allCardsArray = new ArrayList<>(cardsDetected);
        for (int i = 0; i < 1; i++) {
            finalCards[i] = allCardsArray.get(indexOfFinalCards[i]);
        }

        String suit;
        int rank;
        for (int i = 0; i < 1; i++) {
            if(finalCards[i].length() == 2){
                suit = String.valueOf(finalCards[i].charAt(1));
                //Replacer bogstaver med " " for at få ranken i digits
                rank = Integer.parseInt(finalCards[i].replaceAll("[\\D]", ""));
                returnCard = new Card(suit, rank, finalCards[i]);
            }
            if(finalCards[i].length() == 3){
                suit = String.valueOf(finalCards[i].charAt(2));
                rank = Integer.parseInt(finalCards[i].replaceAll("[\\D]", ""));
                returnCard = new Card(suit, rank, finalCards[i]);
            }

        }

        return returnCard;
    }

    public void updateBoard(Card scannedCard) {

        if (logic.getValidMove().equals("tableauToFoundation")) {
            board.moveTableauToFoundation(logic.getMovingCards()[0], logic.getColumnFrom());
        }
        if (logic.getValidMove().equals("tableauToTableau")) {
            board.moveCardColumn(logic.getMovingCards()[0], logic.getColumnFrom(), logic.getColumnTo());
        }
        if (logic.getValidMove().equals("wastepileToFoundation")) {
            board.moveWastepileToFoundation();
        }
        if (logic.getValidMove().equals("wastepileToTableau")) {
            board.moveWastepileToTableau(logic.getMovingCards()[0], logic.getColumnTo());
        }

        if (logic.getValidMove().equals("flipCard")) {
            // TODO: 20/06/2021
            //kald metoden "processImage" i DetectorActivity som så kan ud fra en if statement kalde på en metode i denne klasse der kalder board.flip(Card,logic.getColumnFrom));
            board.flipCardTableau(scannedCard, logic.getColumnFrom());
        }
        if (logic.getValidMove().equals("resetDrawpile")){
            board.resetDrawpile();
        }
        if (logic.getValidMove().equals("draw")) {
            // TODO: 20/06/2021
            //kald metoden "processImage" i DetectorActivity som så kan ud fra en if statement kalde på en metode i denne klasse der kalder board.drawCard(Card);

            board.drawCard(scannedCard);
            System.out.println(board.getWastePile().get(0).getTitle());
        }
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public String getValidMove() {
        return validMove;
    }

    public Logic getLogic() {
        return logic;
    }
}

