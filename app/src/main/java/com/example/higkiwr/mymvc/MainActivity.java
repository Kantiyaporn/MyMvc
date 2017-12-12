package com.example.higkiwr.mymvc;

import android.media.MediaPlayer;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imvAnimal;
    private RadioGroup radAnswer;
    private String strAnswer;
    private MyAlertDialog objMyAlert;
    private Question objQuestion;
    private MyAlertDialog objMyAlertDialog;
    private int intTime = 1;
    private MediaPlayer objMediaPlayerButton, objMediaPlayerRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialWidget();

        objQuestion = new Question();
        objQuestion.setOnQuestionChangeListener(new Question.OnQuestionChangeListener() {
            @Override
            public void onQuestionChangeListener(Question question) {
                switch (question.getIntQuestion()){
                    case 1:
                        imvAnimal.setImageResource(R.drawable.bee);
                        break;
                    case 2:
                        imvAnimal.setImageResource(R.drawable.bird);
                        break;
                    case 3:
                        imvAnimal.setImageResource(R.drawable.cat);
                        break;
                    case 4:
                        imvAnimal.setImageResource(R.drawable.cow);
                        break;
                    default:
                        break;
                }
            }
        });
        radAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton1:
                        strAnswer = "bee";
                        break;
                    case R.id.radioButton2:
                        strAnswer = "bird";
                        break;
                    case R.id.radioButton3:
                        strAnswer = "cat";
                        break;
                    case R.id.radioButton4:
                        strAnswer = "cow";
                        break;
                    default:
                        strAnswer = null;
                        break;
                }
                soundRadioButton();
                ToaseMessage();
            }
        });
    }
    private void ToaseMessage(){
        Toast.makeText(MainActivity.this," Are You Sure Your Answer is " + strAnswer, 5000).show();
    }
    private void soundRadioButton(){
        objMediaPlayerRadioButton = MediaPlayer.create(getBaseContext(), R.raw.bird);
        objMediaPlayerRadioButton.start();
    }
    private void initialWidget(){
        imvAnimal = (ImageView) findViewById(R.id.imageView);
        radAnswer = (RadioGroup) findViewById(R.id.radioGroup);
    }
    private void clickAnswer(View view){
       checkChooseAnswer();
    }
    private void checkChooseAnswer(){
       if (strAnswer != null){
           Log.d("masterUNG", "strAnswer = " + strAnswer);
       } else {
           Log.d("masterUNG", "Please Choose Something");
           objMyAlertDialog = new MyAlertDialog();

           objMyAlertDialog.NoChooseEveryThing(MainActivity.this);
       }
       soundButton();
    }
    private void soundButton(){
        objMediaPlayerButton = MediaPlayer.create(getBaseContext(), R.raw.effect);
        objMediaPlayerButton.start();
    }
    private void sentValueToQuestion(){
        if (intTime == 4){
            //intTime = 0;
             objIntent = new Intent (MainActivity.this, ShowAnswerActivity.class);
            startActivity(objIntent);
        }
        intTime++;
        objQuestion.setIntQuestion(intTime);
    }
}
