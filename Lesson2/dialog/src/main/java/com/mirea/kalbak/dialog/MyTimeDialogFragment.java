package com.mirea.kalbak.dialog;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;
import androidx.fragment.app.DialogFragment;

public class MyTimeDialogFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public TimePickerDialog onCreateDialog(Bundle savedInstanceState) {
        final java.util.Calendar c = java.util.Calendar.getInstance();
        int hour = c.get(java.util.Calendar.HOUR_OF_DAY);
        int minute = c.get(java.util.Calendar.MINUTE);

        return new TimePickerDialog(requireActivity(), this, hour, minute, true);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = hourOfDay + ":" + minute;
        showSnackbar("Выбрано время: " + time);
    }

    private void showSnackbar(String message) {
        if (getActivity() != null) {
            android.view.View view = getActivity().findViewById(android.R.id.content);
            com.google.android.material.snackbar.Snackbar.make(view, message,
                    com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show();
        }
    }

}
