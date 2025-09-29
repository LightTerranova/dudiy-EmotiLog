package com.example.dudiy_emotilog;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

// Making a way to access the db for emoji obj
public class EmojiDAO {
    @Dao
    public interface EmojiDao {
        @Insert
        void insert(Emoji emoji);

        @Query("SELECT *")
        List<Emoji> getLogs(long day);
    }
}
