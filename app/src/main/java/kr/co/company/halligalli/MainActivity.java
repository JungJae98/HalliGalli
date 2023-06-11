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
    ImageView p1_cardView;
    ImageView p2_cardView;
    TextView p1_Tcard;
    TextView p2_Tcard;

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
                }
                player1.desCardcount(); //카드수 1 감소
                //텍스트 새롭게 설정
                if(player1.getCardcount() != 0){
                    // 텍스트를 새롭게 설정한다.
                    int newCard = player1.getCardcount();
                    p1_Tcard.setText(Integer.toString(newCard));
                }else{

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
                }
                p2_cardView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        });
    }


}