package com.coininkk.coinflipper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class Loading extends AppCompatActivity {
    private boolean isActive = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ImageView loadingView = findViewById(R.id.loading);
        Glide
                .with(this)
                .load(R.drawable.giphy)
                .into(loadingView);
        loadAndRun();
    }

    public void loadAndRun() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (isActive) {
                    startActivity(new Intent(Loading.this, MainActivity.class));
                }
                finish();
            }
        }, 4000);
    }

    @Override
    protected void onPause() {
        isActive = false;
        super.onPause();
    }

    @Override
    protected void onResume() {
        isActive = true;
        super.onResume();
    }
}
