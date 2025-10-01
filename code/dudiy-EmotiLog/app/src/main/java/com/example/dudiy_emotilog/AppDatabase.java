package com.example.dudiy_emotilog;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Emoji.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmojiDao emojiDao();
}
