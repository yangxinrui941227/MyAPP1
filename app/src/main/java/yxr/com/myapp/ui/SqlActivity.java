package yxr.com.myapp.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

import yxr.com.myapp.R;
import yxr.com.myapp.entity.Book;
import yxr.com.myapp.sql.MySql;
import yxr.com.myapp.util.Logger;

public class SqlActivity extends AppCompatActivity implements View.OnClickListener{
    private MySql mySql;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_layout);
        mySql = new MySql(this, "bookStore.db", null, 2);
        Button create = (Button) findViewById(R.id.btn_create);
        create.setOnClickListener(this);
         Button add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(this);
    Button btn_create_litepal = (Button) findViewById(R.id.btn_create_litepal);
        btn_create_litepal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_create:
                mySql.getWritableDatabase();
                break;
            case R.id.btn_add:
                SQLiteDatabase writableDatabase = mySql.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name","quan huang");
                values.put("author","佚名");
                values.put("pages",45);
                values.put("price",9.9);
                writableDatabase.insert("book",null,values);
                read(writableDatabase.query("book",null,null,null,null,null,null));
                values.put("price",19.9);
                writableDatabase.update("book",values,"name=?",new String[]{"quan huang"});
                read(writableDatabase.query("book",null,null,null,null,null,null));

                break;
            case R.id.btn_create_litepal:
                SQLiteDatabase database = LitePal.getDatabase();
                Book book = new Book();
                book.setName("quanhuang");
                book.setAuthor("wu");
                book.setPages(50);
                book.setPrice(11.11);
                book.save();
                book.updateAll("name=? ","quanhuang");
                List<Book> all = LitePal.findAll(Book.class);
                for (Book b : all) {
                    Logger.log("Book",b.toString());
                }

                break;
        }
    }

    private void read(Cursor cursor){
        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Logger.log("name",name);
                    Logger.log("author",author);
                    Logger.log("pages",pages+"");
                    Logger.log("price",price+"");

                }while (cursor.moveToNext());
                cursor.close();
            }
        }
    }
}
