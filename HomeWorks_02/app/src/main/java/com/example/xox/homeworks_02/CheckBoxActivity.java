package com.example.xox.homeworks_02;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CheckBoxActivity extends AppCompatActivity {
    TextView tv_check;
    CheckBox cb_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox);
        // фиксируем экран (запрет поворота)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // при нажатии на Toolbar - запуск MainActivity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckBoxActivity.this, MainActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        tv_check = (TextView) findViewById(R.id.tv_checkbox);
        cb_check = (CheckBox) findViewById(R.id.btn_checkbox);
    }

    // обработка CheckBox
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
                if (checked) {
                    tv_check.setText(R.string.checked);
                    cb_check.setText("");
                } else {
                    tv_check.setText("");
                    cb_check.setText(R.string.unchecked);
                }
    }
}