package com.example.alex.colorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SurveyActivity extends AppCompatActivity {

    TextView temp;
    TextView progress;

    ImageView image;
    Button next;
    private RadioButton[] radioButtons = new RadioButton[4];
    private Map<String, String> allCorrectAnswers= new HashMap<>(); //allAnswers in sportsQuiz app
    private String[] colorArray = new String[]{"blue","green","orange",
            "purple","red", "yellow"};
    private int questionNumber;
    private List<String> emotionList = new ArrayList<>();
    private List<String> radioButtonList = new ArrayList<>();
    private Map<String, String> selectedAnswers = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Intent i = getIntent();
        User currentUser = (User) i.getExtras().getSerializable("UserObject");
        //temp = (TextView) findViewById(R.id.progress);
        next = (Button) findViewById(R.id.nextColor);
        image = (ImageView) findViewById(R.id.image_question);
        progress = (TextView) findViewById(R.id.question_counter_overall);


        //temp.setText(currentUser.getAge());
        radioButtons[0] = (RadioButton) findViewById(R.id.radio_button_1);
        radioButtons[1] = (RadioButton) findViewById(R.id.radio_button_2);
        radioButtons[2] = (RadioButton) findViewById(R.id.radio_button_3);
        radioButtons[3] = (RadioButton) findViewById(R.id.radio_button_4);


        questionNumber = 0;
        progress.setText(questionNumber + "/10");
        setImage(questionNumber);
        //This method is used to
        getAnswersFromXML();
        getEmotionsFromXML();
        setRandomAnswers();
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });
    }

    private void nextQuestion() {
        if (questionNumber < colorArray.length-1 && isChecked()!=null){
            addUserAnswersToMap(questionNumber);
            questionNumber++;
            setImage(questionNumber);
            progress.setText(questionNumber+ "/10");
            setRandomAnswers();
        }
    }

    private void setImage(int index) {

        image.setImageResource(getResources().getIdentifier(colorArray[index], "drawable", getPackageName()));

    }

    private void getAnswersFromXML(){

        allCorrectAnswers.clear();

        //specifying where the split is on the filename string to put the answer into map
        String[] stringArray = getResources().getStringArray(R.array.colorAnswers);
        for (String entry : stringArray) {
            String[] splitResult = entry.split("\\|", 2);
            // put answers into map so that it looks like Key:Color and Value:Emotion
            allCorrectAnswers.put(splitResult[1], splitResult[0]);
        }
    }
    private void getEmotionsFromXML(){
        emotionList.clear();
        String[] emotArray = getResources().getStringArray(R.array.emotions);
        for (String temp : emotArray){
            emotionList.add(temp);
        }
    }

    private void setRandomAnswers(){
        Random random = new Random();
        deselectAllRadioButtons();
        radioButtonList.clear();
        String str = colorArray[questionNumber];
        for(int i = 0; i < radioButtons.length; i++) {

            boolean set =false;
            while(!set){
                int j = 0;
                int rnd = random.nextInt(emotionList.size());
                for (String temp : emotionList){
                    if (j==rnd){
                        if(temp.equals(str)) {
                            radioButtons[i].setText(str);
                            radioButtonList.add(str);
                            set = true;
                        }
                        else if(!radioButtonList.contains(str) &&
                                !radioButtonList.contains(temp)){
                            radioButtons[i].setText(temp);
                            radioButtonList.add(temp);
                            set=true;
                        }
                        else
                            rnd = random.nextInt(emotionList.size());
                    }
                    j++;
                }
            }

        }
    }

    private void deselectAllRadioButtons() {
        for (RadioButton radioButton : radioButtons){
            radioButton.setChecked(false);
            radioButton.setText("");
        }
    }

    private RadioButton isChecked (){
        for (int t = 0; t < radioButtons.length; t++){
            if(radioButtons[t].isChecked()) {
                return radioButtons[t];
            }
        }
        return null;

    }
    private void addUserAnswersToMap(int index){
        RadioButton temp = isChecked();
        String userAnswer = temp.getText().toString();
        selectedAnswers.put(colorArray[index].toString(), userAnswer);
    }

}
