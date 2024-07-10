package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        // Initialize layouts and button
        LinearLayout homeLayout = findViewById(R.id.homeart);
        LinearLayout myBooksLayout = findViewById(R.id.mybooksart);
        LinearLayout searchLayout = findViewById(R.id.searchart);
        LinearLayout moreLayout = findViewById(R.id.moreart);
        ImageView threeDotButton = findViewById(R.id.threeDotButton);
        ImageView backButton = findViewById(R.id.backButton);
        Button buttonLend = findViewById(R.id.btnLendBook);
        Button buttonBorrow = findViewById(R.id.btnBorrowBook);
        // Ensure this ID matches your XML

        // Set onClick listeners
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity2.this, Home.class);
                startActivity(intent);
            }
        });
       buttonLend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity2.this, LendBookActivity.class);
                startActivity(intent);
            }
        });
        buttonBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity2.this, BookDetails.class);
                startActivity(intent);
            }
        });

        myBooksLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity2.this, MyBooks.class);
                startActivity(intent);
            }
        });

        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity2.this, search_discovery.class);
                startActivity(intent);
            }
        });

        // Set onClick listener for the "More" button to show pop-up window
        // Set onClick listener for the "More" button to show pop-up window
        moreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity2.this, More.class);
                startActivity(intent);
            }
        });

        threeDotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity2.this, three_dots.class);
                startActivity(intent);
            }
        });


        // Set onClick listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close the current activity
            }
        });










        findViewById(R.id.btnLendBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, LendBookActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnBorrowBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, BookDetails.class);
                startActivity(intent);
            }
        });
    }
}