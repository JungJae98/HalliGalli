package kr.co.company.halligalli;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnDataPassListener {
    int selectCardNum_M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //프래그먼트를 사용하기 위해 가져옴
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();


        //게임을 플레이할 수 있는 곳으로 이동동//
       Button goGameBtn = (Button) findViewById(R.id.goGameBtn);
        goGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GamePlayActivity.class);
                intent.putExtra("playCardNum", selectCardNum_M);
                startActivity(intent);
            }
        });

        //설정창을 띄울 곳 //
        Button settingBtn = (Button) findViewById(R.id.settingBtn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //프래그먼트를 사용하기 위해 가져옴
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                setting_fragment fragment = new setting_fragment();
                transaction.replace(R.id.set_fragment, fragment);
                transaction.commit();
            }
        });
    }
    @Override
    public void onDataPass(int selectCardNum){
        selectCardNum_M = selectCardNum;
        System.out.println(selectCardNum);
    }
}

