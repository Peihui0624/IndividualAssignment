package my.edu.utar.individualv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteAdapter {
    public static final String MYDATABASE_NAME = "LeaderBoardDATABASE";
    public static final String MYDATABASE_TABLE = "LeaderBoardTABLE";
    public static final int MYDATABASE_VERSION = 1;
    public static final String KEY_CONTENT = "Name";
    public static final String KEY_CONTENT2 = "Score";

    private static final String SCRIPT_CREATE_DATABASE = "create table "
            + MYDATABASE_TABLE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_CONTENT + " text not null, "
            + KEY_CONTENT2 + " int);";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    public SQLiteAdapter(Context c) {
        context = c;
    }

    public SQLiteAdapter openToRead() throws
            android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public SQLiteAdapter openToWrite() throws
            android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null,MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        sqLiteHelper.close();
    }

    public long insert(String content, int content2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CONTENT, content);
        contentValues.put(KEY_CONTENT2, content2);
        return sqLiteDatabase.insert(MYDATABASE_TABLE, null,
                contentValues);
    }
    public int deleteAll() {
        return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
    }

//    public String queueAll() {
//        String[] columns = new String[] { KEY_CONTENT , KEY_CONTENT2};
//        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns, null,
//                null, null, null, null);
//        String result = "";
//        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT);
//        int index_CONTENT2 = cursor.getColumnIndex(KEY_CONTENT2);
//        for (cursor.moveToFirst(); !(cursor.isAfterLast());
//             cursor.moveToNext()) {
//            result = result + cursor.getString(index_CONTENT) + " "
//                    + cursor.getString(index_CONTENT2)+ "\n";
//        }
//        return result;
//    }

    public String queueByScore() {
        String[] columns = new String[] { KEY_CONTENT, KEY_CONTENT2 };
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                null, null, null, null,
                KEY_CONTENT2 + " DESC"); // add the ORDER BY clause here
        String result = "";
        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT);
        int index_CONTENT2 = cursor.getColumnIndex(KEY_CONTENT2);
        for (cursor.moveToFirst(); !(cursor.isAfterLast());
             cursor.moveToNext()) {
            result = result + cursor.getString(index_CONTENT) + " "
                    + cursor.getString(index_CONTENT2)+ "\n";
        }
        return result;
    }

    public class SQLiteHelper extends SQLiteOpenHelper {
        public SQLiteHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SCRIPT_CREATE_DATABASE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
