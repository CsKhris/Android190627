package com.cs.activityapp190627;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // MainActivity에서 전달한 Data 가져오기
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        Toast.makeText(SubActivity.this, message, Toast.LENGTH_LONG).show();

        Button mainBtn = (Button)findViewById(R.id.mainbtn);
        mainBtn.setOnClickListener((view) -> {

            // 상위 Activity에게 전달할 Data 생성
            Intent intent1 = new Intent();
            intent1.putExtra("data", "손흥민");

            // Data 전송 : 1번 Code로 intent1 전송
            setResult(1, intent1);

            finish();

        });
    }
}
