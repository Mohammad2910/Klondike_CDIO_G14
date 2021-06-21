package org.tensorflow.lite.examples.detection.logic;

//

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
//

public class Board {
    private static Board instance = null;

    //Table Columns
    private ArrayList<LinkedList<Card>> tableauAll = new ArrayList<>();
    private LinkedList<Card> tableauC1 = new LinkedList<>();
    private LinkedList<Card> tableauC2 = new LinkedList<>();
    private LinkedList<Card> tableauC3 = new LinkedList<>();
    private LinkedList<Card> tableauC4 = new LinkedList<>();
    private LinkedList<Card> tableauC5 = new LinkedList<>();
    private LinkedList<Card> tableauC6 = new LinkedList<>();
    private LinkedList<Card> tableauC7 = new LinkedList<>();

    //Foundation Cards
    private ArrayList<Card> foundationsAll = new ArrayList<Card>();
    private Card foundation1 = new Card("h", 0, "Foundation");
    private Card foundation2 = new Card("s", 0, "Foundation");
    private Card foundation3 = new Card("c", 0, "Foundation");
    private Card foundation4 = new Card("d", 0, "Foundation");

    //Wastepile
    private LinkedList<Card> wastePile = new LinkedList<>();
    //Drawpile
    private LinkedList<Card> drawPile = new LinkedList<>();

    //BoardSetup
    private BoardSetup boardSetup;

    private Board() {

    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    /*
    --------------BRUG DISSE KORT--------------------

    Tableau C1 Indeholder: 10h
    Tableau C2 Indeholder: 5h
    Tableau C3 Indeholder: Bc
    Tableau C4 Indeholder: Ad
    Tableau C5 Indeholder: Kc
    Tableau C6 Indeholder: Ds
    Tableau C7 Indeholder: 7d

     */
    public void setupBoard(String[] finalCards) {
        ArrayList<Card> listOfCards = new ArrayList<>();
        String suit;
        int rank;
        for (int i = 0; i < finalCards.length; i++) {
            if(finalCards[i].length() == 2){
                suit = String.valueOf(finalCards[i].charAt(1));
                //Replacer bogstaver med " " for at få ranken i digits
                rank = Integer.parseInt(finalCards[i].replaceAll("[\\D]", ""));
                listOfCards.add(new Card(suit, rank, finalCards[i]));
            }
            if(finalCards[i].length() == 3){
                suit = String.valueOf(finalCards[i].charAt(2));
                rank = Integer.parseInt(finalCards[i].replaceAll("[\\D]", ""));
                listOfCards.add(new Card(suit, rank, finalCards[i]));
            }

        }

        //Drawpile
        for (int i = 0; i < 24; i++) {
            drawPile.add(new Card("", 0, "NA"));
        }

        //Foundations
        foundationsAll.add(foundation1);
        foundationsAll.add(foundation2);
        foundationsAll.add(foundation3);
        foundationsAll.add(foundation4);

        //Tableau 1
        tableauC1.add(listOfCards.get(0));

        //Tableau 2
        tableauC2.add(listOfCards.get(1));
        tableauC2.add(new Card("", 0, "NA"));

        //Tableau 3
        tableauC3.add(listOfCards.get(2));
        for (int i = 0; i < 2; i++) {
            tableauC3.add(new Card("", 0, "NA"));
        }

        //Tableau 4
        tableauC4.add(listOfCards.get(3));
        for (int i = 0; i < 3; i++) {
            tableauC4.add(new Card("", 0, "NA"));
        }

        //Tableau 5
        tableauC5.add(listOfCards.get(4));
        for (int i = 0; i < 4; i++) {
            tableauC5.add(new Card("", 0, "NA"));
        }

        //Tableau 6
        tableauC6.add(listOfCards.get(5));
        for (int i = 0; i < 5; i++) {
            tableauC6.add(new Card("", 0, "NA"));
        }

        //Tableau 7
        tableauC7.add(listOfCards.get(6));
        for (int i = 0; i < 6; i++) {
            tableauC7.add(new Card("", 0, "NA"));
        }

        tableauAll.add(tableauC1);
        tableauAll.add(tableauC2);
        tableauAll.add(tableauC3);
        tableauAll.add(tableauC4);
        tableauAll.add(tableauC5);
        tableauAll.add(tableauC6);
        tableauAll.add(tableauC7);

        System.out.println(
                "\n" + "Tableau C1 Indeholder: " + tableauC1.get(0).getTitle() + "\n" +
                        "Tableau C2 Indeholder: " + tableauC2.get(0).getTitle() + "\n" +
                        "Tableau C3 Indeholder: " + tableauC3.get(0).getTitle() + "\n" +
                        "Tableau C4 Indeholder: " + tableauC4.get(0).getTitle() + "\n" +
                        "Tableau C5 Indeholder: " + tableauC5.get(0).getTitle() + "\n" +
                        "Tableau C6 Indeholder: " + tableauC6.get(0).getTitle() + "\n" +
                        "Tableau C7 Indeholder: " + tableauC7.get(0).getTitle()
        );
    }

    public void drawCard(Card drawnCard) {
        wastePile.addFirst(drawnCard);
        drawPile.remove();
    }

    public void resetDrawpile() {
        int wastePileSize = wastePile.size();
        for (int i = 0; i < wastePileSize; i++) {
            drawPile.add(new Card("", 0, "NA"));
            wastePile.remove();
        }
    }

    public void moveTableauToFoundation(Card card, int columnNoFrom) {
        for (int i = 0; i < 4; i++) {
            if (card.getSuit().equals(foundationsAll.get(i).getSuit())) {
                foundationsAll.set(i, card);
                tableauAll.get(columnNoFrom).removeFirst();
                return;
            }
        }
    }

    public void moveWastepileToFoundation() {
        for (int i = 0; i < 4; i++) {
            if (wastePile.getFirst().getSuit().equals(foundationsAll.get(i).getSuit())) {
                foundationsAll.set(i, wastePile.getFirst());
                wastePile.removeFirst();
                return;
            }
        }

    }

    public void moveWastepileToTableau(Card card, int columnNoTo) {
        wastePile.removeFirst();
        tableauAll.get(columnNoTo).addFirst(card);
    }

    public void flipCardTableau(Card card, int columnNoFrom) {
        tableauAll.get(columnNoFrom).remove();
        tableauAll.get(columnNoFrom).addFirst(card);
    }

    public void moveCardColumn(Card card, int columnNoFrom, int columnNoTo) {
        ArrayList<Card> cardHolderList = new ArrayList<>();
        for (int i = 0; i < tableauAll.get(columnNoFrom).size(); i++) {
            cardHolderList.add(tableauAll.get(columnNoFrom).get(i));
            if (tableauAll.get(columnNoFrom).get(i) == card) {
                Collections.reverse(cardHolderList);
                for (int j = 0; j < i+1; j++) {
                    tableauAll.get(columnNoTo).addFirst(cardHolderList.get(j));
                    tableauAll.get(columnNoFrom).removeFirst();
                }
                return;
            }
        }
    }


    /*
     Getters
     */
    public LinkedList<Card> getHighestColumnCard(){
        LinkedList<Card> highestCards = new LinkedList<>();
        for (LinkedList<Card> columnCards : getTableauAll()) {
            for (int i = columnCards.size() - 1; i >= 0; i--) {
                if (!columnCards.get(i).getTitle().equals("NA")){
                    highestCards.add(columnCards.get(i));
                    i = -1;
                }
            }
        }
        return highestCards;
    }

    public ArrayList<LinkedList<Card>> getTableauAll() {
        return tableauAll;
    }

    public LinkedList<Card> getTableauC1() {
        return tableauC1;
    }

    public LinkedList<Card> getTableauC2() {
        return tableauC2;
    }

    public LinkedList<Card> getTableauC3() {
        return tableauC3;
    }

    public LinkedList<Card> getTableauC4() {
        return tableauC4;
    }

    public LinkedList<Card> getTableauC5() {
        return tableauC5;
    }

    public LinkedList<Card> getTableauC6() {
        return tableauC6;
    }

    public LinkedList<Card> getTableauC7() {
        return tableauC7;
    }

    public ArrayList<Card> getFoundationsAll() {
        return foundationsAll;
    }

    public Card getFoundation1() {
        return foundation1;
    }

    public Card getFoundation2() {
        return foundation2;
    }

    public Card getFoundation3() {
        return foundation3;
    }

    public Card getFoundation4() {
        return foundation4;
    }

    public LinkedList<Card> getWastePile() {
        return wastePile;
    }

    public LinkedList<Card> getDrawPile() {
        return drawPile;
    }

    public void printTableau(){
        for (LinkedList<Card> cards:tableauAll){
            System.out.println(cards.getFirst().getTitle());


        }
    }

}

