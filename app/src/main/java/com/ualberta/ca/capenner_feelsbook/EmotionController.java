package com.ualberta.ca.capenner_feelsbook;

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

    public void updateEmotionCount(int type) {
        emotionList.updateEmotionCount(type);
    }
}
