package com.example.dudiy_emotilog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

public class SummaryActivity extends AppCompatActivity {

    private AppDatabase db;             //Re-creating the db is fine
    private TextView summaryText;
    private Button btnBackToLog, btnPickDate;       // creating the buttons nicely

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        summaryText = findViewById(R.id.summaryText);
        btnBackToLog = findViewById(R.id.btnBackToLog);
        btnPickDate = findViewById(R.id.btnPickDate);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mood-db").allowMainThreadQueries().build();

        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(System.currentTimeMillis());
        showSummaryForDate(today);

        btnPickDate.setOnClickListener(v -> showDatePicker());
        btnBackToLog.setOnClickListener(v -> finish());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (DatePicker view, int year, int month, int dayOfMonth) -> {
                    String pickedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
                    showSummaryForDate(pickedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    private void showSummaryForDate(String date) {
        List<Emoji> logs = db.emojiDao().getLogsByDate(date);

        if (logs.isEmpty()) {
            summaryText.setText("No logs found for " + date);
            return;
        }

        // Count the number of logs
        Map<String, Integer> counts = new HashMap<>();
        for (Emoji log : logs) {
            String label = log.mood;
            counts.put(label, counts.getOrDefault(label, 0) + 1);
        }

        int total = logs.size();
        StringBuilder summaryBuilder = new StringBuilder();
        summaryBuilder.append("Summary for ").append(date).append("\n\n");

        // show the logs and their percent
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            int count = entry.getValue();
            double percentage = (count * 100.0) / total;
            summaryBuilder.append(entry.getKey())
                    .append(": ")
                    .append(count)
                    .append(" (")
                    .append(String.format(Locale.getDefault(), "%.1f", percentage))
                    .append("%)\n");
        }

        summaryText.setText(summaryBuilder.toString());
    }
}