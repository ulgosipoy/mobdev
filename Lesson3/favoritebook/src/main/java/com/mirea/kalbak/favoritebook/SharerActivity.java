package com.mirea.kalbak.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SharerActivity extends AppCompatActivity {
    private EditText editTextBookName;
    private EditText editTextQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sharer);
        Log.d("SharerActivity", "Активность успешно создана");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView ageView = findViewById(R.id.textView3);
            String bookName = extras.getString(MainActivity.BOOK_NAME_KEY, "");
            String quote = extras.getString(MainActivity.QUOTES_KEY, "");
            ageView.setText(String.format("Моя любимая книга: %s и цитата: %s",
                    bookName, quote));
        }

        editTextBookName = findViewById(R.id.editTextBookName);
        editTextQuote = findViewById(R.id.editTextQuote);

        Button buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(v -> {
            String book = editTextBookName.getText().toString();
            String quote = editTextQuote.getText().toString();
            String result = "Название Вашей любимой книги: " + book + ". Цитата: " + quote;

            Intent resultIntent = new Intent();
            resultIntent.putExtra(MainActivity.USER_MESSAGE, result);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}