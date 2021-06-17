//package org.tensorflow.lite.examples.detection.logic;
//
//
//import sun.awt.image.ImageWatched;
//
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.LinkedList;
//
//public class Board {
//    int counter = 1;
//    public LinkedList<Card> wastepile = new LinkedList<>();
//    public LinkedList<Card> tableauC1 = new LinkedList<>();
//    public LinkedList<Card> tableauC2 = new LinkedList<>();
//    public LinkedList<Card> tableauC3 = new LinkedList<>();
//    public LinkedList<Card> tableauC4 = new LinkedList<>();
//    public LinkedList<Card> tableauC5 = new LinkedList<>();
//    public LinkedList<Card> tableauC6 = new LinkedList<>();
//    public LinkedList<Card> tableauC7 = new LinkedList<>();
////    public Card foundation1 = new Card("null", 0, 0, 0, 0);
////    public Card foundation2 = new Card("null", 0, 0, 0, 0);
////    public Card foundation3 = new Card("null", 0, 0, 0, 0);
////    public Card foundation4 = new Card("null", 0, 0, 0, 0);
//
//    public LinkedList<Card> cards;
//
//    public Board(LinkedList<Card> cards) {
//        this.cards = cards;
//
//
//        for (Card card : cards) {
//            if ((card.left + card.right) / 2 <= 267 && (card.top + card.bottom) / 2 >= 187) {
//                wastepile.add(card);
//                continue;
//            }
//            if ((card.left + card.right) / 2 >= 267 && (card.top + card.bottom) / 2 <= 187) {
//                switch (counter) {
//                    case 1:
//                        foundation1 = card;
//                        break;
//                    case 2:
//                        foundation2 = card;
//                        break;
//                    case 3:
//                        foundation3 = card;
//                        break;
//                    case 4:
//                        foundation4 = card;
//                        break;
//                    default:
//                        break;
//                }
//                counter++;
//                continue;
//            }
//            if ((card.left + card.right) / 2 >= 267 && (card.top + card.bottom) / 2 >= 187) {
//                if (tableauC1.isEmpty()) {
//                    tableauC1.add(card);
//                    continue;
//                }
//                if (tableauC1.getLast().left <= (card.left + card.right) / 2 && (card.left + card.right) / 2 <= tableauC1.getLast().getRight()) {
//                    tableauC1.add(card);
//                    continue;
//                }
//                if (tableauC2.isEmpty()) {
//                    tableauC2.add(card);
//                    continue;
//                }
//                if (tableauC2.getLast().left <= (card.left + card.right) / 2 && (card.left + card.right) / 2 <= tableauC2.getLast().getRight()) {
//                    tableauC2.add(card);
//                    continue;
//                }
//                if (tableauC3.isEmpty()) {
//                    tableauC3.add(card);
//                    continue;
//                }
//                if (tableauC3.getLast().left <= (card.left + card.right) / 2 && (card.left + card.right) / 2 <= tableauC3.getLast().getRight()) {
//                    tableauC3.add(card);
//                    continue;
//                }
//                if (tableauC4.isEmpty()) {
//                    tableauC4.add(card);
//                    continue;
//                }
//                if (tableauC4.getLast().left <= (card.left + card.right) / 2 && (card.left + card.right) / 2 <= tableauC4.getLast().getRight()) {
//                    tableauC4.add(card);
//                    continue;
//                }
//                if (tableauC5.isEmpty()) {
//                    tableauC5.add(card);
//                    continue;
//                }
//                if (tableauC5.getLast().left <= (card.left + card.right) / 2 && (card.left + card.right) / 2 <= tableauC5.getLast().getRight()) {
//                    tableauC5.add(card);
//                    continue;
//                }
//                if (tableauC6.isEmpty()) {
//                    tableauC6.add(card);
//                    continue;
//                }
//                if (tableauC6.getLast().left <= (card.left + card.right) / 2 && (card.left + card.right) / 2 <= tableauC6.getLast().getRight()) {
//                    tableauC6.add(card);
//                    continue;
//                }
//                if (tableauC7.isEmpty()) {
//                    tableauC7.add(card);
//                    continue;
//                }
//                if (tableauC7.getLast().left <= (card.left + card.right) / 2 && (card.left + card.right) / 2 <= tableauC7.getLast().getRight()) {
//                    tableauC7.add(card);
//                    continue;
//                }
//            }
//        }
//
//
//        sort(tableauC1);
//        sort(tableauC2);
//        sort(tableauC3);
//        sort(tableauC4);
//        sort(tableauC5);
//        sort(tableauC6);
//        sort(tableauC7);
//        sort(wastepile);
//
//
//    }
//
//    //last cards meaning the cards at the back of the column stack
//    public LinkedList<Card> getLastCards() {
//        LinkedList<Card> lastCards = new LinkedList<>();
//        if (!tableauC1.isEmpty()) {
//            lastCards.add(tableauC1.getLast());
//        }
//        if (!tableauC2.isEmpty()) {
//            lastCards.add(tableauC2.getLast());
//
//        }
//        if (!tableauC3.isEmpty()) {
//            lastCards.add(tableauC3.getLast());
//
//        }
//        if (!tableauC4.isEmpty()) {
//            lastCards.add(tableauC4.getLast());
//
//        }
//        if (!tableauC5.isEmpty()) {
//            lastCards.add(tableauC5.getLast());
//
//        }
//        if (!tableauC6.isEmpty()) {
//            lastCards.add(tableauC6.getLast());
//
//        }
//        if (!tableauC7.isEmpty()) {
//            lastCards.add(tableauC7.getLast());
//
//        }
//
//        return lastCards;
//    }
//
//    //first cards meaning the cards at the front of the column stack
//    public LinkedList<Card> getFirstCard() {
//        LinkedList<Card> firstCards = new LinkedList<>();
//        if (!tableauC1.isEmpty()) {
//            firstCards.add(tableauC1.getFirst());
//        }
//        if (!tableauC2.isEmpty()) {
//            firstCards.add(tableauC2.getFirst());
//
//        }
//        if (!tableauC3.isEmpty()) {
//            firstCards.add(tableauC3.getFirst());
//
//        }
//        if (!tableauC4.isEmpty()) {
//            firstCards.add(tableauC4.getFirst());
//
//        }
//        if (!tableauC5.isEmpty()) {
//            firstCards.add(tableauC5.getFirst());
//
//        }
//        if (!tableauC6.isEmpty()) {
//            firstCards.add(tableauC6.getFirst());
//
//        }
//        if (!tableauC7.isEmpty()) {
//            firstCards.add(tableauC7.getFirst());
//
//        }
//
//        return firstCards;
//    }
//
//    public LinkedList<Card> getFoundations() {
//        LinkedList<Card> foundations = new LinkedList<>();
//        foundations.add(foundation1);
//        foundations.add(foundation2);
//        foundations.add(foundation3);
//        foundations.add(foundation4);
//
//
//        return foundations;
//    }
//
//
//    public Card getWastepileCard() {
//        return wastepile.getFirst();
//    }
//
//
//    public LinkedList<Card> sort(LinkedList<Card> cards) {
//        Collections.sort(cards, new Comparator<Card>() {
//            @Override
//            public int compare(Card o1, Card o2) {
//                return Float.compare(o2.top, o1.top);
//            }
//        });
//        return cards;
//    }
//
//    public LinkedList<Card> getCards() {
//        return cards;
//    }
//
//
//    public LinkedList<Card> getWastepile() {
//        return wastepile;
//    }
//
//    public LinkedList<Card> getTableauC1() {
//        return tableauC1;
//    }
//
//    public LinkedList<Card> getTableauC2() {
//        return tableauC2;
//    }
//
//    public LinkedList<Card> getTableauC3() {
//        return tableauC3;
//    }
//
//    public LinkedList<Card> getTableauC4() {
//        return tableauC4;
//    }
//
//    public LinkedList<Card> getTableauC5() {
//        return tableauC5;
//    }
//
//    public LinkedList<Card> getTableauC6() {
//        return tableauC6;
//    }
//
//    public LinkedList<Card> getTableauC7() {
//        return tableauC7;
//    }
//
//    public Card getFoundation1() {
//        return foundation1;
//    }
//
//    public Card getFoundation2() {
//        return foundation2;
//    }
//
//    public Card getFoundation3() {
//        return foundation3;
//    }
//
//    public Card getFoundation4() {
//        return foundation4;
//    }
//}
