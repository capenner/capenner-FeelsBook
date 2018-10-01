package com.ualberta.ca.capenner_feelsbook;

import java.util.Date;

public class EmotionController {

    // Singleton
    private static EmotionList emotionList = null;
    public static EmotionList getEmotionList() {
        if (emotionList == null) {
            emotionList = new EmotionList();
        }
        return emotionList;
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
