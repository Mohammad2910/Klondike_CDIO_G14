package org.tensorflow.lite.examples.detection.logic;

import org.tensorflow.lite.examples.detection.DetectorActivity;


public class Controller {
    private Board board = Board.getInstance();
    private Logic logic = new Logic();


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

}

