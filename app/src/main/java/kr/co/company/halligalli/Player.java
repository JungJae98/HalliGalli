package kr.co.company.halligalli;

public class Player {
    private int cardcount;

    public Player(int cardcount){ //플레이어의 카드 카운트 세팅
        this.cardcount = cardcount;
    }

    public void desCardcount(){ //플레이어의 카드 카운트 감소
        this.cardcount--;
    }

    public int getCardcount(){ //플레이어의 카드 카운트 가져오기
        return cardcount;
    }

    public int sumCardcount(int sumcard){
        this.cardcount = this.cardcount+ sumcard;
        return cardcount;
    }
}
