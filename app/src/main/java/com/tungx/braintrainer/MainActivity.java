package com.tungx.braintrainer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    Button startButton;
    Button box1;
    Button box2;
    Button box3;
    Button box4;
    Button userTap;
    Button resetButton;
    TextView timeBox;
    TextView questionBox;
    TextView scoreBox;
    TextView scoreDisplay;
    GridLayout answerBox;
    GridLayout scoreDisplayBox;
    int value_1;
    int value_2;
    int correctAns;
    int randomBoxTag;
    int box1_random;
    int box2_random;
    int box3_random;
    int box4_random;
    int box1_tag;
    int box2_tag;
    int box3_tag;
    int box4_tag;
    int userAns;
    String totalScore;
    double countScore;
    double countTotal=-1;
    int timeLeft=30000;
    double yourScore;
    boolean inGame = true;
    boolean scoreIsAppeared = false;
    boolean isPressed = false;
    TextView correctText;

    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        timeBox.setVisibility(View.VISIBLE);
        questionBox.setVisibility(View.VISIBLE);
        scoreBox.setVisibility(View.VISIBLE);
        answerBox.setVisibility(View.VISIBLE);
        randomQuestion();
        setCorrectAnswerBox(randomBoxTag);
        countDownTimer = new CountDownTimer(timeLeft,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeBox.setText(String.valueOf(millisUntilFinished/1000)+"s");
                inGame = true;
            }

            @Override
            public void onFinish() {
                Log.i("countTotal", String.valueOf(countTotal));
                if(countTotal != 0) {
                    yourScore = (countScore/countTotal)*100;
                } else {
                    yourScore = 0;
                }

                if(isPressed) {
                    scoreDisplay.setText(String.format("Your score: "+totalScore+" (%.2f",yourScore) + "%)");
                } else {
                    scoreDisplay.setText("Your score: 0/0 (00.00%)");
                }

                correctText.setVisibility(View.INVISIBLE);
                scoreDisplayBox.setVisibility(View.VISIBLE);
                scoreIsAppeared = true;
                resetButton.setVisibility(View.VISIBLE);
                inGame = false;
            }
        };
        countDownTimer.start();
    }

    public void reset(View view) {
        if(scoreIsAppeared != true) {
            countScore = 0;
            countTotal = 0;
            scoreBox.setText((int)countScore+"/"+(int)countTotal);
            totalScore = "0/0";
            timeLeft = 30000;
            countDownTimer.start();
            randomQuestion();
            setCorrectAnswerBox(randomBoxTag);
            resetButton.setVisibility(View.INVISIBLE);
        }
    }

    public void answer(View view) {
        isPressed = true;
        Random r = new Random();
        randomBoxTag = r.nextInt(4);
        if(inGame) {
            userTap = (Button) view;
            userAns = Integer.parseInt(userTap.getText().toString());
            if(userAns == correctAns) {
                correctText.setText("Correct");
                correctText.setBackgroundColor(Color.parseColor("#06b915"));
                correctText.setVisibility(View.VISIBLE);
                randomQuestion();
                setCorrectAnswerBox(randomBoxTag);
                countScore++;
                totalScore = (int)countScore+"/"+(int)countTotal;
                scoreBox.setText(totalScore);
            } else {
                correctText.setText("Incorrect");
                correctText.setBackgroundColor(Color.parseColor("#db131d"));
                correctText.setVisibility(View.VISIBLE);
                randomQuestion();
                setCorrectAnswerBox(randomBoxTag);
                totalScore = (int)countScore+"/"+(int)countTotal;
                scoreBox.setText(totalScore);
            }

        }

    }

    public void back(View view) {
        scoreDisplayBox.setVisibility(View.INVISIBLE);
        scoreIsAppeared = false;
    }

    public void setCorrectAnswerBox(int randomBoxTag) {
        Random rand = new Random();
        box1_random = rand.nextInt(200);
        box2_random = rand.nextInt(200);
        box3_random = rand.nextInt(200);
        box4_random = rand.nextInt(200);
        box1_tag = Integer.parseInt(box1.getTag().toString());
        box2_tag = Integer.parseInt(box2.getTag().toString());
        box3_tag = Integer.parseInt(box3.getTag().toString());
        box4_tag = Integer.parseInt(box4.getTag().toString());
        if(box1_tag == randomBoxTag) {
            box1.setText(String.valueOf(correctAns));
            box2.setText(String.valueOf(box2_random));
            box3.setText(String.valueOf(box3_random));
            box4.setText(String.valueOf(box4_random));
        } else if(box2_tag == randomBoxTag) {
            box1.setText(String.valueOf(box1_random));
            box2.setText(String.valueOf(correctAns));
            box3.setText(String.valueOf(box3_random));
            box4.setText(String.valueOf(box4_random));
        } else if(box3_tag == randomBoxTag) {
            box1.setText(String.valueOf(box1_random));
            box2.setText(String.valueOf(box2_random));
            box3.setText(String.valueOf(correctAns));
            box4.setText(String.valueOf(box4_random));
        } else {
            box1.setText(String.valueOf(box1_random));
            box2.setText(String.valueOf(box2_random));
            box3.setText(String.valueOf(box3_random));
            box4.setText(String.valueOf(correctAns));
        }
    }

    public void randomQuestion() {
        Random random = new Random();
        value_1 = random.nextInt(100) + 1;
        value_2 = random.nextInt(100) + 1;
        correctAns = value_1 + value_2;
        questionBox.setText(value_1+" + "+value_2);
        countTotal++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startButton);
        box1 = (Button) findViewById(R.id.box1);
        box2 = (Button) findViewById(R.id.box2);
        box3 = (Button) findViewById(R.id.box3);
        box4 = (Button) findViewById(R.id.box4);
        resetButton = (Button) findViewById(R.id.resetButton);
        timeBox = (TextView) findViewById(R.id.timeBox);
        questionBox = (TextView) findViewById(R.id.questionBox);
        scoreBox = (TextView) findViewById(R.id.scoreBox);
        correctText = (TextView) findViewById(R.id.correctText);
        scoreDisplay = (TextView) findViewById(R.id.scoreDisplay);
        answerBox = (GridLayout) findViewById(R.id.answerBox);
        scoreDisplayBox = (GridLayout) findViewById(R.id.scoreDisplayBox);
        Random r = new Random();
        randomBoxTag = r.nextInt(4);
        scoreBox.setText("0/0");
        scoreDisplay.setText("Your score: 0/0 (00.00%)");

    }
}
