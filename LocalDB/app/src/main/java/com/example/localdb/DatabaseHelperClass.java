package com.example.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "employee_database";
    //Database Table name
    private static final String TABLE_NAME = "EMPLOYEE";
    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PESAN = "pesan";
    private SQLiteDatabase sqLiteDatabase;



    //Constructor
    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME +"("+ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL,"+PESAN+" TEXT NOT NULL);";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add Employee Data
    public void addEmployee(EmployeeModelClass employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.NAME, employeeModelClass.getName());
        contentValues.put(this.EMAIL, employeeModelClass.getEmail());
        contentValues.put(this.PESAN, employeeModelClass.getPesan());
        sqLiteDatabase.insert(TABLE_NAME, null,contentValues);
    }

    public List<EmployeeModelClass> getEmployeeList(){
        String sql = "select * from " + TABLE_NAME + " ORDER BY " + NAME + " DESC ";
        List<EmployeeModelClass> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String pesan = cursor.getString(3);
                storeEmployee.add(new EmployeeModelClass(id,name,email,pesan));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }
//    public List<EmployeeModelClass> getEmployee(int i){
//        String sql = "select * from " + TABLE_NAME + " WHERE " + ID + " = " + i;
//        List<EmployeeModelClass> storeEmployee = new ArrayList<>();
//        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
//        if (cursor.moveToFirst()){
//            do {
//                int id = Integer.parseInt(cursor.getString(0));
//                String name = cursor.getString(1);
//                String email = cursor.getString(2);
//                storeEmployee.add(new EmployeeModelClass(id,name,email));
//            }while (cursor.moveToNext());
//        }
//        cursor.close();
//        return storeEmployee;
//    }
    public Cursor oneData(int i){
        Cursor cur = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " WHERE " + ID + " = " + i, null);
        return cur;
    }

    public void updateEmployee(EmployeeModelClass employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,employeeModelClass.getName());
        contentValues.put(EMAIL,employeeModelClass.getEmail());
        contentValues.put(PESAN,employeeModelClass.getPesan());
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = " + String.valueOf(employeeModelClass.getId()), null);
    }

    public void deleteEmployee(int id){
        sqLiteDatabase.delete(TABLE_NAME, ID + " = " + id , null);
    }

}
