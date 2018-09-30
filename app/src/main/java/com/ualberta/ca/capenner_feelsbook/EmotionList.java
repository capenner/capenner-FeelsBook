package com.ualberta.ca.capenner_feelsbook;

import java.util.ArrayList;

public class EmotionList {
    private int LoveCount;      //type 1
    private int JoyCount;       //type 2
    private int SurpriseCount;  //type 3
    private int AngerCount;     //type 4
    private int SadnessCount;   //type 5
    private int FearCount;      //type 6
    private ArrayList<Emotion> emotionList;

    public EmotionList() {
        emotionList = new ArrayList<Emotion>();
    }

    public ArrayList<Emotion> getEmotionList() {
        return emotionList;
    }

    public int getEmotionCount(int type) {
        int count=0;
        switch (type) {
            case 1: count=LoveCount;
                break;
            case 2: count=JoyCount;
                break;
            case 3: count=SurpriseCount;
                break;
            case 4: count=AngerCount;
                break;
            case 5: count=SadnessCount;
                break;
            case 6: count=FearCount;
                break;
        }
        return count;
    }

    public int updateEmotionCount(int type) {
        int count=0;
        switch (type) {
            case 1: count=LoveCount+1;
            break;
            case 2: count=JoyCount+1;
                break;
            case 3: count=SurpriseCount+1;
                break;
            case 4: count=AngerCount+1;
                break;
            case 5: count=SadnessCount+1;
                break;
            case 6: count=FearCount+1;
                break;
        }
        return count;
    }

    public void addEmotion(Emotion emotion) {
        emotionList.add(emotion);
    }

    public void deleteEmotion(Emotion emotion) {
        emotionList.remove(emotion);
    }
}
