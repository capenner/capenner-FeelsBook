package com.ualberta.ca.capenner_feelsbook;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class EmotionController {

    // Lazy Singleton
    private static EmotionList emotionList = null;
    public static EmotionList getEmotionList() {
        if (emotionList == null) {
            try {
                emotionList = SaveEmotionState.getSaveEmotionState().loadEmotionList();
                try {
                    emotionList.addListener(new Listener() {
                        @Override
                        public void update() {
                            saveEmotionList();
                        }
                    });
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                emotionList = new EmotionList();
                emotionList.addListener(new Listener() {
                    @Override
                    public void update() {
                        saveEmotionList();
                    }
                });
            }
        }
        return emotionList;
    }

    private static void saveEmotionList() {
        try {
            SaveEmotionState.getSaveEmotionState().saveEmotionList(getEmotionList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEmotion(Emotion emotion) {
        getEmotionList().addEmotion(emotion);
    }

    public void updateEmotionCount(int type, boolean increase) {
        emotionList.updateEmotionCount(type, increase);
    }

    public int getEmotionCount(int type) {
        return emotionList.getEmotionCount(type);
    }
}
