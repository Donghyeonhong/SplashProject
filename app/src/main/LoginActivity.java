package com.example.splashproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private TextView display_id, display_pw, display_statustext;
    String id, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        display_id = findViewById(R.id.textView_id);
        display_pw = findViewById(R.id.textView_pw);
        display_statustext = findViewById(R.id.textView11);

        Intent intent = getIntent();
        if(intent != null){
            id = intent.getStringExtra("ID");
            pw = intent.getStringExtra("Password");

            display_id.setText(id);
            display_pw.setText(pw);
        }

    }

    public void check(View v){
        Intent intent = new Intent();
        if (isUserValid(id, pw)){
            intent.putExtra("status", "로그인 성공");
        }
        else {
            intent.putExtra("status", "로그인 실패");
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    private boolean isUserValid(String username, String password){
        return username.equals("abcd") && password.equals("1234");
    }
}