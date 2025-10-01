package com.example.dudiy_emotilog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.*;

public class SummaryActivity extends AppCompatActivity {
    private AppDatabase db;                             // Re-declaring things is fine
    private TextView tvSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "mood-db").allowMainThreadQueries().build();

        tvSummary = findViewById(R.id.summaryText);
        Button pickDateBtn = findViewById(R.id.btnPickDate);

        pickDateBtn.setOnClickListener(v -> showDatePicker());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = String.format(Locale.US,
                            "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                    showSummaryForDate(date);
                },
                year, month, day);
        dialog.show();
    }

    private void showSummaryForDate(String date) {
        List<Emoji> logs = db.emojiDao().getLogsByDate(date);

        if (logs.isEmpty()) {
            tvSummary.setText("No logs for " + date);
            return;
        }

        Map<String, Integer> counts = new HashMap<>();
        for (Emoji log : logs) {
            counts.put(log.mood, counts.getOrDefault(log.mood, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Summary for ").append(date).append(":\n\n");
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            sb.append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append(" times\n");
        }

        tvSummary.setText(sb.toString());
    }
}