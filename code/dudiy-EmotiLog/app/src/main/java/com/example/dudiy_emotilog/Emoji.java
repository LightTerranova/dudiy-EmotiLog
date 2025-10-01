package com.example.dudiy_emotilog;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Emoji {
    // Creating an emoji object that people will use to log their moods
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String mood;
    public long timestamp;
}