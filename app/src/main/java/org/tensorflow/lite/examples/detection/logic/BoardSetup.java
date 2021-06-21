package org.tensorflow.lite.examples.detection.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class BoardSetup {

    public BoardSetup() {

    }

    public String[] makeCards(LinkedHashSet<String> cardsDetected, LinkedList<String> allCardsDetected) {
        ArrayList<Integer> cardOccurence = new ArrayList<Integer>();
        for (String card : cardsDetected) {
            cardOccurence.add(Collections.frequency(allCardsDetected, card));
        }
        int maxOfCard;
        int indexOfFinalCards[] = new int[7];

        for (int i = 0; i < 7; i++) {
            maxOfCard = Collections.max(cardOccurence);
            indexOfFinalCards[i] = cardOccurence.indexOf(maxOfCard);
            cardOccurence.set(indexOfFinalCards[i], 0);
        }
        String[] finalCards = new String[7];
        Arrays.sort(indexOfFinalCards);
        ArrayList<String> allCardsArray = new ArrayList<>(cardsDetected);
        for (int i = 0; i < 7; i++) {
            finalCards[i] = allCardsArray.get(indexOfFinalCards[i]);
        }
        return finalCards;
    }

}
