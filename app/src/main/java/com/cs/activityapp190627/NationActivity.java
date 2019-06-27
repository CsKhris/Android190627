package com.cs.activityapp190627;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class NationActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation);

        Button call = (Button)findViewById(R.id.call);
        call.setOnClickListener(view -> {
            // 실시간 권한 Check(6.0부터 추가)
            if(ContextCompat.checkSelfPermission(NationActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-3539-3379"));
                startActivity(intent);
            }
        });

        listView = (ListView)findViewById(R.id.nationlist);

        // Database 사용을 위한 객체 생성
        DBHelper dbHelper = new DBHelper(NationActivity.this);

        // Data를 조회해 올 Database 객체 생성
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Soccer Table에서 Nation을 전부 가져오기
        Cursor cursor = db.rawQuery("select nation from soccer group by nation" , new String[]{});

        // 읽은 Data를 순회하면서 List에 추가
        list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        db.close();

        // ListViwe에 출력할 Adapter 만들기
        adapter = new ArrayAdapter<>(NationActivity.this, android.R.layout.simple_list_item_1, list);

        // ListView 와 Adapter 연결
        listView.setAdapter(adapter);

        // ListView 에서 항목을 Click 했을 때 처리 하는 Handler
        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){

                // 선택한 Data 찾아오기
                String nation = list.get(i);

                // 하위 Data를 출력할 PlayerActivity의 Intent를 생성하고 Data를 설정하고 출력
                Intent intent = new Intent(NationActivity.this, PlayerActivity.class);

                intent.putExtra("nation", nation);
                startActivity(intent);

            }
        });
    }
}
