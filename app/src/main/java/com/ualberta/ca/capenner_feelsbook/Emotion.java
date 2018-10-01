package com.ualberta.ca.capenner_feelsbook;

import android.util.Log;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

//Love      type 1
//Joy       type 2
//Surprise  type 3
//Anger     type 4
//Sadness   type 5
//Fear      type 6
public class Emotion implements Serializable {
    public int type;
    private LocalDateTime timestamp;
    public String comment;
    private String face;

    public Emotion(int type, String comment) {
        this.type = type;
        this.comment = comment;
        setFace(type);
        LocalDateTime date = LocalDateTime.now();
        String text = date.format(ISO_LOCAL_DATE_TIME);
        timestamp = LocalDateTime.parse(text);
    }

    public void setType(int type) {
        setFace(type);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public LocalDateTime getDate() {
        return this.timestamp;
    }

    private void setFace(int type) {
        switch (type) {
            case 1:
                this.face = "<3";
                break;
            case 2:
                this.face = ":)";
                break;
            case 3:
                this.face = ":O";
                break;
            case 4:
                this.face = ">:(";
                break;
            case 5:
                this.face = ":(";
                break;
            case 6:
                this.face = ":/";
                break;
        }
    }

    private String getFace() {
        return this.face;
    }

    @Override
    public String toString() {
        return getFace() + " @ " + this.getDate().format(ISO_LOCAL_DATE_TIME);
    }
}
