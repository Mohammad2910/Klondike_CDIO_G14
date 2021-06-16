package org.tensorflow.lite.examples.detection.logic;

public class Card {
    private String suit;
    private String rank;
    private String title;

    public Card(String suit, String rank, String title) {
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
