package com.cs.activityapp190627;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    // 생성자 - Context를 넘겨받아서 상위 Class를 호출
    public DBHelper(Context context){
        super(context, "database.slqite", null, 1);
    }

    // 처음 사용하려고 했을 때 호출되는 Method
    @Override
    public void onCreate(SQLiteDatabase db){

        //
        String sql = "create table soccer(" + "_id integer primary key autoincrement, " + "nation text, player text)";

        //
        db.execSQL(sql);

        //
        db.execSQL("insert into soccer(nation, player) " + "values('대한민국', '차범근')" );
        db.execSQL("insert into soccer(nation, player) " + "values('대한민국', '손흥민')" );
        db.execSQL("insert into soccer(nation, player) " + "values('대한민국', '황의조')" );
        db.execSQL("insert into soccer(nation, player) " + "values('대한민국', '박지성')" );
        db.execSQL("insert into soccer(nation, player) " + "values('영국', 'Gerrad')" );
        db.execSQL("insert into soccer(nation, player) " + "values('일본', 'Nakata')" );
        db.execSQL("insert into soccer(nation, player) " + "values('대한민국', '황희찬')" );
        db.execSQL("insert into soccer(nation, player) " + "values('아르헨티나', 'Messi')" );
        db.execSQL("insert into soccer(nation, player) " + "values('포르투갈', 'Ronaldo')" );
        db.execSQL("insert into soccer(nation, player) " + "values('프랑스', 'Geresmen')" );

        }

    // Database Version이 변경 되었을 때 호출되는 Method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Table을 삭제하고 다시 생성
        db.execSQL("drop table soccer");
        onCreate(db);

    }
}
