package com.cs.activityapp190627;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // startActivityForResult Method로 하위 Activity를 출력하는 경우,
    // 하위 Activity가 소멸될 때 호출되는 Method
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        String data = intent.getStringExtra("data");
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "Create Main Activity", Toast.LENGTH_LONG).show();

        Button subbtn = (Button)findViewById(R.id.subbtn);

        subbtn.setOnClickListener((view)->{

            // SubActivity를 호출할 수 있는 Intent 만들기
            // 부모가 될 Activity의 참조와 출력될 Activity의 Class이름을 가지고 생성
            Intent intent = new Intent(MainActivity.this, SubActivity.class);

            // 하위 Activity에게 전달할 Data 만들기
            EditText edit = (EditText)findViewById(R.id.message);
            String data = edit.getText().toString();
            intent.putExtra("message", data);

            // 새로운 Intent 출력
            //startActivity(intent);

            // Data를 넘겨받을 수 있도록 Intent를 출력
            // 두번째로 넘겨주는 정수는 하위 Activity를 구분하기 위한 Code 입니다.
            startActivityForResult(intent, 1);

        });
    }

    // Activity가 화면에 보여지면 무조건 호출되는 Method
    @Override
    public void onResume(){
        super.onResume();
        Toast.makeText(MainActivity.this, "Continue MainActivity", Toast.LENGTH_LONG).show();
    }
}