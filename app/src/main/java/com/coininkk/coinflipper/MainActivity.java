package com.coininkk.coinflipper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String HEAD = "HEAD";
    private static final String TAILS = "TAILS";
    private static final int DURATION = 250;
    private static final int DELAY = DURATION * 2;

    private ImageView frontSide;
    private ImageView backSide;
    private EasyFlipView flip;
    private boolean endFlip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flip = findViewById(R.id.cardFlipView);
        flip.setFlipDuration(DURATION);
        flip.setFlipEnabled(true);
        flip.setFlipOnceEnabled(false);
        frontSide = findViewById(R.id.coin_front_side);
        backSide = findViewById(R.id.coin_back_side);
        Glide.with(this).load(R.drawable.front_side).into(frontSide);
        Glide.with(this).load(R.drawable.back_side).into(backSide);
        frontSide.setOnClickListener(view -> startFlip());
        backSide.setOnClickListener(view -> startFlip());
        flip.setOnFlipListener((flip, newCurrentSide) -> {
            if (endFlip) {
                if (newCurrentSide.equals(EasyFlipView.FlipState.FRONT_SIDE)) {
                    showToast(HEAD);
                } else {
                    showToast(TAILS);
                }
            }
        });

        endFlip = false;
    }

    private void showToast(String result) {
        Toast toast = Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void startFlip() {
        final long startFlip = System.currentTimeMillis();
        final Handler handler = new Handler();
        int i[] = {0};
        Random random = new Random();
        long randomIdx = random.nextInt(7) + 3;
        backSide.setClickable(false);
        frontSide.setClickable(false);
        endFlip = false;
        for (; i[0] < randomIdx; i[0]++) {
            handler.postDelayed(() -> {
                flip.flipTheView();
                if (System.currentTimeMillis() - startFlip >= i[0] * DELAY - (DURATION + DELAY)) {
                    endFlip = true;
                    frontSide.setClickable(true);
                    backSide.setClickable(true);
                }
            }, DELAY * i[0]);
        }
    }

}