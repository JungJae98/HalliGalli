package kr.co.company.halligalli;

import java.util.Random;

public class Card {
    private int field_card = 0;
    private String card1 = "start"; //1번 플레이어의 카드
    private String card2 = "start"; //2번 플레이어의 카드

//    private String cardType(){
//        Random random = new Random();
//        int cardTypeRand = random.nextInt(4)+1; //카드 타입을 정하기 위한 랜덤변수
//        String cardType = null; //카드의 이름이 저장될 변수
//
//        switch (cardTypeRand){
//            case 1:
//                cardType = "banana";
//                break;
//            case 2:
//                cardType = "berry";
//                break;
//            case 3:
//                cardType = "lime";
//                break;
//            case 4:
//                cardType = "plot";
//                break;
//        }
//        return cardType;
//    }
//
//    private int cardNumber(){
//        Random random = new Random();
//        int cardNumberRand = random.nextInt(100)+1; //카드 숫자를 정하기 위한 랜덤변수
//        int cardNumber; //카드의 숫자가 저장될 변수
//
//        if(cardNumberRand <= 35){
//            return 1;
//        } else if (cardNumberRand <= 56) {
//            return 2;
//        } else if (cardNumberRand <= 77) {
//            return 3;
//        } else if (cardNumberRand <= 91) {
//            return 4;
//        } else {
//            return 5;
//        }
//    }
//
//    public void filpCard(){
//        Random random = new Random();
//        int cardTypeRand = random.nextInt(4)+1; //카드 타입을 정하기 위한 랜덤변수
//        String cardType; //카드의 이름이 저장될 변수
//
//
//        switch (cardTypeRand){
//            case 1:
//                cardType = "banana";
//                break;
//            case 2:
//                cardType = "berry";
//                break;
//            case 3:
//                cardType = "lime";
//                break;
//            case 4:
//                cardType = "plot";
//                break;
//        }
//
//        int cardNumberRand = random.nextInt(100)+1; //카드 숫자를 정하기 위한 랜덤변수
//        int cardNumber; //카드의 숫자가 저장될 변수
//
//        if(cardNumberRand <= 35){
//
//        } else if (cardNumberRand <= 56) {
//
//        } else if (cardNumberRand <= 77) {
//
//        } else if (cardNumberRand <= 91) {
//
//        } else if (cardNumberRand <= 100) {
//
//        }
//
//    }

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

    public void addFieldCard(){
        field_card++;
        System.out.println(field_card);
    }

    public void resetFieldCard(){
        field_card = 0;
        System.out.println();
    }


}

