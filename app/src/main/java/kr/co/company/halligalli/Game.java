package kr.co.company.halligalli;

import java.util.Random;

public class Game {
    private Player player1;
    private Player player2;

    // 카드의 타입을 정하는 함수
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
                cardType = "plum";
                break;
        }
        return cardType;
    }

    //카드의 숫자를 정하는 함수
    private int cardNumber(){
        Random random = new Random();
        int cardNumberRand = random.nextInt(100)+1; //카드 숫자를 정하기 위한 랜덤변수
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

    public String cardFilp(){
        String cardFilp = null;
        String cardType = cardType();
        int cardNumber = cardNumber();
        if(cardType == "banana"){
            switch(cardNumber){
                case 1:
                    cardFilp = "banana1";
                    break;
                case 2:
                    cardFilp = "banana2";
                    break;
                case 3:
                    cardFilp = "banana3";
                    break;
                case 4:
                    cardFilp = "banana4";
                    break;
                case 5:
                    cardFilp = "banana5";
                    break;
            }
//            if(cardNumber() == 1){
//                return cardFilp ="banana1";
//            } else if (cardNumber() == 2) {
//                return cardFilp ="banana2";
//            } else if (cardNumber() == 3) {
//                return cardFilp ="banana3";
//            } else if (cardNumber() == 4) {
//                return cardFilp ="banana4";
//            } else if (cardNumber() == 5) {
//                return cardFilp ="banana5";
//            }
        }
        if(cardType == "berry"){
            switch(cardNumber){
                case 1:
                    cardFilp = "strawberry1";
                    break;
                case 2:
                    cardFilp = "strawberry2";
                    break;
                case 3:
                    cardFilp = "strawberry3";
                    break;
                case 4:
                    cardFilp = "strawberry4";
                    break;
                case 5:
                    cardFilp = "strawberry5";
                    break;
            }
//            if(cardNumber() == 1){
//                return cardFilp ="strawberry1";
//            } else if (cardNumber() == 2) {
//                return cardFilp ="strawberry2";
//            } else if (cardNumber() == 3) {
//                return cardFilp ="strawberry3";
//            } else if (cardNumber() == 4) {
//                return cardFilp ="strawberry4";
//            } else if (cardNumber() == 5) {
//                return cardFilp ="strawberry5";
//            }
        }
        if(cardType == "plum"){
            switch(cardNumber){
                case 1:
                    cardFilp = "plum1";
                    break;
                case 2:
                    cardFilp = "plum2";
                    break;
                case 3:
                    cardFilp = "plum3";
                    break;
                case 4:
                    cardFilp = "plum4";
                    break;
                case 5:
                    cardFilp = "plum5";
                    break;
            }
//            if(cardNumber() == 1){
//                return cardFilp ="plum1";
//            } else if (cardNumber() == 2) {
//                return cardFilp ="plum2";
//            } else if (cardNumber() == 3) {
//                return cardFilp ="plum3";
//            } else if (cardNumber() == 4) {
//                return cardFilp ="plum4";
//            } else if (cardNumber() == 5) {
//                return cardFilp ="plum5";
//            }
        }
        if(cardType == "lime"){
            switch(cardNumber){
                case 1:
                    cardFilp = "lime1";
                    break;
                case 2:
                    cardFilp = "lime2";
                    break;
                case 3:
                    cardFilp = "lime3";
                    break;
                case 4:
                    cardFilp = "lime4";
                    break;
                case 5:
                    cardFilp = "lime5";
                    break;
            }
//            if(cardNumber() == 1){
//                return cardFilp ="lime1";
//            } else if (cardNumber() == 2) {
//                return cardFilp ="lime2";
//            } else if (cardNumber() == 3) {
//                return cardFilp ="lime3";
//            } else if (cardNumber() == 4) {
//                return cardFilp ="lime4";
//            } else if (cardNumber() == 5) {
//                return cardFilp = "lime5";
//            }
        }
        return cardFilp;
    }
}
