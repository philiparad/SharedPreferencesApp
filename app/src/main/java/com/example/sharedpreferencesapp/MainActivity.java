package com.example.sharedpreferencesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, scoreEditText;
    private Button saveButton, loadButton, clearButton;
    private TextView displayTextView;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        scoreEditText = findViewById(R.id.scoreEditText);
        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);
        clearButton = findViewById(R.id.clearButton);
        displayTextView = findViewById(R.id.displayTextView);

        sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        editor = sharedPref.edit();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                int score = 0;
                try {
                    score = Integer.parseInt(scoreEditText.getText().toString());
                } catch (NumberFormatException e) {
                    score = 0;
                }
                editor.putString(getString(R.string.user_key), username);
                editor.putInt(getString(R.string.user_score), score);
                editor.apply();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = sharedPref.getString(getString(R.string.user_key), "");
                int score = sharedPref.getInt(getString(R.string.user_score), 0);
                displayTextView.setText("User: " + username + "\nScore: " + score);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();
                displayTextView.setText("Preferences cleared");
            }
        });
    }
}