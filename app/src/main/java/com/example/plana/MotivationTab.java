package com.example.plana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MotivationTab extends AppCompatActivity {
    private ImageButton imageButtonMotivation;
    private ImageButton imageButtonCalendar;
    private ImageButton imageButtonToday;
    private TextView textViewQuote;
    private Handler quoteHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_motivation_tab);
        textViewQuote = (TextView) findViewById(R.id.textViewQuote);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    MediaType mediaType = MediaType.parse("application/json");
                    RequestBody body = RequestBody.create(mediaType, "{\r\n    \"key1\": \"value\",\r\n    \"key2\": \"value\"\r\n}");
                    Request request = new Request.Builder()
                            .url("https://motivational-quotes1.p.rapidapi.com/motivation")
                            .post(body)
                            .addHeader("content-type", "application/json")
                            .addHeader("x-rapidapi-host", "motivational-quotes1.p.rapidapi.com")
                            .addHeader("x-rapidapi-key", "b38d1941b5msh60a966118b7a05dp1abb8fjsnb2f29c8d84dc")
                            .build();

                    Response response = client.newCall(request).execute();
                    String quote= response.body().string();
                    quoteHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            textViewQuote.setText(quote);
                        }
                    });
                    Log.i("Response", quote);
                } catch (Exception e) {
                    Log.i("Error", e.getMessage());
                }
            }
        });

        thread.start();

        imageButtonMotivation = (ImageButton) findViewById(R.id.imageButton1);
        imageButtonCalendar = (ImageButton) findViewById(R.id.imageButton2);
        imageButtonToday = (ImageButton) findViewById(R.id.imageButton3);

        imageButtonMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationTab.this, MotivationTab.class);
                startActivity(intent);
            }
        });

        imageButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationTab.this, FirstCalendarTab.class);
                startActivity(intent);
            }
        });

        imageButtonToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotivationTab.this, TaskToday.class);
                startActivity(intent);
            }
        });
    }
}