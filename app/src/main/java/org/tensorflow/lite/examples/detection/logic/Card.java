package org.tensorflow.lite.examples.detection.logic;

public class Card {
    private String suit;
    private int rank;
    private String title;

    public Card(String suit, int rank, String title) {
        this.suit = suit;
        this.rank = rank;
        this.title = title;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

