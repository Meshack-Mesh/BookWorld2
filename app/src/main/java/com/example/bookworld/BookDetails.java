package com.example.bookworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class BookDetails extends AppCompatActivity {

    private TextView titleTextView;
    private TextView authorTextView;
    private TextView descriptionTextView;
    private TextView priceTextView;
    private ImageView thumbnailImageView;
    private Button borrowButton;

    private ImageView backButton;
    private ImageView threeDotsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        // Initialize views
        titleTextView = findViewById(R.id.bookTitle);
        authorTextView = findViewById(R.id.bookAuthor);
        descriptionTextView = findViewById(R.id.bookDescription);
        priceTextView = findViewById(R.id.bookPrice);
        thumbnailImageView = findViewById(R.id.bookThumbnail);
        borrowButton = findViewById(R.id.borrowBk);
        backButton = findViewById(R.id.backButton);
        threeDotsButton = findViewById(R.id.three_dotButton);

        // Set borrow button click listener
        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to BorrowPage activity
                Intent intent = new Intent(BookDetails.this, BorrowPage.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        threeDotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the "three dots" activity
                Intent intent = new Intent(BookDetails.this,three_dots.class);
                startActivity(intent);
            }
        });

        // Retrieve book details from intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("BOOK_TITLE", "Default Title");
            String author = extras.getString("BOOK_AUTHOR", "Unknown Author");
            String description = extras.getString("BOOK_DESCRIPTION", "No Description");
            String price = extras.getString("BOOK_PRICE", "Price Not Available");
            String thumbnailUrl = extras.getString("BOOK_THUMBNAIL");

            // Set retrieved data to TextViews and ImageView
            titleTextView.setText(title);
            authorTextView.setText(author);
            descriptionTextView.setText(description);
            priceTextView.setText(price);
            Picasso.get().load(thumbnailUrl).into(thumbnailImageView);

            // Make description and price visible
            descriptionTextView.setVisibility(View.VISIBLE);
            priceTextView.setVisibility(View.VISIBLE);
        }
    }
}
