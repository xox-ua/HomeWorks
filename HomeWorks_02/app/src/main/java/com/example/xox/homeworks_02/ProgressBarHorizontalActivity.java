package com.example.xox.homeworks_02;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarHorizontalActivity extends AppCompatActivity {
    ProgressBar bar;
    TextView tv_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar_horizontal);
        // фиксируем экран (запрет поворота)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // при нажатии на Toolbar - запуск MainActivity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProgressBarHorizontalActivity.this, MainActivity.class);
                startActivityForResult(intent, 1);
              }
        });

        // ProgressBar Horizontal
        bar = (ProgressBar) findViewById(R.id.progress_bar);
        tv_complete = (TextView) findViewById(R.id.complete);
        ObjectAnimator animation = ObjectAnimator.ofInt(bar, "progress", 0, 100);
        animation.setDuration(3000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }
            @Override
            public void onAnimationEnd(Animator animator) {
                tv_complete.setText(R.string.download_complete);
            }
            @Override
            public void onAnimationCancel(Animator animator) {
            }
            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animation.start();
    }
}