package kr.co.company.halligalli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int setCardNum = 30;
    Game game = new Game();
    Player player1 = new Player(setCardNum); //기본 카드 숫자를 세팅
    Player player2 = new Player(setCardNum);
    Card card = new Card(); //카드 세팅과 저장할 곳
    ImageView p1_cardView; // 어떤 카드인지 띄워질 곳
    ImageView p2_cardView; //어떤 카드인지 띄워질 곳
    TextView p1_Tcard; //player1 카드 남은 갯수
    TextView p2_Tcard; //player2 카드 남은 갯수
//    String card[] = {"start", "start"}; //어떤 카드들이 나왔었는지 저장될 배열 0은 player1 1은 player2



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1_cardView = (ImageView) findViewById(R.id.p1_card);
        p2_cardView = (ImageView) findViewById(R.id.p2_card);
        p1_Tcard = (TextView) findViewById(R.id.p1_Tcard);
        p2_Tcard = (TextView) findViewById(R.id.p2_Tcard);
        
        p1_Tcard.setText(Integer.toString(setCardNum)); //세팅된 카드 숫자를 출력

        p1_cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String cardFilp = game.cardFilp();
                // 이미지를 랜덤으로 불러서 가져옴
                int imageResource = getResources().getIdentifier(cardFilp, "drawable", getPackageName());
                if(imageResource != 0){
                    p1_cardView.setImageResource(imageResource); //0이 아닐때 이미지를 변경한다.
                    card.setCard1(cardFilp);
//                    card[0] = cardFilp; //
                }
                player1.desCardcount(); //카드수 1 감소


                //카드 카운트가 0이 될 경우를 처리해야함
                if(player1.getCardcount() != 0){
                    // 남은카드 텍스트 업데이트
                    int newCard = player1.getCardcount();
                    p1_Tcard.setText(Integer.toString(newCard));
                }else{  // 카드 카운트가 0이 될 경우
                    //
                    //
                    //
                    // 게임 종료에 관한 코드 작성이 필요
                    //
                    //
                    //
                    //
                }

                // 과일 갯수의 합이 5일 경우
                String card1 = card.getCard1();
                String card2 = card.getCard2();
                if ((card1.equals("banana1") && card2.equals("banana4"))
                        || (card1.equals("banana2") && card2.equals("banana3"))
                        || (card1.equals("banana3") && card2.equals("banana2"))
                        || (card1.equals("banana4") && card2.equals("banana1"))
                        || (card1.equals("plum1") && card2.equals("plum4"))
                        || (card1.equals("plum2") && card2.equals("plum3"))
                        || (card1.equals("plum3") && card2.equals("plum2"))
                        || (card1.equals("plum4") && card2.equals("plum1"))
                        || (card1.equals("lime1") && card2.equals("lime4"))
                        || (card1.equals("lime2") && card2.equals("lime3"))
                        || (card1.equals("lime3") && card2.equals("lime2"))
                        || (card1.equals("lime4") && card2.equals("lime1"))
                        || (card1.equals("strawberry1") && card2.equals("strawberry4"))
                        || (card1.equals("strawberry2") && card2.equals("strawberry3"))
                        || (card1.equals("strawberry3") && card2.equals("strawberry2"))
                        || (card1.equals("strawberry4") && card2.equals("strawberry1"))
                        || (card1.equals("banana5") && !(card2.equals("banana1") || card2.equals("banana2") || card2.equals("banana3") || card2.equals("banana4") || card2.equals("banana5")))
                        || (card2.equals("banana5") && !(card1.equals("banana1") || card1.equals("banana2") || card1.equals("banana3") || card1.equals("banana4") || card1.equals("banana5")))
                        || (card1.equals("plum5") && !(card2.equals("plum1") || card2.equals("plum2") || card2.equals("plum3") || card2.equals("plum4") || card2.equals("plum5")))
                        || (card2.equals("plum5") && !(card1.equals("plum1") || card1.equals("plum2") || card1.equals("plum3") || card1.equals("plum4") || card1.equals("plum5")))
                        || (card1.equals("lime5") && !(card2.equals("lime1") || card2.equals("lime2") || card2.equals("lime3") || card2.equals("lime4") || card2.equals("lime5")))
                        || (card2.equals("lime5") && !(card1.equals("lime1") || card1.equals("lime2") || card1.equals("lime3") || card1.equals("lime4") || card1.equals("lime5")))
                        || (card1.equals("strawberry5") && !(card2.equals("strawberry1") || card2.equals("strawberry2") || card2.equals("strawberry3") || card2.equals("strawberry4") || card2.equals("strawberry5")))
                        || (card2.equals("strawberry5") && !(card1.equals("strawberry1") || card1.equals("strawberry2") || card1.equals("strawberry3") || card1.equals("strawberry4") || card1.equals("strawberry5")))
                ) {
                    System.out.println(card1 +" : " + card2 + "합이 5임");
                        // 조건이 참일 때 실행할 코드
                }






                p1_cardView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        });

        p2_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardFilp = game.cardFilp();
                // 이미지를 랜덤으로 불러서 가져옴
                int imageResource = getResources().getIdentifier(cardFilp, "drawable", getPackageName());
                if(imageResource != 0){
                    p2_cardView.setImageResource(imageResource);
                    card.setCard2(cardFilp);
                }
                player2.desCardcount(); //카드 수 1감소

                //카드 카운트가 0이 될 경우를 처리해야 함
                if(player2.getCardcount() != 0){
                    //남은카드 텍스트 업데이트
                    int newCard = player2.getCardcount();
                    p2_Tcard.setText(Integer.toString(newCard));
                }else{ //카드 카운트가 0이 될 경우
                    //
                    //
                    //
                    // 게임 종료에 관한 코드 작성이 필요
                    //
                    //
                    //
                    //
                }





                p2_cardView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        });


    }



}