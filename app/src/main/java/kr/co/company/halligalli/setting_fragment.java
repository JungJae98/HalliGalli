package kr.co.company.halligalli;

import android.content.Context;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class setting_fragment extends Fragment {
    int selectCardNum;

    public setting_fragment() {
        // Required empty public constructor
    }

    RadioGroup radioGroup;
    Button closeButton, saveButton;
    RadioButton radio20, radio25, radio30, radio35;
    private OnDataPassListener onDataPassListener;
    // 효과음을 사용하기위한 사운드 풇
    private SoundPool soundPool;
    private int soundId;
    private Context context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting_fragment, container, false);

        radioGroup = rootView.findViewById(R.id.radioGroup);
        closeButton = rootView.findViewById(R.id.closeButton);
        saveButton = rootView.findViewById(R.id.saveButton);
        radio20 = rootView.findViewById(R.id.radioButton20);
        radio25 = rootView.findViewById(R.id.radioButton25);
        radio30 = rootView.findViewById(R.id.radioButton30);
        radio35 = rootView.findViewById(R.id.radioButton35);




        // 사운드풀 설정
        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .build();
        soundId = soundPool.load(context, R.raw.btn_touch, 1);



        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundId, 1f, 1f, 1, 0, 1f); //사운드 재생
                // 버튼 클릭 시 프래그먼트 종료
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(setting_fragment.this).commit();
            }
        });



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 라디오 버튼 선택 값을 저장하고 메인 액티비티로 전달
                soundPool.play(soundId, 1f, 1f, 1, 0, 1f); //사운드 재생
                int selectedId = radioGroup.getCheckedRadioButtonId();
                switch (selectedId) {
                    case R.id.radioButton20:
                        selectCardNum = 20;
                        break;
                    case R.id.radioButton25:
                        selectCardNum = 25;
                        break;
                    case R.id.radioButton30:
                        selectCardNum = 30;
                        break;
                    case R.id.radioButton35:
                        selectCardNum = 35;
                        break;
                    default:
                        selectCardNum = 30;
                        break;
                }

                onDataPassListener.onDataPass(selectCardNum);

                // 프래그먼트를 비활성화하기 위해 숨김 처리
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.hide(setting_fragment.this);
                transaction.commit();



//                // 프래그먼트 종료
//                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//
//                fragmentManager.beginTransaction().remove(setting_fragment.this).commit();
            }
        });

        return rootView;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_setting_fragment, container, false);
    }
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        try {
            onDataPassListener = (OnDataPassListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnDataPassListener");
        }
        this.context = context;
    }


}
