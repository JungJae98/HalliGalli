package kr.co.company.halligalli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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


        MainHandler mHandler = new MainHandler();// 핸들러 사용
        BackgroundThread thread = new BackgroundThread(mHandler); //쓰레드 가져옴
        thread.start();




        // 카드 뒤집힐 때 애니메이션 효과를 위한 코드
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(200);

        p1_cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(game.playerTurn == 0){
                    String cardFilp = game.cardFilp();
                    // 이미지를 랜덤으로 불러서 가져옴
                    int imageResource = getResources().getIdentifier(cardFilp, "drawable", getPackageName());
                    // 불러온 이미지로 p1_cardView의 이미지를 변경하는 if문 구절
                    if(imageResource != 0){
                        p1_cardView.setImageResource(imageResource); // 이미지를 변경
                        card.setCard1(cardFilp);
                        p1_cardView.startAnimation(animation); //카드에 애니메이션 효과
                    }
                    player1.desCardcount(); //카드수 1 감소
                    card.addFieldCard(); // 필드 카드수 증가


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
                    p1_cardView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    game.p1Turn(); // 턴전환
                }
            }
        });

        p2_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game.playerTurn != 0){

                    String cardFilp = game.cardFilp();

                    // 이미지를 랜덤으로 불러서 가져옴
                    int imageResource = getResources().getIdentifier(cardFilp, "drawable", getPackageName());
                    // 불러온 이미지로 p2_cardView의 이미지를 변경하는 if문 구절
                    if(imageResource != 0){
                        p2_cardView.setImageResource(imageResource); // 이미지를 변경
                        card.setCard2(cardFilp);
                        p2_cardView.startAnimation(animation); //카드에 애니메이션 효과
                    }
                    player2.desCardcount(); //카드 수 1감소
                    card.addFieldCard(); // 필드 카드수 증가

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
                    game.p2Turn(); //플레이어 턴 전환
                }
            }
        });
    }

    // 쓰레드를 이용하여 카드 조건을 확인
    class BackgroundThread extends Thread{
        Handler mhandler;
        public BackgroundThread(Handler handler){
            mhandler = handler;
        }
        public void run(){
            while(true){
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
                    card.resetFieldCard();

                    Message msg = new Message();
                    msg.what = 1;

                    mhandler.sendMessage(msg);

                }
                try{
                    Thread.sleep(100);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //핸들러를 통해서 데이터를 제어할 예정
    class MainHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);

            if(msg.what == 1){
                int imageResource = getResources().getIdentifier("bell", "drawable", getPackageName());
                if (imageResource != 0){
                    p1_cardView.setImageResource(imageResource);
                    p2_cardView.setImageResource(imageResource);
                }
            }
        }
    }
}

