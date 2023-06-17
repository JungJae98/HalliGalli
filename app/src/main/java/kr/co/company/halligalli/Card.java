package kr.co.company.halligalli;

import java.util.Random;

public class Card {
    private int field_card = 0;
    private String card1 = "start"; //1번 플레이어의 카드
    private String card2 = "start"; //2번 플레이어의 카드



    public void setCard1(String v){ //플레이어1 카드 세팅
        card1 = v;
    }

    public void setCard2(String v){ //플레이어2 카드 세팅
        card2 = v;
    }

    public String getCard1(){ //플레이어1 카드 정보 가져옴
        return card1;
    }

    public String getCard2(){ //플레이어2 카드 정보 가져옴
        return card2;
    }

    public void addFieldCard(){ // 필드 카드 하나씩 증가
        field_card++;
        System.out.println(field_card);
    }

    public int getField_card(){ // 필드에 있는 카드 가져옴
        return this.field_card;
    }

    public void resetFieldCard(){ // 필드에 있는 카드 리셋
        field_card = 0;
        System.out.println();
    }


}

