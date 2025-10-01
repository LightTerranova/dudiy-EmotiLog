package com.example.dudiy_emotilog;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Emoji.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    // Creates a database onject with a data access object
    // This is where data will be stored
    public abstract EmojiDao emojiDao();
}
