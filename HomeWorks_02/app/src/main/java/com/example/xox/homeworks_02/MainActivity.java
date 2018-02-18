package com.example.xox.homeworks_02;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_email;
    EditText et_pass;
    Button btn_google;
    Button btn_facebook;
    Button btn_show_pass;
    Button btn_log_in;
    TextView tv_clear;
    TextView tv_sign_up;
    TextView tv_terms;
    int pass_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // фиксируем экран (запрет поворота)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_email = (EditText) findViewById(R.id.email);
        et_pass = (EditText) findViewById(R.id.pass);
        btn_google = (Button) findViewById(R.id.btn_google);
        btn_facebook = (Button) findViewById(R.id.btn_facebook);
        btn_show_pass = (Button) findViewById(R.id.btn_pass);
        btn_log_in = (Button) findViewById(R.id.btn_log_in);
        tv_clear = (TextView) findViewById(R.id.clear);
        tv_sign_up = (TextView) findViewById(R.id.sign_up);
        tv_terms = (TextView) findViewById(R.id.terms);

        // нажатие на кнопку Google - запуск активности с кастомным ProgressBar'ом
        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProgressBarRoundActivity.class);
                startActivityForResult(intent, 1);
                Toast.makeText(getApplicationContext(), R.string.google, Toast.LENGTH_SHORT).show();
            }
        });

        // нажатие на кнопку Facebook - запуск активности с кастомным горизонтальным ProgressBar'ом
        btn_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProgressBarHorizontalActivity.class);
                startActivityForResult(intent, 1);
                Toast.makeText(getApplicationContext(), R.string.facebook, Toast.LENGTH_SHORT).show();
            }
        });

        // обработка нажатия на "Log in", а также правильности ввода e-mail: ____@___.__
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence temp_email = et_email.getText().toString();
                String temp_pass = et_pass.getText().toString();

                if (!isValidEmail(temp_email)) {
                    et_email.requestFocus();
                    et_email.setError(getString(R.string.error_email));
                } else if (temp_pass.matches("")) {
                    et_pass.setError(getString(R.string.error_pass));
                } else {
                    // осуществляем переход и выводим сообщение
                    Toast.makeText(getApplicationContext(), R.string.toast1, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // обработка кнопки "скрыть/показать пароль"
        btn_show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (pass_index == 1) {
                    // скрыть пароль
                    et_pass.setTransformationMethod(new PasswordTransformationMethod());
                    pass_index = 0;
                } else {
                    /// показать пароль
                    et_pass.setTransformationMethod(null);
                    pass_index = 1;
                }
            }
        });

        // обработка нажатия на "Clear the input fields"
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_email.setText("");
                et_pass.setText("");
            }
        });

        // обработка нажатия на "Sign up"
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.toast2, Toast.LENGTH_SHORT).show();
            }
        });

        // обработка нажатия на "Terms"
        tv_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckBoxActivity.class);
                startActivityForResult(intent, 1);
            }
        });


    }

    // проверка правильности формата введённого email
    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
