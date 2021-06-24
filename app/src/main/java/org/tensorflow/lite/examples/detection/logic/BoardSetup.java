package org.tensorflow.lite.examples.detection.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Gruppe medlemmer:
 * Christian Merithz Uhrenfeldt Nielsen - s195480
 * David Lukas Mikkelsen - s147197
 * Johan Jens Kryger Larsen - s195491
 * Mohammad Tawrat Nafiu Uddin - s184174
 * Sander Eg Albeck Johansen - s195453
 */
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
