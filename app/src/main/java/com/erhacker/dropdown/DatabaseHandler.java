package com.erhacker.dropdown;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "spinnerState";
    private static final String TABLE_NAME = "labels";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SATE = "state";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_VILLAGE = "village";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_SATE + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void insertLabel(String state,String city,String village){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SATE, state);//column name, column value


        // Inserting Row
        sqLiteDatabase.insert(TABLE_NAME, null, values);//tableName, nullColumnHack, CotentValues
        sqLiteDatabase.close(); // Closing database connection

    }
    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }
}
