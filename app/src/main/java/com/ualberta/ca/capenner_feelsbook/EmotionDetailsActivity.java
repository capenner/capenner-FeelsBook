package com.ualberta.ca.capenner_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EmotionDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar detailsToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(detailsToolbar);

        //Getting the emotion from the bundle
        Intent intent = getIntent();
        int position = intent.getIntExtra("emotion.position", 0);
        Emotion emotion = EmotionController.getEmotionList().getEmotion(position);
        //Set the text items
        TextView title_content = findViewById(R.id.DetailsTitle);
        TextView comment_content = findViewById(R.id.DetailsCommentArea);
        String type = "";
        String title;
        switch (emotion.getType()) {
            case 1: type="Love";
                break;
            case 2: type="Joy";
                break;
            case 3: type="Surprise";
                break;
            case 4: type="Anger";
                break;
            case 5: type="Sadness";
                break;
            case 6: type="Fear";
                break;
        }
        LocalDateTime date = emotion.getTimestamp();
        title = type + " was felt at " + date.toString();
        title_content.setText(title);
        if (!emotion.getComment().equals("")) {
            comment_content.setText(emotion.getComment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Toast.makeText(EmotionDetailsActivity.this, "edit", Toast.LENGTH_SHORT).show();
            Intent currentIntent = getIntent();
            int position = currentIntent.getIntExtra("emotion.position", 0);
            Emotion emotion = EmotionController.getEmotionList().getEmotion(position);
            //goto addEmotion to edit it
            Intent nextIntent = new Intent(EmotionDetailsActivity.this, AddEmotion.class);
            nextIntent.putExtra("emotion.position", position);
            startActivity(nextIntent);
            finish();
        } else if (id == R.id.action_delete) {
            Intent intent = getIntent();
            int position = intent.getIntExtra("emotion.position", 0);
            Emotion emotion = EmotionController.getEmotionList().getEmotion(position);
            EmotionController.getEmotionList().deleteEmotion(emotion);
            EmotionController.getEmotionList().updateEmotionCount(emotion.getType(), false);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
