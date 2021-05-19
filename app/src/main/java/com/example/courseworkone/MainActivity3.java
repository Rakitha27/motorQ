package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class MainActivity3 extends AppCompatActivity {

    //Declare variables
    private ImageView img;
    private TypedArray icons;
    private Button submit;
    private EditText typeLetter;
    private TextView carName;
    private View identifyLayout;
    private TextView correctAnswerDisplay;
    private TextView timer;
    boolean win,time;

    String letterTried;
    String wordDisplayed;
    String wordToBeGuessed;
    String triesLeft;
    char[] wordDisplayedList;
    ArrayList<String> wordList;


    Animation scaleAnimation;
    int wrongGuesses = 3;



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

    Random random = new Random();
    int randomNo = random.nextInt(carList.length);


    void initializeGame(){
        wordToBeGuessed = carList[randomNo];
        //wordList.remove(randomNo);

        //Initialize char array
        wordDisplayedList = wordToBeGuessed.toCharArray();

        //Add underscores
        for (int i = 0; i < wordDisplayedList.length; i++){
            wordDisplayedList[i] = '_';
        }

        revealLetterInWord(wordDisplayedList[0]);
        revealLetterInWord(wordDisplayedList[wordDisplayedList.length-1]);

        wordDisplayed = String.valueOf(wordDisplayedList);

        //Display word
        displayWordsOnScreen();
        typeLetter.setText("");
        letterTried = " ";
        //carName.setText();
    }


    private void displayWordsOnScreen() {
        String formattedString = "";
        for (char character : wordDisplayedList){
            formattedString += character + " " ;
        }
        carName.setText(formattedString);
    }

    private void revealLetterInWord(char letter) {
        int indexOfLetters= wordToBeGuessed.indexOf(letter);

        //Loop if index is positive or 0
        while (indexOfLetters >= 0){
            wordDisplayedList[indexOfLetters] = wordToBeGuessed.charAt(indexOfLetters);
            indexOfLetters = wordToBeGuessed.indexOf(letter, indexOfLetters + 1);
        }
        //Update the Strings
        wordDisplayed = String.valueOf(wordDisplayedList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Initialize variables
        img = (ImageView) findViewById(R.id.img);
        submit = (Button) findViewById(R.id.submit);
        timer = (TextView) findViewById(R.id.timer);
        identifyLayout = findViewById(R.id.identifyLayout);
        carName = (TextView) findViewById(R.id.typeName);
        typeLetter = (EditText) findViewById(R.id.inputLetter);
        correctAnswerDisplay = (TextView) findViewById(R.id.correctAnswerDisplay);

        img.setBackgroundResource(carImage[randomNo]);

        Intent intent = getIntent();
        boolean value = intent.getBooleanExtra("value", false);
        if (value) {
            startTimer();
        } else {
            timer.setText("");
        }

        initializeGame();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        typeLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    checkIfLetterIsInWord(s.charAt(0));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void startTimer() {
        time=true;
        CountDownTimer countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf((int) (millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {
                if (win) {
                    Toast.makeText(MainActivity3.this, Html.fromHtml("<font color='#0EF23B' ><b>" + "CORRECT!" + "</b></font>"),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, Html.fromHtml("<font color='#F20E0E' ><b>" + "WRONG!" + "</b></font>"),
                            Toast.LENGTH_SHORT).show();

                }
                submit.setText("NEXT");
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        };
        countDownTimer.start();
    }
    private void checkIfLetterIsInWord(char letter) {
        //If the letter was found in the word
        if (wordToBeGuessed.indexOf(letter) >= 0){
            if (wordDisplayed.indexOf(letter) < 0 ){
                revealLetterInWord(letter);     //Replace the underscore with the letter

                displayWordsOnScreen();         //Update the changes on screen

                if (!wordDisplayed.contains("_")){
                    typeLetter.setEnabled(false);
                    if (time){
                        win = true;
                    }else {
                        submit.setText("NEXT");
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                        Snackbar correctNote = Snackbar.make(identifyLayout, "CORRECT!", Snackbar.LENGTH_INDEFINITE)
                                .setBackgroundTint(Color.rgb(0, 179, 60));
                        correctNote.show();
                    }
                }
            }

        }
        //If the letter was not found
        else{
            wrongGuesses = wrongGuesses - 1;
           // decreaseAndDisplayTriedLeft();
            if (wrongGuesses == 0){
                typeLetter.setEnabled(false);
                if (time){
                    win = false;
                }
                else {
                    submit.setText("NEXT");
                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });
                    correctAnswerDisplay.setText("Answer is "+ wordToBeGuessed );
                    correctAnswerDisplay.setBackgroundColor(Color.rgb(255, 255, 128));
                    correctAnswerDisplay.setTextSize(20);
                    Snackbar wrongNote = Snackbar.make(identifyLayout, "WRONG!", Snackbar.LENGTH_INDEFINITE)
                            .setBackgroundTint(Color.rgb(230,0,0));
                    wrongNote.show();
                }

            }
        }
    }


}