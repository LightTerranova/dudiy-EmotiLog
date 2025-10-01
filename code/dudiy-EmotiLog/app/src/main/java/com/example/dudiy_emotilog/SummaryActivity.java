package com.example.dudiy_emotilog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.text.SimpleDateFormat;
import java.util.*;

public class SummaryActivity extends AppCompatActivity {
    // Creates summary and shows it on the summary screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView summaryText = findViewById(R.id.summaryText);

        // create db
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mood-db").allowMainThreadQueries().build();

        // making a date format to use for comparing later
        // getting today's date
        List<Emoji> logs = db.emojiDao().getAllLogs();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String today = sdf.format(new Date());

        // Counting the number of times an emotion happened
        // converts emoji date to new format, stores the num of todays emotions in a hashmap
        Map<String, Integer> freq = new HashMap<>();
        for (Emoji log : logs) {
            String logDate = sdf.format(new Date(log.timestamp));
            if (logDate.equals(today)) {
                freq.put(log.mood, freq.getOrDefault(log.mood, 0) + 1);
            }
        }

        // Showing the hashmap by building a string
        if (freq.isEmpty()) {
            summaryText.setText(R.string.no_logs_to_show);
        } else {
            StringBuilder sb = new StringBuilder();
            for (String mood : freq.keySet()) {
                sb.append(mood).append(": ").append(freq.get(mood)).append("\n");
            }
            summaryText.setText(sb.toString());
        }

        // back button to go back to the log your mood
        Button backToLogButton = findViewById(R.id.btnBackToLog);
        backToLogButton.setOnClickListener(v -> {
            Intent intent = new Intent(SummaryActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

