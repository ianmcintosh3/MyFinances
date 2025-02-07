package com.example.myfinances;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FinanceDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myFinances.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_FINANCE =
            "create table finance (_id integer primary key autoincrement,"
            + "accountNumber text not null, initialBalance text,"
            + "currentBalance text, interestRate text,"
            + "paymentAmount text);";

    public FinanceDBHelper(Context context){
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_FINANCE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(FinanceDBHelper.class.getName(),
                "Upgrading database from version" + oldVersion + "to"
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS finance");
        onCreate(db);
    }


}
