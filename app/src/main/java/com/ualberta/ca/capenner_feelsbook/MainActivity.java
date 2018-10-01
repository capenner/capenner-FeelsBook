/*
    capenner_feelsbook record emotions with dates and optional comments

    Copyright (C) 2018  Christopher Penner

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.ualberta.ca.capenner_feelsbook;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity implements Serializable{
    private static final String FILENAME = "data.sav";

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.add_button);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEmotion.class);
                startActivity(intent);
            }
        });
        try {
            EmotionList emotionList = SaveEmotionState.loadEmotionList(MainActivity.this, "data.sav");
            Collection<Emotion> emotions = emotionList.getEmotionCollection();
            final ArrayList<Emotion> list = new ArrayList<Emotion>(emotions);
            ListView listView = findViewById(R.id.EmotionListView);
            final ArrayAdapter<Emotion> emotionAdapter = new ArrayAdapter<Emotion>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(emotionAdapter);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            ListView listView = findViewById(R.id.EmotionListView);
            Collection<Emotion> emotions = EmotionController.getEmotionList().getEmotionCollection();
            final ArrayList<Emotion> list = new ArrayList<Emotion>(emotions);
            final ArrayAdapter<Emotion> emotionAdapter = new ArrayAdapter<Emotion>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(emotionAdapter);
        }
        EmotionController.getEmotionList().addListener(new Listener() {
            @Override
            public void update() {
                list.clear();
                Collection<Emotion> emotions = EmotionController.getEmotionList().getEmotionCollection();
                list.addAll(emotions);
                emotionAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EmotionDetailsActivity.class);
                intent.putExtra("emotion.position", position);
                startActivity(intent);
            }
        });
    }

    public void statsPage(MenuItem menuItem) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View statsView = inflater.inflate(R.layout.popup_window, null);
        EmotionController controller = new EmotionController();
        int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
        int height = ConstraintLayout.LayoutParams.MATCH_PARENT;
        final PopupWindow popupWindow = new PopupWindow(statsView, width, height, true);
        popupWindow.showAtLocation(statsView, Gravity.CENTER, 0, 0);
        statsView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
        //Update counts when selected
        TextView loveCount = popupWindow.getContentView().findViewById(R.id.loveCount);
        TextView joyCount = popupWindow.getContentView().findViewById(R.id.joyCount);
        TextView surpriseCount = popupWindow.getContentView().findViewById(R.id.surpriseCount);
        TextView angerCount = popupWindow.getContentView().findViewById(R.id.angerCount);
        TextView sadnessCount = popupWindow.getContentView().findViewById(R.id.sadnessCount);
        TextView fearCount = popupWindow.getContentView().findViewById(R.id.fearCount);
        String type_1_count = "Love: " + Integer.toString(controller.getEmotionCount(1));
        String type_2_count = "Joy: " + Integer.toString(controller.getEmotionCount(2));
        String type_3_count = "Surprise: " + Integer.toString(controller.getEmotionCount(3));
        String type_4_count = "Anger: " + Integer.toString(controller.getEmotionCount(4));
        String type_5_count = "Sadness: " + Integer.toString(controller.getEmotionCount(5));
        String type_6_count = "Fear: " + Integer.toString(controller.getEmotionCount(6));
        loveCount.setText(type_1_count);
        joyCount.setText(type_2_count);
        surpriseCount.setText(type_3_count);
        angerCount.setText(type_4_count);
        sadnessCount.setText(type_5_count);
        fearCount.setText(type_6_count);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_stats) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
