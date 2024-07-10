package com.example.bookworld.bookdata;

public class Book {
    private String id;
    private String thumbnailUrl;
    private String title;
    private String author;
    private String description; // New field for book description
    private String price;
    private float rating;
    private boolean expanded; // Optional if you use it for expanding details

    // Constructors, getters, and setters
    public Book(String id, String thumbnailUrl, String title, String author, String description, String price, float rating) {
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.expanded = false; // Default value for expanded state
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
