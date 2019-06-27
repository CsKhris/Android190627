package com.cs.activityapp190627;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // NationActivity에서 넘겨준 Nation Key의 Data 찾아오기
        Intent intent = getIntent();
        String nation = intent.getStringExtra("nation");

        // Soccer Table에서 Nation Column의 값이 nation 변수인 Data를 찾아와서 ArrayList에 저장하기
        DBHelper dbHelper = new DBHelper(PlayerActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select player from soccer where nation = ?", new String[]{nation});
        ArrayList<String> list = new ArrayList<>();
        while(cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        db.close();

        // ListView에 Data를 출력하기 위한 Adapter 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                PlayerActivity.this, android.R.layout.simple_list_item_1, list);

        // ListView에 Adapter 연결
        ListView playerlist = (ListView)findViewById(R.id.playerlist);
        playerlist.setAdapter(adapter);

        // TextView에 넘겨받은 Nation의 값을 출력
        TextView title = (TextView)findViewById(R.id.subtitle);
        title.setText(nation);

        // Back Button을 눌렀을 때 이전 화면으로 돌아가는 Code
        Button back = (Button)findViewById(R.id.backbtn);
        back.setOnClickListener(view -> {
            finish();
        });
    }
}
