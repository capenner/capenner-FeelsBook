package com.ualberta.ca.capenner_feelsbook;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class EmotionList implements Serializable {
    private int LoveCount;      //type 1
    private int JoyCount;       //type 2
    private int SurpriseCount;  //type 3
    private int AngerCount;     //type 4
    private int SadnessCount;   //type 5
    private int FearCount;      //type 6
    private ArrayList<Emotion> emotionList;
    protected transient ArrayList<Listener> listeners;

    public EmotionList() {
        emotionList = new ArrayList<Emotion>();
        listeners = new ArrayList<Listener>();
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

    public void updateEmotionCount(int type, boolean increase) {
        if (increase) {
            switch (type) {
                case 1: LoveCount++;
                    break;
                case 2: JoyCount++;
                    break;
                case 3: SurpriseCount++;
                    break;
                case 4: AngerCount++;
                    break;
                case 5: SadnessCount++;
                    break;
                case 6: FearCount++;
                    break;
            }
        } else {
            switch (type) {
                case 1: LoveCount--;
                    break;
                case 2: JoyCount--;
                    break;
                case 3: SurpriseCount--;
                    break;
                case 4: AngerCount--;
                    break;
                case 5: SadnessCount--;
                    break;
                case 6: FearCount--;
                    break;
            }
        }
    }

    public Collection<Emotion> getEmotionCollection() {
        return emotionList;
    }

    public Emotion getEmotion(int position) {
        return emotionList.get(position);
    }

    public void editEmotion(Emotion emotion) {
        // Fix later
        int index;
        if (emotionList.contains(emotion)) {
            index = emotionList.indexOf(emotion);
            emotionList.set(index, emotion);
        }
    }

    public LocalDateTime getEmotionDate(Emotion emotion) {
        int index;
        if (emotionList.contains(emotion)) {
            index = emotionList.indexOf(emotion);
        } else {
            return null;
        }
        return emotionList.get(index).getDate();
    }

    public void addEmotion(Emotion emotion) {
        emotionList.add(0, emotion);
        notifyListeners();
    }

    public void deleteEmotion(Emotion emotion) {
        emotionList.remove(emotion);
        notifyListeners();
    }

    public void notifyListeners() {
        for (Listener listener: listeners) {
            listener.update();
        }
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
}
