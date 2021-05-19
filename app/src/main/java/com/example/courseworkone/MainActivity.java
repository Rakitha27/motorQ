package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    SwitchCompat switchCompat ;
    private TextView timer;
    private static final int start = 20;
    private boolean timerRunning;
    private CountDownTimer countDownTimer;
    private int timeLeft = start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        switchCompat = (SwitchCompat) findViewById(R.id.switchIt);
        timer = (TextView) findViewById(R.id.timer);



//        switchCompat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (switchCompat.isChecked()){
//
//                }
//            }
//        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity2();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                openMainActivity3();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                openMainActivity4();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                openMainActivity5();

            }
        });

    }

    public void openMainActivity2() {
        Intent intent = new Intent(this,MainActivity2.class);
        intent.putExtra("value",switchCompat.isChecked());
        startActivity(intent);
    }
    private void openMainActivity3() {
        Intent intent = new Intent(this,MainActivity3.class);
        intent.putExtra("value",switchCompat.isChecked());
        startActivity(intent);
    }
    private void openMainActivity4() {
        Intent intent = new Intent(this,MainActivity4.class);
        intent.putExtra("value",switchCompat.isChecked());
        startActivity(intent);
    }
    private void openMainActivity5() {
        Intent intent = new Intent(this,MainActivity5.class);
        intent.putExtra("value",switchCompat.isChecked());
        startActivity(intent);
    }
}
