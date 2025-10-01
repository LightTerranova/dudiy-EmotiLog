package com.example.dudiy_emotilog;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmojiDao {
    @Insert
    void insert(Emoji log);

    @Query("SELECT * FROM Emoji")
    List<Emoji> getAllLogs();
}
