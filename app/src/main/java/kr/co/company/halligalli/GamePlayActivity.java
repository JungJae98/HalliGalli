package kr.co.company.halligalli;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GamePlayActivity extends AppCompatActivity {

    int setCardNum = 20; //플레이어 카드카운트 세팅
    Game game = new Game();
    Player player1; //기본 카드 숫자를 세팅
    Player player2;
    Card card = new Card(); //카드 세팅과 저장할 곳
    ImageView p1_cardView; // 어떤 카드인지 띄워질 곳
    ImageView p2_cardView; //어떤 카드인지 띄워질 곳
    TextView p1_Tcard; //player1 카드 남은 갯수
    TextView p2_Tcard; //player2 카드 남은 갯수
    ImageView p1_bell; //player1 벨
    ImageView p2_bell; //player2 벨
    int newCard; //카드 갯수 업데이트를 위한 임시 변수
    boolean messageSent = false; //핸들러 메시지 수신을 위한 불린형 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        p1_cardView = (ImageView) findViewById(R.id.p1_card);
        p2_cardView = (ImageView) findViewById(R.id.p2_card);
        p1_Tcard = (TextView) findViewById(R.id.p1_Tcard);
        p2_Tcard = (TextView) findViewById(R.id.p2_Tcard);
        p1_bell = (ImageView) findViewById(R.id.p1_bell);
        p2_bell = (ImageView) findViewById(R.id.p2_bell);

        // 인스턴스로 받아온 카드로 카드 세팅
        setCardNum = getIntent().getIntExtra("playCardNum", 30);
        if(setCardNum == 0){
            player1 = new Player(30);
            player2 = new Player(30);
            p1_Tcard.setText(Integer.toString(30)); //세팅된 카드 카운팅 숫자를 출력
            p2_Tcard.setText(Integer.toString(30)); //세팅된 카드 카운팅 숫자를 출력
        } else {
            player1 = new Player(setCardNum);
            player2 = new Player(setCardNum);
            p1_Tcard.setText(Integer.toString(setCardNum)); //세팅된 카드 카운팅 숫자를 출력
            p2_Tcard.setText(Integer.toString(setCardNum)); //세팅된 카드 카운팅 숫자를 출력
        }

        System.out.println(setCardNum);





        MainHandler mHandler = new MainHandler();// 핸들러 사용
        BackgroundThread thread = new BackgroundThread(mHandler); //쓰레드 가져옴
        ReHandler rhandler = new ReHandler(); // 카드 카운트를 위한 쓰레드 가져옴
        CardCountThread countThread = new CardCountThread(rhandler); // 카드 카운트를 위한 쓰레드 가져옴
        thread.start();
        countThread.start();
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
                    p1_cardView.setAlpha(0.6f);
                    p2_cardView.setAlpha(1f);


                    //카드 카운트가 0이 될 경우를 처리해야함
                    if(player1.getCardcount() > 0){
                        // 남은카드 텍스트 업데이트
                        newCard = player1.getCardcount();
                        p1_Tcard.setText(Integer.toString(newCard));
                    }

                    p1_cardView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    game.p1Turn(); // 턴전환
                    messageSent = false;

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
                    p1_cardView.setAlpha(1f);
                    p2_cardView.setAlpha(0.6f);

                    //카드 카운트가 0이 될 경우를 처리해야 함
                    if(player2.getCardcount() > 0){
                        //남은카드 텍스트 업데이트
                        newCard = player2.getCardcount();
                        p2_Tcard.setText(Integer.toString(newCard));
                    }else{

                    }

                    p2_cardView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    game.p2Turn(); //플레이어 턴 전환
                    messageSent = false;

                }
            }
        });


    }

    class CardCountThread extends Thread{
        Handler rhandler;
        public CardCountThread(Handler handler){rhandler = handler;}
        public void run(){
            while(true){
                // 이것도 쓰레드에서 처리를 해야하나?
                if(player1.getCardcount() <= 0 || player2.getCardcount() <= 0){
                    Message rmsg = new Message();
                    rmsg.what = 1;
                    rhandler.sendMessage(rmsg); //핸들러로 메시지를 보냄
                }
            }
        }
    }

    class ReHandler extends Handler{
        boolean gameOver = false;
        @Override
        public void handleMessage(Message rmsg){
            super.handleMessage(rmsg);
            String winner;

            if(player1.getCardcount() > player2.getCardcount()){
                winner = "player1";
            }else {
                winner = "player2";
            }

            if(gameOver != true){
                if(rmsg.what == 1){

                    //이건 핸들러에서 처리를 해야할 듯?
                    AlertDialog.Builder builder = new AlertDialog.Builder(GamePlayActivity.this);
                    builder.setTitle(winner + "승리!").setMessage("게임을 다시시작하세요");
                    builder.setPositiveButton("재시작", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "재시작한다", Toast.LENGTH_SHORT).show();
                            restartGame();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setCancelable(false);
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                }
                gameOver = true;
            }
        }
    }

    private void restartGame(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
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
                if(messageSent == false){
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
                        Message msg = new Message();
                        msg.what = 1;

                        mhandler.sendMessage(msg); //핸들러로 메시지를 보냄
                        System.out.println("check");

                        messageSent = true; //메시지를 보내고나면 다시 못보내기 하기 위해 사용되는 변수

                        //카드를 초기화하기 위해 사용
                        card.setCard1("a");
                        card.setCard2("b");

                    }
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
            int imageResource = getResources().getIdentifier("cardback",  // p1,p2_cardView 바꿀 이미지 세팅
                    "drawable", getPackageName());

            // 카드 뒤집힐 때 애니메이션 효과를 위한 코드
            AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
            animation.setDuration(200);

            //조건
            if(msg.what == 1){
                // bell 투명도 조절
                p1_bell.setAlpha(1f);
                p2_bell.setAlpha(1f);

                //View 비활성화, bell 활성화
                p1_cardView.setEnabled(false);
                p2_cardView.setEnabled(false);
                p1_bell.setEnabled(true);
                p2_bell.setEnabled(true);
                p1_bell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newCard = player1.sumCardcount(card.getField_card()); //플레이어 카드에 필드카드 추가
                        p1_Tcard.setText(Integer.toString(newCard)); // 플레이어 카드 카운트 업데이트
                        card.resetFieldCard(); // 필드 카드 초기화
                        p1_bell.startAnimation(animation); //카드에 애니메이션 효과

                        // bell 투명도 조절
                        p1_bell.setAlpha(0.3f);
                        p2_bell.setAlpha(0.3f);
                        msg.what = 0;

                        //View 활성화, bell 비활성화
                        p1_cardView.setEnabled(true);
                        p2_cardView.setEnabled(true);
                        p1_bell.setEnabled(false);
                        p2_bell.setEnabled(false);

                        p1_cardView.setImageResource(imageResource); // 이미지를 변경
                        p2_cardView.setImageResource(imageResource); // 이미지를 변경
                    }
                });
                p2_bell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newCard = player2.sumCardcount(card.getField_card()); //플레이어 카드에 필드카드 추가
                        p2_Tcard.setText(Integer.toString(newCard)); // 플레이어 카드 카운트 업데이트
                        card.resetFieldCard(); // 필드카드 초기화
                        p2_bell.startAnimation(animation); //카드에 애니메이션 효과

                        // bell 투명도 조절
                        p1_bell.setAlpha(0.3f);
                        p2_bell.setAlpha(0.3f);
                        msg.what = 0;

                        //View 활성화, bell 비활성화
                        p1_cardView.setEnabled(true);
                        p2_cardView.setEnabled(true);
                        p1_bell.setEnabled(false);
                        p2_bell.setEnabled(false);

                        p1_cardView.setImageResource(imageResource); // 이미지를 변경
                        p2_cardView.setImageResource(imageResource); // 이미지를 변경

                    }
                });

            }
        }
    }

}