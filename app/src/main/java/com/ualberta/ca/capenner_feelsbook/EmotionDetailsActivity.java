package com.ualberta.ca.capenner_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.Toast;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class EmotionDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_details);

        Toolbar detailsToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(detailsToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        Log.d("STATUS", "onCreateOptionsMenu: HA");
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
        } else if (id == R.id.action_delete) {
            Toast.makeText(EmotionDetailsActivity.this, "DELETE THIS", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
