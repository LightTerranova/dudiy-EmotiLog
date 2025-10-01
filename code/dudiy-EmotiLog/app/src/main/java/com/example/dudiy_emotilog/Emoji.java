package com.example.dudiy_emotilog;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Emoji {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String mood;
    public long timestamp;
}