package org.tensorflow.lite.examples.detection.logic;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class LogicTest {



    @Test
    public void possibleFoundationMove() {
        LinkedHashSet<String> cardsDetected = new LinkedHashSet<>();
        LinkedList<String> allCardsDetected = new LinkedList<>();

        cardsDetected.add("1s");
        cardsDetected.add("2s");
        cardsDetected.add("3s");
        cardsDetected.add("4s");
        cardsDetected.add("6s");
        cardsDetected.add("7s");
        cardsDetected.add("8s");

        allCardsDetected.add("1s");
        allCardsDetected.add("2s");
        allCardsDetected.add("3s");
        allCardsDetected.add("4s");
        allCardsDetected.add("6s");
        allCardsDetected.add("7s");
        allCardsDetected.add("8s");

        BoardSetup boardSetup = new BoardSetup();
        Board.getInstance().setupBoard(boardSetup.makeCards(cardsDetected, allCardsDetected));

        Logic logic = new Logic();
        Card expected = new Card("s", 0, "Foundation");
        Card actual = logic.possibleFoundationMove(new Card("s",1,"1s"));

        assertEquals(expected.getTitle(), actual.getTitle() );



    }
}