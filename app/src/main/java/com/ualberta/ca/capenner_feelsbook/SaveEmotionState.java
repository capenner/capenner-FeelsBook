package com.ualberta.ca.capenner_feelsbook;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
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
import java.util.ArrayList;
import java.util.List;

public class SaveEmotionState implements Serializable{
    private EmotionList emotionList;

    public SaveEmotionState(EmotionList emotionList) {
        if (emotionList == null)
            this.emotionList = emotionList;
    }

    public static EmotionList loadEmotionList(Context context, String key) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (EmotionList) ois.readObject();
    }

    public static void saveEmotionList(Context context, String key, EmotionList emotionList) throws IOException{
            FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(emotionList);
            oos.close();
            fos.close();
    }
}
