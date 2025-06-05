package com.mirea.kalbak.dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.fragment.app.DialogFragment;

public class MyDateDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    @Override
    public DatePickerDialog onCreateDialog(Bundle savedInstanceState) {
        final java.util.Calendar c = java.util.Calendar.getInstance();
        int year = c.get(java.util.Calendar.YEAR);
        int month = c.get(java.util.Calendar.MONTH);
        int day = c.get(java.util.Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(requireActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date = day + "/" + (month + 1) + "/" + year;
        showSnackbar("Выбрана дата: " + date);
    }

    private void showSnackbar(String message) {
        if (getActivity() != null) {
            android.view.View view = getActivity().findViewById(android.R.id.content);
            com.google.android.material.snackbar.Snackbar.make(view, message,
                    com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show();
        }
    }
}
