package com.example.alex.colorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by alex on 11/18/15.
 */
public class QuestionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spinner;
    private Button startQuiz;
    private static final String[]options = {"Male", "Female", "Transgender", "Do not want to choose"};
    private EditText age;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_question);
        spinner = (Spinner)findViewById(R.id.spinner);
        age = (EditText) findViewById(R.id.editAge);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(QuestionActivity.this,
                android.R.layout.simple_spinner_item,options);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        startQuiz = (Button) findViewById(R.id.goTOQuiz);
        startQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                beginQuiz(v);
            }
        });

    }

    private void beginQuiz(View v) {
        if(conditionsAreMet()==true) {
            String userAge = age.getText().toString();
            String userGender = spinner.getSelectedItem().toString();
            User user = new User (userGender, userAge);
            Intent intent = new Intent(this, SurveyActivity.class);
            intent.putExtra("UserObject", user);
            Log.d("PassedUserObject", user.toString());
            startActivity(intent);
        }
    }

    private boolean conditionsAreMet() {
        boolean next = false;
        if (!age.getText().toString().isEmpty() && Integer.parseInt(age.getText().toString())>=13
                && Integer.parseInt(age.getText().toString())<=38)
            next = true;

        return next;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
