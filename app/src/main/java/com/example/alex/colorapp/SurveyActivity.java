package com.example.alex.colorapp;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SurveyActivity extends AppCompatActivity {
    TextView progress;
    ImageView image;
    Button next;
    private RadioButton[] radioButtons = new RadioButton[4];
    private Map<String, String> allCorrectAnswers= new HashMap<>(); //allAnswers in sportsQuiz app
    private String[] colorArray = new String[]{"blue","green","orange",
            "purple","red", "yellow"};
    private Map<String, String> userAnswers = new HashMap<>();
    private int questionNumber;
    private List<String> emotArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        next = (Button) findViewById(R.id.nextColor);
        image = (ImageView) findViewById(R.id.image_question);
        progress = (TextView) findViewById(R.id.question_counter_overall);
        radioButtons[0] = (RadioButton) findViewById(R.id.radio_button_1);
        radioButtons[1] = (RadioButton) findViewById(R.id.radio_button_2);
        radioButtons[2] = (RadioButton) findViewById(R.id.radio_button_3);
        radioButtons[3] = (RadioButton) findViewById(R.id.radio_button_4);
        questionNumber = 1;
        progress.setText((questionNumber + 1) + "/10");
        setImage(0);
//        getAnswersFromXML();
//        getEmotionsFromXML();
//        setRandomAnswers();
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (questionNumber < colorArray.length){
                    setImage(questionNumber);
                    questionNumber++;
                    progress.setText((questionNumber+1) + "/10");
//                    setRandomAnswers();
                }
            }
        });
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
            // put answers into map
            allCorrectAnswers.put(splitResult[0], splitResult[1]);
        }
    }
    private void getEmotionsFromXML(){
        emotArray.clear();
        String[] stringArray = getResources().getStringArray(R.array.emotions);
        for (String entry : stringArray){
            emotArray.add(entry);
        }
    }

    private void setRandomAnswers(){
        Random random = new Random();
        deselectAllRadioButtons();

        for(int i = 0; i < radioButtons.length; i++) {
            int rnd = random.nextInt(emotArray.size());
            String str = colorArray[questionNumber-1];
            if (allCorrectAnswers.containsValue(colorArray[questionNumber - 1])) {

                radioButtons[i].setText(allCorrectAnswers.get(str));

            }
            else if(!(emotArray.get(rnd).equals(str))){
                radioButtons[i].setText(emotArray.get(rnd));
            }
        }
    }

    private void deselectAllRadioButtons() {
        for (RadioButton radioButton : radioButtons){
            radioButton.setChecked(false);
            radioButton.setText("");
        }
    }

}
