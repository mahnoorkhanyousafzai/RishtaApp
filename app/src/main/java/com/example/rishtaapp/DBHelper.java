package com.example.rishtaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RishtaApp.db";
    public static final String TABLE_NAME = "users";
    public static final String TABLE_NAME_CLIENTS = "clients";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "DATETIME";

    // Clients table columns
    // Clients table columns
    public static final String COL_CLIENT_ID = "ID";
    public static final String COL_CLIENT_FULLNAME = "FULLNAME";
    public static final String COL_CLIENT_GENDER = "GENDER";
    public static final String COL_CLIENT_AGE = "AGE";
    public static final String COL_CLIENT_HEIGHT = "HEIGHT";
    public static final String COL_CLIENT_CAST = "CAST_OF";
    public static final String COL_CLIENT_RELIGION = "RELIGION";
    public static final String COL_CLIENT_SECT = "SECT";
    public static final String COL_CLIENT_EDUCATION = "EDUCATION";
    public static final String COL_CLIENT_HOBBIES = "HOBBIES";
    public static final String COL_CLIENT_FAMILY_DETAILS = "FAMILY_DETAILS";
    public static final String COL_CLIENT_MARITAL_STATUS = "MARITAL_STATUS";
    public static final String COL_CLIENT_RESIDENCE = "RESIDENCE";
    public static final String COL_CLIENT_PHOTO = "PHOTO";
    public static final String COL_CLIENT_PROFESSION = "PROFESSION";
    public static final String COL_CLIENT_JOB = "JOB";
    public static final String COL_CLIENT_COMPLEXION = "COMPLEXION";
    public static final String COL_CLIENT_CHILDREN = "CHILDREN";
    public static final String COL_CLIENT_MINDSET = "MINDSET";
    public static final String COL_CLIENT_REFER_BY = "REFERED_BY";
    public static final String COL_CLIENT_STATUS = "STATUS";
    public static final String COL_CLIENT_REQUIREMENT = "REQUIREMENT";
    public static final String COL_CLIENT_CUSTOMER_FEE = "CUSTOMER_FEE";
    public static final String COL_CLIENT_AFTER_RISHTA_AMOUNT = "AFTER_RISHTA_AMOUNT";
    public static final String COL_CLIENT_CONTACT_DETAILS = "CONTACT_DETAILS";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT UNIQUE, PASSWORD TEXT, DATETIME DATETIME DEFAULT CURRENT_TIMESTAMP)");

        // Create clients table
        db.execSQL("CREATE TABLE " + TABLE_NAME_CLIENTS + "("
                + COL_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CLIENT_FULLNAME + " TEXT, "
                + COL_CLIENT_GENDER + " TEXT, "
                + COL_CLIENT_AGE + " INTEGER, "
                + COL_CLIENT_HEIGHT + " TEXT, "
                + COL_CLIENT_CAST + " TEXT, "
                + COL_CLIENT_RELIGION + " TEXT, "
                + COL_CLIENT_SECT + " TEXT, "
                + COL_CLIENT_EDUCATION + " TEXT, "
                + COL_CLIENT_HOBBIES + " TEXT, "
                + COL_CLIENT_FAMILY_DETAILS + " TEXT, "
                + COL_CLIENT_MARITAL_STATUS + " TEXT, "
                + COL_CLIENT_RESIDENCE + " TEXT, "
                + COL_CLIENT_PHOTO + " TEXT, "
                + COL_CLIENT_PROFESSION + " TEXT, "
                + COL_CLIENT_JOB + " TEXT, "
                + COL_CLIENT_COMPLEXION + " TEXT, "
                + COL_CLIENT_CHILDREN + " TEXT, "
                + COL_CLIENT_MINDSET + " TEXT, "
                + COL_CLIENT_REFER_BY + " TEXT, "
                + COL_CLIENT_STATUS + " TEXT, "
                + COL_CLIENT_REQUIREMENT + " TEXT, "
                + COL_CLIENT_CUSTOMER_FEE + " REAL, "
                + COL_CLIENT_AFTER_RISHTA_AMOUNT + " REAL, "
                + COL_CLIENT_CONTACT_DETAILS + " TEXT "
                + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CLIENTS);
        onCreate(db);
    }

    // Insert Data
    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public boolean insertClient(String fullname, String gender, int age, String height, String cast, String religion,
                                String sect, String education, String hobbies, String familyDetails, String maritalStatus,
                                String residence, String photo, String profession, String job, String complexion,
                                String children, String mindset, String referBy, String status, String requirement,
                                double customerFee, double afterRishtaAmount,String contactdetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CLIENT_FULLNAME, fullname);
        contentValues.put(COL_CLIENT_GENDER, gender);
        contentValues.put(COL_CLIENT_AGE, age);
        contentValues.put(COL_CLIENT_HEIGHT, height);
        contentValues.put(COL_CLIENT_CAST, cast);
        contentValues.put(COL_CLIENT_RELIGION, religion);
        contentValues.put(COL_CLIENT_SECT, sect);
        contentValues.put(COL_CLIENT_EDUCATION, education);
        contentValues.put(COL_CLIENT_HOBBIES, hobbies);
        contentValues.put(COL_CLIENT_FAMILY_DETAILS, familyDetails);
        contentValues.put(COL_CLIENT_MARITAL_STATUS, maritalStatus);
        contentValues.put(COL_CLIENT_RESIDENCE, residence);
        contentValues.put(COL_CLIENT_PHOTO, photo);
        contentValues.put(COL_CLIENT_PROFESSION, profession);
        contentValues.put(COL_CLIENT_JOB, job);
        contentValues.put(COL_CLIENT_COMPLEXION, complexion);
        contentValues.put(COL_CLIENT_CHILDREN, children);
        contentValues.put(COL_CLIENT_MINDSET, mindset);
        contentValues.put(COL_CLIENT_REFER_BY, referBy);
        contentValues.put(COL_CLIENT_STATUS, status);
        contentValues.put(COL_CLIENT_REQUIREMENT, requirement);
        contentValues.put(COL_CLIENT_CUSTOMER_FEE, customerFee);
        contentValues.put(COL_CLIENT_AFTER_RISHTA_AMOUNT, afterRishtaAmount);
        contentValues.put(COL_CLIENT_CONTACT_DETAILS, contactdetails);
        long result = db.insert(TABLE_NAME_CLIENTS, null, contentValues);
        return result != -1;
    }

    // Check if the same client data exists
    public boolean checkIfClientExists(String fullname, String gender, int age, String height, String cast, String religion,
                                       String sect, String education, String maritalStatus, String profession) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_CLIENTS +
                        " WHERE FULLNAME = ? AND GENDER = ? AND AGE = ? AND HEIGHT = ? AND CAST_OF = ? AND RELIGION = ? " +
                        "AND SECT = ? AND EDUCATION = ? AND MARITAL_STATUS = ? AND PROFESSION = ?",
                new String[]{fullname, gender, String.valueOf(age), height, cast, religion, sect, education, maritalStatus, profession});

        return cursor.getCount() > 0;
    }

    public boolean deleteClient(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME_CLIENTS, "ID = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }
    public Cursor listClients() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME_CLIENTS, null);
    }

    public boolean updateClientStatus(int id, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CLIENT_STATUS, status);
        int result = db.update(TABLE_NAME_CLIENTS, contentValues, "ID = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }


    // Search clients by multiple fields: name, age, gender, ID, and marital status
    public Cursor searchClients(String name, Integer age, String gender, Integer id, String maritalStatus) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Building the query
        String query = "SELECT * FROM " + TABLE_NAME_CLIENTS + " WHERE 1=1";  // Always true condition to start appending

        // Adding conditions for each field that is not null
        if (name != null && !name.isEmpty()) {
            query += " AND FULLNAME LIKE '%" + name + "%'";
        }
        if (age != null) {
            query += " AND AGE = " + age;
        }
        if (gender != null && !gender.isEmpty()) {
            query += " AND GENDER = '" + gender + "'";
        }
        if (id != null) {
            query += " AND ID = " + id;
        }
        if (maritalStatus != null && !maritalStatus.isEmpty()) {
            query += " AND MARITAL_STATUS = '" + maritalStatus + "'";
        }

        return db.rawQuery(query, null);
    }



    // Check if username exists
    public boolean checkUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ? AND PASSWORD = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }
    public String getPasswordByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT PASSWORD FROM " + TABLE_NAME + " WHERE USERNAME = ?", new String[]{username});

        if (cursor != null && cursor.moveToFirst()) {
            String password = cursor.getString(0);  // Get the password column value
            cursor.close();
            return password;
        } else {
            return null;  // Username not found
        }
    }


}