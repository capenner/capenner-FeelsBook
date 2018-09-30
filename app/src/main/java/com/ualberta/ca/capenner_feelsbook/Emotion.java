package com.ualberta.ca.capenner_feelsbook;

import java.util.Date;
//Love      type 1
//Joy       type 2
//Surprise  type 3
//Anger     type 4
//Sadness   type 5
//Fear      type 6
public class Emotion {
    public int type;
    private String face;
    private Date timestamp;
    public String comment;

    public Emotion(int type, String comment) {
        this.type = type;
        switch (type) {
            case 1: this.face="<3";
                break;
            case 2: this.face=":)";
                break;
            case 3: this.face=":O";
                break;
            case 4: this.face=">:(";
                break;
            case 5: this.face=":(";
                break;
            case 6: this.face=":/";
                break;
        }
        this.comment = comment;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getType() {
        return face;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return this.comment;
    }

    public Date getDate() {
        return this.timestamp;
    }
}
