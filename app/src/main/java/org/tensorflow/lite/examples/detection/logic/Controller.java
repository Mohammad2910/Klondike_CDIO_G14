package org.tensorflow.lite.examples.detection.logic;

public class Controller {
    private Board board = Board.getInstance();
    private Logic logic;

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
//        if (){
//            board.flipCardTableau();
//        }
//        if (){
//            board.resetDrawpile();
//        }
//        if (){
//            board.drawCard();
//        }
    }

}
