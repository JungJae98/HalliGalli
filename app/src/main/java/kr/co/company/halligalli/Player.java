package kr.co.company.halligalli;

public class Player {
    private int cardcount;

    public Player(int cardcount){
        this.cardcount = cardcount;
    }

    public void desCardcount(){
        this.cardcount--;
    }

    public int getCardcount(){
        return cardcount;
    }

    public int sumCardcount(int sumcard){
        return cardcount + sumcard;
    }
}
