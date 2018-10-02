package com.ualberta.ca.capenner_feelsbook;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveEmotionState implements Serializable{
    private static final String FILENAME = "data.sav";
    private Context context;


    @SuppressLint("StaticFieldLeak")
    static private SaveEmotionState saveEmotionState = null;

    public static void initSaveState(Context context) {
        if (saveEmotionState == null) {
            if (context == null) {
                throw new RuntimeException("Missing context");
            }
            saveEmotionState = new SaveEmotionState(context);
        }
    }

    public static SaveEmotionState getSaveEmotionState() {
        return saveEmotionState;
    }

    private SaveEmotionState(Context context) {
        this.context = context;
    }

    public EmotionList loadEmotionList() throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (EmotionList) ois.readObject();
    }

    public void saveEmotionList(EmotionList emotionList) throws IOException{
        FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(emotionList);
        oos.close();
        fos.close();
    }
}
