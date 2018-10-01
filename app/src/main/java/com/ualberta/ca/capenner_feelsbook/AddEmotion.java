package com.ualberta.ca.capenner_feelsbook;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;

public class AddEmotion extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emotion);

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.save_button);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type=0;
                EmotionController control = new EmotionController();
                Spinner spinner = findViewById(R.id.EmotionDropMenu);
                String dropMenuItem = spinner.getSelectedItem().toString();
                EditText commentText = findViewById(R.id.commentText);
                String comment = commentText.getText().toString();
                switch (dropMenuItem) {
                    case "Love": type=1;
                        break;
                    case "Joy": type=2;
                        break;
                    case "Surprise": type=3;
                        break;
                    case "Anger": type=4;
                        break;
                    case "Sadness": type=5;
                        break;
                    case "Fear": type=6;
                        break;
                }
                Intent intent = getIntent();
                int position = intent.getIntExtra("emotion.position", -1);
                if (position == -1) {
                    control.addEmotion(new Emotion(type, comment));
                    control.updateEmotionCount(type, true);
                    Emotion emotion = EmotionController.getEmotionList().getEmotion(0);
                } else {
                    Emotion emotion = EmotionController.getEmotionList().getEmotion(position);
                    int prevType = emotion.getType();
                    control.updateEmotionCount(prevType, false);
                    emotion.setType(type);
                    control.updateEmotionCount(type, true);
                    emotion.setComment(comment);
                    EmotionController.getEmotionList().notifyListeners();
                }
                try {
                    SaveEmotionState.saveEmotionList(AddEmotion.this, "data.sav", EmotionController.getEmotionList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
