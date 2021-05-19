package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity5 extends AppCompatActivity {

    Random random = new Random();

    private ImageView imageOne;
    private ImageView imageTwo;
    private ImageView imageThree;

    private EditText nameOne;
    private EditText nameTwo;
    private EditText nameThree;

    private Button submit;
    private TextView timer;
    private TextView correct;
    private TextView ans1;
    private TextView ans2;
    private TextView ans3;
    private TextView marks;

    int noOne;
    int noTwo;
    int noThree;
    int c1 = 0;
    int c2 = 0;
    int c3 = 0;
    int tryAgain = 3;

    private Integer carImage[] = {R.drawable.benz, R.drawable.bmw, R.drawable.buggati, R.drawable.ferrari,
            R.drawable.jaguar, R.drawable.lamborghini, R.drawable.mercedes, R.drawable.ford, R.drawable.kawasaki,
            R.drawable.lexus, R.drawable.acura, R.drawable.bently, R.drawable.buick, R.drawable.dodge, R.drawable.fiat,
            R.drawable.kia, R.drawable.lotus, R.drawable.mazda, R.drawable.mercury, R.drawable.polestar,
            R.drawable.porsche, R.drawable.telsa, R.drawable.volvo, R.drawable.chevrolet, R.drawable.genesis, R.drawable.nissan,
            R.drawable.pontiac, R.drawable.rollsroyce, R.drawable.scion, R.drawable.subaru, R.drawable.volkswagen};

    private String carList[] = {"benz","BMW","buggati","ferrari","jaguar","lamborghini","mercedes", "ford", "kawasaki", "lexus",
            "acura","bently", "buick", "dodge", "fiat", "kia", "lotus", "mazda", "mercury", "polestar",
            "porsche", "tesla", "volvo", "chevrolet", "genesis", "nissan", "pontiac", "rollsroyce", "scion", "subaru",
            "volkswagen"};

    private void startTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                tryAgain = 0;
                initializeGame();
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        imageOne = (ImageView) findViewById(R.id.imageOne);
        imageTwo = (ImageView) findViewById(R.id.imageTwo);
        imageThree = (ImageView) findViewById(R.id.imageThree);

        nameOne = (EditText) findViewById(R.id.nameOne);
        nameTwo = (EditText) findViewById(R.id.nameTwo);
        nameThree = (EditText) findViewById(R.id.nameThree);

        submit = (Button) findViewById(R.id.submit);
        timer = (TextView) findViewById(R.id.timer);
        correct = (TextView) findViewById(R.id.correct);

        ans1 = (TextView) findViewById(R.id.ans1);
        ans2 = (TextView) findViewById(R.id.ans2);
        ans3 = (TextView) findViewById(R.id.ans3);
        marks = (TextView) findViewById(R.id.marks);

        noOne = random.nextInt(carList.length);
        do {
            noTwo = random.nextInt(carList.length);
        }while (noTwo == noOne);

        do {
            noThree = random.nextInt(carList.length);
        }while ((noThree == noTwo) || (noThree == noOne));

        imageOne.setImageResource(carImage[noOne]);
        imageTwo.setImageResource(carImage[noTwo]);
        imageThree.setImageResource(carImage[noThree]);

        Intent intent = getIntent();
        boolean value = intent.getBooleanExtra("value",false);
        if (value){
            startTimer();
        }else {
            timer.setText("");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAgain = tryAgain - 1;
                //System.out.println(tryAgain);
                initializeGame();
                if (submit.getText().toString().equals("NEXT")){
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    private void initializeGame() {

        if (nameOne.getText().toString().equalsIgnoreCase(carList[noOne])){
            nameOne.setBackgroundColor(Color.rgb(0, 204, 0));
            nameOne.setEnabled(false);
            c1 = 1;
           marks.setText("SCORE : 1");
        }else {
            nameOne.setBackgroundColor(Color.RED);
            if ( tryAgain == 0 && c1 == 0){
                ans1.setText(carList[noOne]);
                ans1.setTextColor(Color.rgb(255, 255, 0));
            }
        }
        if (nameTwo.getText().toString().equalsIgnoreCase(carList[noTwo])){
            nameTwo.setBackgroundColor(Color.rgb(0, 204, 0));
            nameTwo.setEnabled(false);
            c2 = 1;
            marks.setText("SCORE : 1");
        }else {
            nameTwo.setBackgroundColor(Color.RED);
            if ( tryAgain == 0 && c2 == 0){
                ans2.setText(carList[noTwo]);
                ans2.setTextColor(Color.rgb(255, 255, 0));
            }

        }
        if (nameThree.getText().toString().equals(carList[noThree].toLowerCase())){
            nameThree.setBackgroundColor(Color.rgb(0, 204, 0));
            nameThree.setEnabled(false);
            c3 = 1;
            marks.setText("SCORE : 1");

        }else {
            nameThree.setBackgroundColor(Color.RED);
            if ( tryAgain == 0 && c3 == 0){
                ans3.setText(carList[noThree]);
                ans3.setTextColor(Color.rgb(255, 255, 0));
            }
        }
        if ((c1 == 1) && (c2 == 1) && (c3 == 1)) {
            correct.setText("All Correct!");
            correct.setTextColor(Color.rgb(0, 179, 60));
            marks.setText("SCORE : 3");
            submit.setText("NEXT");
        }else if (((c1 == 1) && (c2 == 1)) || ((c2 == 1) && (c3 == 1)) || ((c1 == 1) && (c3 == 1))){
            marks.setText("SCORE : 2");
        }
        System.out.println(tryAgain);
        System.out.println(c1 +"ddddd"+ c2 +"ddddd"+ c3);
        while (tryAgain == 0){
            if (c1 == 0) {
                ans1.setText(carList[noOne]);
                System.out.println(carList[noOne]);
                nameOne.setEnabled(false);
                ans1.setTextColor(Color.rgb(255, 255, 0));
                if (c2 == 0) {
                    ans2.setText(carList[noTwo]);
                    nameTwo.setEnabled(false);
                    ans2.setTextColor(Color.rgb(255, 255, 0));
                    if (c3 == 0) {
                        ans3.setText(carList[noThree]);
                        nameThree.setEnabled(false);
                        ans3.setTextColor(Color.rgb(255, 255, 0));
                    }
                }
            }
            submit.setText("NEXT");
            break;
        }
    }
}