package org.tensorflow.lite.examples.detection.logic;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.tensorflow.lite.examples.detection.CameraActivity;
import org.tensorflow.lite.examples.detection.DetectorActivity;

import java.util.LinkedHashSet;
import java.util.LinkedList;


public class Controller {
    private Board board = Board.getInstance();
    private Logic logic = new Logic();
    private BoardSetup boardSetup;
    private CameraActivity cameraActivity;
    private SharedPreferences prefs;

    public Controller(CameraActivity cameraActivity) {
        this.cameraActivity = cameraActivity;
        prefs = PreferenceManager.getDefaultSharedPreferences(cameraActivity);
    }


    public void initBoardSetup(LinkedHashSet<String> cardsDetected, LinkedList<String> allCardsDetected){
        if(prefs.getBoolean("firstBoardSetup",true)){
            boardSetup = new BoardSetup(cardsDetected,allCardsDetected);
            Board.getInstance().setupBoard(boardSetup.makeCards());
            prefs.edit().putBoolean("firstBoardSetup",false).apply();
        } else{
            logic.getMovingCards();
        }
    }

    public void updateBoard() {

        if (logic.getValidMove().equals("tableauToFoundation")) {
            board.moveTableauToFoundation(logic.getMovingCards()[0], logic.getColumnFrom());
        }
        if (logic.getValidMove().equals("tableauToTableau")) {
            board.moveCardColumn(logic.getMovingCards()[0], logic.getColumnFrom(), logic.getColumnTo());
        }
        if (logic.getValidMove().equals("wastepileToFoundation")){
            board.moveWastepileToFoundation();
        }
        if (logic.getValidMove().equals("wastepileToTableau")){
            board.moveWastepileToTableau(logic.getMovingCards()[0], logic.getColumnTo());
        }

        if (logic.getValidMove().equals("flipCard")){
            // TODO: 20/06/2021
            //kald metoden "processImage" i DetectorActivity som så kan ud fra en if statement kalde på en metode i denne klasse der kalder board.flip(Card,logic.getColumnFrom));
            board.flipCardTableau(new Card("h",11,"11h"),logic.getColumnFrom());
        }
//        if (){
//            board.resetDrawpile();
//        }
        if (logic.getValidMove().equals("draw")){
            // TODO: 20/06/2021
            //kald metoden "processImage" i DetectorActivity som så kan ud fra en if statement kalde på en metode i denne klasse der kalder board.drawCard(Card);

            board.drawCard(new Card("h",9,"9h"));
            System.out.println(board.getWastePile().get(0).getTitle());
        }
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }
}

