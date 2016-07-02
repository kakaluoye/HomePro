package com.lin.threelayersqlitemodal.commom;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by my on 2016/6/25.
 */
public class StudentSqliteOpenHelper extends SQLiteOpenHelper {


    public StudentSqliteOpenHelper(Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL("create table if not exists student (stuNo integer primary key" +
                "stuName varchar(20)," +
                "address varchar(50)," +
                "money double," +
                "age int)");
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小黑", "郑州二七", 10000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小白", "郑州金水区", 1000, 22});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"老张", "北京", 20000, 23});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"老王", "郑州二七", 100000, 25});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小黄", "郑州高新区", 10000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小亮", "东莞", 30000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小明", "上海", 40000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小红", "深圳", 50000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小黑2", "郑州二七", 10000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小白2", "郑州金水区", 1000, 22});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"老张2", "北京", 20000, 23});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"老王2", "郑州二七", 100000, 25});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小黄2", "郑州高新区", 10000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小亮2", "东莞", 30000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小明2", "上海", 40000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小红2", "深圳", 50000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小黑3", "郑州二七", 10000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小白3", "郑州金水区", 1000, 22});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"老张3", "北京", 20000, 23});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"老王3", "郑州二七", 100000, 25});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小黄3", "郑州高新区", 10000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小亮3", "东莞", 30000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小明3", "上海", 40000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小红3", "深圳", 50000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小黑4", "郑州二七", 10000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小白4", "郑州金水区", 1000, 22});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"老张4", "北京", 20000, 23});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"老王4", "郑州二七", 100000, 25});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小黄4", "郑州高新区", 10000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小亮4", "东莞", 30000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小明4", "上海", 40000, 20});
        db.execSQL("insert into student(stuName,address,money,age) values(?,?,?,?)", new Object[]{"小红4", "深圳", 50000, 20});
        Log.i("aaa", "Helper_Create执行OK了");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

