package org.tensorflow.lite.examples.detection.logic;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class BoardSetupTest {

    @Test
    public void makeCards() {
        LinkedHashSet<String> cardsDetected = new LinkedHashSet<>();
        LinkedList<String> allCardsDetected = new LinkedList<>();

        cardsDetected.add("a");
        cardsDetected.add("h");
        cardsDetected.add("g");
        cardsDetected.add("t");
        cardsDetected.add("d");
        cardsDetected.add("e");
        cardsDetected.add("s");
        cardsDetected.add("c");

        allCardsDetected.add("a");
        allCardsDetected.add("a");
        allCardsDetected.add("a");
        allCardsDetected.add("h");
        allCardsDetected.add("h");
        allCardsDetected.add("h");
        allCardsDetected.add("g");
        allCardsDetected.add("g");
        allCardsDetected.add("g");
        allCardsDetected.add("t");
        allCardsDetected.add("t");
        allCardsDetected.add("t");
        allCardsDetected.add("d");
        allCardsDetected.add("d");
        allCardsDetected.add("d");
        allCardsDetected.add("e");
        allCardsDetected.add("s");
        allCardsDetected.add("s");
        allCardsDetected.add("s");
        allCardsDetected.add("s");
        allCardsDetected.add("c");
        allCardsDetected.add("c");
        allCardsDetected.add("c");


        BoardSetup boardSetup = new BoardSetup();
        String[] expected = {"a","h","g","t","d","s","c"};

        assertArrayEquals(expected,boardSetup.makeCards(cardsDetected,allCardsDetected));
    }
}