package com.example.triviaapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseClass extends SQLiteOpenHelper {
    Context context;

    public static final int DatabaseVersion = 1;
    public static final String DatabaseName = "TriviaDatabase";
    private static final String TableName = "gameResult";

    private static final String ColumnId = "id";
    private static final String ColumnDateTime = "dateTime";
    private static final String ColumnAnswer1 = "answer1";
    private static final String ColumnAnswer2 = "answer2";
    private static final String ColumnAnswer3 = "answer3";

    public DatabaseClass(@Nullable Context context) {
        super(context, DatabaseName, null, DatabaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String query = "CREATE TABLE " + TableName + " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ColumnDateTime + " TEXT, " + ColumnAnswer1 + " TEXT, " + ColumnAnswer2 + " TEXT, " + ColumnAnswer3 + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    //read data from database
    public Cursor readAllGameData() {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery("SELECT * FROM " + TableName, null, null);
        }
        return cursor;
    }

    //add data to database
    public void addGameResult(String date, String answer1, String answer2, String answer3) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ColumnDateTime, date);
        cv.put(ColumnAnswer1, answer1);
        cv.put(ColumnAnswer2, answer2);
        cv.put(ColumnAnswer3, answer3);

        long resultValue = db.insert(TableName, null, cv);

        if (resultValue == -1) {
            Toast.makeText(context, "Data could not be added", Toast.LENGTH_SHORT).show();
            Log.d("status", "not added");
        } else {
            Toast.makeText(context, "Data added successfully", Toast.LENGTH_SHORT).show();
            Log.d("status", "added");
        }
    }
}
