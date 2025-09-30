package com.example.dudiy_emotilog;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// Creating an object to store the DAOs
@Database(entities = {Emoji.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmojiDAO emojiDAO();
}
