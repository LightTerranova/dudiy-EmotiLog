package com.example.dudiy_emotilog;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Emoji to put into a db with unique pk
@Entity
public class Emoji {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String mood;
    public long timestamp;
}