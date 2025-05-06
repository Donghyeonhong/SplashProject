package com.example.splashproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText EdId, EdPw;
    private TextView StatusText;
    private Button Login, j_btn_main;

    String tag = "LoginCheck";

    ActivityResultLauncher<Intent> Launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EdId = (EditText) findViewById(R.id.EditID);
        EdPw = (EditText) findViewById(R.id.EditPassword);
        StatusText = findViewById(R.id.textView4);
        Login = findViewById(R.id.button);

        Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = EdId.getText().toString();
                String pw = EdPw.getText().toString();

                Log.d(tag, "입력 아이디: " + id);
                Log.d(tag, "입력 패스워드: " + pw);

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("ID", id);
                intent.putExtra("Password", pw);
                //런처실행
                Launcher.launch(intent);
            }
        });

        //Launcher
        Launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result->{
            if(result.getResultCode() == MainActivity.RESULT_OK){
                Intent data = result.getData();
                StatusText.setText(data.getStringExtra("status"));
            }

            j_btn_main = findViewById(R.id.btn_main);

            //StatusText = 로그인 성공
            if("로그인 성공".equals(StatusText.getText().toString())){
                j_btn_main.setVisibility(View.VISIBLE);
            }
            else{
                j_btn_main.setVisibility(View.INVISIBLE);
            }

        });
    }

    public void onClicked_main(View view) {
        Intent intent = new Intent(MainActivity.this, IntentActivity.class);
        startActivity(intent);
    }
}