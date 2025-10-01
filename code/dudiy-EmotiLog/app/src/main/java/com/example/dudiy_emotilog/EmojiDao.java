package com.example.dudiy_emotilog;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmojiDao {
    // Creating a data access object to provide an interface to the database
    // Only allows inserting emoji objects and retrieving by date objects
    @Insert
    void insert(Emoji log);

    @Query("SELECT * FROM Emoji WHERE date(timestamp/1000, 'unixepoch') = :date")
    List<Emoji> getLogsByDate(String date);
}
