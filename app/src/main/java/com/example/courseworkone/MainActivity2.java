package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity{

    private ImageView images;
    private TypedArray icons,nameList;
    private Button btnChange;
    private Spinner spinner;
    private TextView message;
    private TextView answer;
    private View identifyLayout;
    Random random = new Random();
    public static String correctAnswer;
    private CountDownTimer countDownTimer;
    private TextView timer;
    boolean win;

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

    void refresh(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void startTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                if (win) {
                    Toast.makeText(MainActivity2.this, Html.fromHtml("<font color='#0EF23B' ><b>" + "CORRECT!" + "</b></font>"),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, Html.fromHtml("<font color='#F20E0E' ><b>" + "WRONG!" + "</b></font>"),
                            Toast.LENGTH_SHORT).show();

                }
                btnChange.setText("Next");
                refresh();
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        icons = getResources().obtainTypedArray(R.array.icons);
        images = (ImageView) findViewById(R.id.imageView);
        btnChange = (Button) findViewById(R.id.nextButton);
        spinner = (Spinner) findViewById(R.id.spinner);
        answer = (TextView) findViewById(R.id.message);
        identifyLayout = findViewById(R.id.identifyLayout);
        timer = (TextView) findViewById(R.id.timer);

        int randomNo = random.nextInt(carList.length);
        images.setImageResource(carImage[randomNo]);
        correctAnswer = carList[randomNo];
        Intent intent = getIntent();
        boolean value = intent.getBooleanExtra("value",false);
        if (value){
            startTimer();
        }else {
            timer.setText("");
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nameList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.getOnItemSelectedListener();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state= parent.getItemAtPosition(position).toString();
                System.out.println(state);
                System.out.println(correctAnswer);

                btnChange.setOnClickListener(new View.OnClickListener() {

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {

                        if (state.equals(carList[randomNo])){
                            win = true;
                            Snackbar correctNote = Snackbar.make(identifyLayout, "CORRECT!", Snackbar.LENGTH_INDEFINITE)
                                    .setBackgroundTint(Color.rgb(0, 179, 60));
                            correctNote.show();
                            btnChange.setText("Next");
                            btnChange.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    refresh();
                                }
                            });

                        }else {
                            win = false;
                            Snackbar wrongNote = Snackbar.make(identifyLayout, "WRONG!", Snackbar.LENGTH_INDEFINITE)
                                    .setBackgroundTint(Color.rgb(230,0,0));
                            wrongNote.show();
                            answer.setText("Correct answer is " + correctAnswer);
                            answer.setBackgroundColor(Color.rgb(255, 255, 128));
                            answer.setTextSize(20);
                            btnChange.setText("Next");
                            btnChange.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    refresh();
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}

