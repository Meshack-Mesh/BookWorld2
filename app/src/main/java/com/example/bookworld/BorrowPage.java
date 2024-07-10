package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BorrowPage extends AppCompatActivity {

    private EditText nameEditText;
    private Spinner daysSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_page);

        // Initialize views
        ImageView backButton = findViewById(R.id.backButton);
        ImageView threeDotButton = findViewById(R.id.threeDotButton);
        nameEditText = findViewById(R.id.nameEditText);
        daysSpinner = findViewById(R.id.daysSpinner);
        Button submitButton = findViewById(R.id.submitButton);

        // Set up the Spinner with the days array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daysSpinner.setAdapter(adapter);

        // Set click listeners for the buttons
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity to go back to the previous one
                finish();
            }
        });

        threeDotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BorrowPage.this, three_dots.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();

                if (name.isEmpty()) {
                    nameEditText.setError("Name cannot be empty");
                    nameEditText.requestFocus();
                    return;
                }

                String days = daysSpinner.getSelectedItem().toString();
                Toast.makeText(BorrowPage.this, "Name: " + name + ", Days: " + days, Toast.LENGTH_SHORT).show();
            }
        });
    }
}