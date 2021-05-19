package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity4 extends AppCompatActivity {

    private ImageView clickOne, clickTwo, clickThree;
    private TextView carName;
    private TextView timer;
    int noOne;
    int noTwo;
    int noThree;
    private View clickableImage;
    private Button button;
    boolean win,time;


    Random random = new Random();

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
        time = true;
        CountDownTimer countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {

                if (win) {
                    Toast.makeText(MainActivity4.this, Html.fromHtml("<font color='#0EF23B' ><b>" + "CORRECT!" + "</b></font>"),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity4.this, Html.fromHtml("<font color='#F20E0E' ><b>" + "WRONG!" + "</b></font>"),
                            Toast.LENGTH_SHORT).show();

                }
                nextScreen();
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        clickOne = (ImageView) findViewById(R.id.click1);
        clickTwo = (ImageView) findViewById(R.id.click2);
        clickThree = (ImageView) findViewById(R.id.click3);
        carName = (TextView) findViewById(R.id.manufacture);
        timer = (TextView) findViewById(R.id.timer);
        clickableImage = findViewById(R.id.clickableImage);
        button = (Button) findViewById(R.id.next);


        noOne = random.nextInt(carImage.length);
        do {
            noTwo = random.nextInt(carImage.length);
        }while (noTwo == noOne);

        do {
            noThree = random.nextInt(carImage.length);
        }while ((noThree == noTwo) || (noThree == noOne));
        int rNo = random.nextInt(2);
        clickOne.setImageResource(carImage[noOne]);
        clickTwo.setImageResource(carImage[noTwo]);
        clickThree.setImageResource(carImage[noThree]);
        Integer name[] = {noOne, noTwo, noThree};

        carName.setText(carList[name[rNo]]);

        Intent intent = getIntent();
        boolean value = intent.getBooleanExtra("value",false);
        if (value){
            startTimer();
        }else {
            timer.setText("");
        }


        clickOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOne.setEnabled(false);
                clickTwo.setEnabled(false);
                clickThree.setEnabled(false);

                if (carList[noOne].equals(carList[name[rNo]])){
                    if (time){
                        win = true;
                    }
                    else {
                        Snackbar correctNote = Snackbar.make(clickableImage, "CORRECT!", Snackbar.LENGTH_INDEFINITE)
                                .setBackgroundTint(Color.rgb(0, 179, 60));
                        correctNote.show();
                    }

                }else {
                    if (time){
                        win = false;
                    }
                    else {
                        Snackbar wrongNote = Snackbar.make(clickableImage, "WRONG!", Snackbar.LENGTH_INDEFINITE)
                                .setBackgroundTint(Color.rgb(230,0,0));
                        wrongNote.show();
                        if (carList[noTwo].equals(carList[name[rNo]])){
                            clickTwo.setBackgroundColor(Color.GREEN);
                        }else {
                            clickThree.setBackgroundColor(Color.GREEN);
                        }
                    }

                }
            }
        });

        clickTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOne.setEnabled(false);
                clickTwo.setEnabled(false);
                clickThree.setEnabled(false);
                if (carList[noTwo].equals(carList[name[rNo]])){
                    if (time){
                        win = true;
                    }
                    else {
                        Snackbar correctNote = Snackbar.make(clickableImage, "CORRECT!", Snackbar.LENGTH_INDEFINITE)
                                .setBackgroundTint(Color.rgb(0, 179, 60));
                        correctNote.show();
                    }

                }else {
                    if (time){
                        win = false;
                    }
                    else {
                        Snackbar wrongNote = Snackbar.make(clickableImage, "WRONG!", Snackbar.LENGTH_INDEFINITE)
                                .setBackgroundTint(Color.rgb(230,0,0));
                        wrongNote.show();
                        if (carList[noThree].equals(carList[name[rNo]])){
                            clickThree.setBackgroundColor(Color.GREEN);
                        }else {
                            clickOne.setBackgroundColor(Color.GREEN);
                        }
                    }
                }
            }
        });

        clickThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOne.setEnabled(false);
                clickTwo.setEnabled(false);
                clickThree.setEnabled(false);
                if (carList[noThree].equals(carList[name[rNo]])){
                    if (time){
                        win = true;
                    }
                    else{
                        Snackbar correctNote = Snackbar.make(clickableImage, "CORRECT!", Snackbar.LENGTH_INDEFINITE)
                                .setBackgroundTint(Color.rgb(0, 179, 60));
                        correctNote.show();
                    }
                }else {
                    if (time){
                        win = false;
                    }
                    else {
                        Snackbar wrongNote = Snackbar.make(clickableImage, "WRONG!", Snackbar.LENGTH_INDEFINITE)
                                .setBackgroundTint(Color.rgb(230,0,0));
                        wrongNote.show();
                        if (carList[noTwo].equals(carList[name[rNo]])){
                            clickTwo.setBackgroundColor(Color.GREEN);
                        }else {
                            clickOne.setBackgroundColor(Color.GREEN);
                        }
                    }

                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextScreen();
            }
        });

    }

    private void nextScreen() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}