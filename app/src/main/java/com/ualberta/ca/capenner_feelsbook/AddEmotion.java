package com.ualberta.ca.capenner_feelsbook;

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

public class AddEmotion extends AppCompatActivity {

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
                control.addEmotion(new Emotion(type, comment));
                control.updateEmotionCount(type);
                //Toast.makeText(AddEmotion.this, dropMenuItem + comment , Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddEmotion.this, "Ayyy" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddEmotion.this, EmotionDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
