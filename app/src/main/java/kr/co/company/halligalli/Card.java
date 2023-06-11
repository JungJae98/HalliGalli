package kr.co.company.halligalli;

import java.util.Random;

public class Card {



    private String cardType(){
        Random random = new Random();
        int cardTypeRand = random.nextInt(4)+1; //카드 타입을 정하기 위한 랜덤변수
        String cardType = null; //카드의 이름이 저장될 변수
        
        switch (cardTypeRand){
            case 1:
                cardType = "banana";
                break;
            case 2:
                cardType = "berry";
                break;
            case 3:
                cardType = "lime";
                break;
            case 4:
                cardType = "plot";
                break;
        }
        return cardType;
    }

    private int cardNumber(){
        Random random = new Random();
        int cardNumberRand = random.nextInt(100)+1; //카드 숫자를 정하기 위한 랜덤변수
        int cardNumber; //카드의 숫자가 저장될 변수

        if(cardNumberRand <= 35){
            return 1;
        } else if (cardNumberRand <= 56) {
            return 2;
        } else if (cardNumberRand <= 77) {
            return 3;
        } else if (cardNumberRand <= 91) {
            return 4;
        } else {
            return 5;
        }
    }

    public void filpCard(){
        Random random = new Random();
        int cardTypeRand = random.nextInt(4)+1; //카드 타입을 정하기 위한 랜덤변수
        String cardType; //카드의 이름이 저장될 변수
        

        switch (cardTypeRand){
            case 1:
                cardType = "banana";
                break;
            case 2:
                cardType = "berry";
                break;
            case 3:
                cardType = "lime";
                break;
            case 4:
                cardType = "plot";
                break;
        }

        int cardNumberRand = random.nextInt(100)+1; //카드 숫자를 정하기 위한 랜덤변수
        int cardNumber; //카드의 숫자가 저장될 변수
        
        if(cardNumberRand <= 35){
            
        } else if (cardNumberRand <= 56) {
            
        } else if (cardNumberRand <= 77) {
            
        } else if (cardNumberRand <= 91) {
            
        } else if (cardNumberRand <= 100) {
            
        }

    }



}

