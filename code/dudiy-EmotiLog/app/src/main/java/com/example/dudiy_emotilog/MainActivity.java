package com.example.dudiy_emotilog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    // main activity to show mood buttons
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating a database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mood-db").allowMainThreadQueries().build();

        // setting up buttons
        setupButton(R.id.btnHappy, "Happy");
        setupButton(R.id.btnSad, "Sad");
        setupButton(R.id.btnAngry, "Angry");
        setupButton(R.id.btnExcited, "Excited");
        setupButton(R.id.btnTired, "Tired");
        setupButton(R.id.btnCalm, "Calm");

        findViewById(R.id.btnSummary).setOnClickListener(v -> {
            startActivity(new Intent(this, SummaryActivity.class));
        });
    }

    // makes emoji object when button pressed
    // uses dao to put into db
    private void setupButton(int id, String mood) {
        Button btn = findViewById(id);
        btn.setOnClickListener(v -> {
            Emoji log = new Emoji();
            log.mood = mood;
            log.timestamp = System.currentTimeMillis();

            db.emojiDao().insert(log);
        });
    }
}