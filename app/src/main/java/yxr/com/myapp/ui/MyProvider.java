package yxr.com.myapp.ui;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import org.litepal.LitePal;

import yxr.com.myapp.sql.MySql;

public class MyProvider extends ContentProvider {
    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static UriMatcher uriMatcher;
    public static final String AUTHORITY = "yxr.com.myapp.provider";
    private SQLiteDatabase database;
    private MySql mySql;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }
    @Override
    public boolean onCreate() {
        mySql = new MySql(getContext(), "bookStore.db", null, 2);
        return true;
    }

     @Override
    public Cursor query( Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
         Cursor cursor = null;
         SQLiteDatabase readableDatabase = mySql.getReadableDatabase();
         switch(uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = readableDatabase.query("book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
                case BOOK_ITEM:
                    String s = uri.getPathSegments().get(1);
                    cursor = readableDatabase.query("book",projection,"id=?",new String[]{s},null,null,sortOrder);
                break;case CATEGORY_DIR:
                break;case CATEGORY_ITEM:
                break;
        }
        return cursor;
    }

   
    @Override
    public String getType( Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.yxr.com.myapp.provider.book";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.yxr.com.myapp.provider.book";

                case TABLE2_DIR:
                    return "vnd.android.cursor.dir/vnd.yxr.com.myapp.provider.category";
                case TABLE2_ITEM:
                    return "vnd.android.cursor.item/vnd.yxr.com.myapp.provider.category";

        }
        return null;
    }

   
    @Override
    public Uri insert( Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete( Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update( Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
