package com.bank.basic_bank_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1123456789,'Raj',10000.00,'raj@gmail.com','XXXXXXXXXXXX5678','ABC09876543')");
        db.execSQL("insert into user_table values(2234567891,'Rudresh',786.67,'rudresh@gmail.com','XXXXXXXXXXXX6578','BCA98765432')");
        db.execSQL("insert into user_table values(3345678912,'Suresh',17899.56,'suresh@gmail.com','XXXXXXXXXXXX6785','BCE18765432')");
        db.execSQL("insert into user_table values(4456789123,'Veehal',2867.01,'veehal@gmail.com','XXXXXXXXXXXX6875','ABD86543210')");
        db.execSQL("insert into user_table values(5567891234,'Ananth',623.48,'ananth@gmail.com','XXXXXXXXXXXX7685','AED09876576')");
        db.execSQL("insert into user_table values(6678912345,'Binu',897.16,'binu@gmail.com','XXXXXXXXXXXX7586','BCA65432109')");
        db.execSQL("insert into user_table values(7789123456,'Devika',1285.00,'devika@gmail.com','XXXXXXXXXXXX7865','CAB54321098')");
        db.execSQL("insert into user_table values(8891234567,'Nandan',8934.22,'nandan@gmail.com','XXXXXXXXXXXX8765','ABC10987654')");
        db.execSQL("insert into user_table values(9912345678,'Maruthi',9348.46,'maruthi@gmail.com','XXXXXXXXXXXX8675','ABE43210987')");
        db.execSQL("insert into user_table values(8012385678,'Harsha',874.90,'harsha@gmail.com','XXXXXXXXXXXX8567','CAB21098765')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
