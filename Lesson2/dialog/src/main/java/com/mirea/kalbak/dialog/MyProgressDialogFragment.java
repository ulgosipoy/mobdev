package com.mirea.kalbak.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {
    private ProgressBar progressBar;
    private Handler handler;
    private Runnable progressUpdater;
    private int progress = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 50, 50, 50);

        TextView message = new TextView(requireContext());
        message.setText("Пожалуйста, подождите...");
        message.setTextSize(16);
        layout.addView(message);

        progressBar = new ProgressBar(requireContext(), null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setMax(100);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 30, 0, 0);
        progressBar.setLayoutParams(params);
        layout.addView(progressBar);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity())
                .setTitle("Загрузка данных")
                .setView(layout)
                .setCancelable(false);

        startProgress();

        return builder.create();
    }

    private void startProgress() {
        handler = new Handler();
        progressUpdater = new Runnable() {
            @Override
            public void run() {
                if (progress < 100) {
                    progress += 2;
                    progressBar.setProgress(progress);
                    handler.postDelayed(this, 100);
                } else {
                    dismiss();
                    showSnackbar("Загрузка завершена!");
                }
            }
        };
        handler.postDelayed(progressUpdater, 100);
    }

    private void showSnackbar(String message) {
        if (getActivity() != null) {
            View rootView = getActivity().findViewById(android.R.id.content);
            com.google.android.material.snackbar.Snackbar.make(
                    rootView,
                    message,
                    com.google.android.material.snackbar.Snackbar.LENGTH_LONG
            ).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(progressUpdater);
        }
    }
}
