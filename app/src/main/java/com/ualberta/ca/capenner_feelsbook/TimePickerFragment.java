package com.ualberta.ca.capenner_feelsbook;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.time.LocalDateTime;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Activity AddEmotion = getActivity();
        Intent intent = AddEmotion.getIntent();
        final int position = intent.getIntExtra("emotion.position", -1);
        Emotion emotion = EmotionController.getEmotionList().getEmotion(position);
        LocalDateTime localDateTime = emotion.getTimestamp();
        localDateTime = localDateTime.withHour(hourOfDay);
        localDateTime = localDateTime.withMinute(minute);
        emotion.setTimestamp(localDateTime);
    }
}
