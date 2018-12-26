package yxr.com.myapp.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import yxr.com.myapp.util.AlterMessage;

public class MySql extends SQLiteOpenHelper {
    private Context context;
    public static String sql = "create table book(id integer primary key autoincrement,author text,price real,pages integer,name text)";
    public static String sql2 = "create table category(id integer primary key autoincrement,category_name text,catagory_code text)";
    public MySql( Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        db.execSQL(sql2);
        AlterMessage.toast(context,"table has created 1,2");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists book");
        db.execSQL("drop table if exists category");
        onCreate(db);
    }
}
