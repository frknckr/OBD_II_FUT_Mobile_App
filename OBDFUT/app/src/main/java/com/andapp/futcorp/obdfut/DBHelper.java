package com.andapp.futcorp.obdfut;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    //    private static final String DATABASE_NAME = "dedeDB";
    private static final String DATABASE_NAME = "dedeDB111";
    private static final String TABLE_RTI_1 = "rti1";
    private static final String TABLE_1 = "rti";
    private static final String TABLE_2 = "emission";
    private static final String TABLE_3 = "faults";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_1 +
                "(id INTEGER PRIMARY KEY, acceleration REAL, coolant REAL, intake REAL, speed REAL, " +
                "load REAL, throttle REAL, boost REAL, totalKm REAL)";
        String sql2 = "CREATE TABLE " + TABLE_2 + "(id INTEGER PRIMARY KEY, model TEXT, co2 INTEGER)";
        String sql3 = "CREATE TABLE " + TABLE_3 + "(id INTEGER PRIMARY KEY, status INTEGER," +
                " code TEXT, description TEXT, part TEXT)";
        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_1);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_2);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_3);
        onCreate(db);
    }

    public void insertInfo(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("acceleration", car.getAcceleration());
        values.put("coolant", car.getCoolant());
        values.put("intake", car.getIntake());
        values.put("speed", car.getSpeed());
        values.put("load", car.getLoad());
        values.put("throttle", car.getThrottle());
        values.put("boost", car.getBoost());
        values.put("totalKm", car.getTotalKm());
        db.insert(TABLE_1, null, values);
        db.close();
    }

    public HashMap<String, Float> carInfo(int id) {
        HashMap<String, Float> car = new HashMap<String, Float>();
        String selectQuery = "SELECT * FROM " + TABLE_1 + " WHERE id=" + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            car.put("acceleration", cursor.getFloat(1));
            car.put("coolant", cursor.getFloat(2));
            car.put("intake", cursor.getFloat(3));
            car.put("speed", cursor.getFloat(4));
            car.put("load", cursor.getFloat(5));
            car.put("throttle", cursor.getFloat(6));
            car.put("boost", cursor.getFloat(7));
        }
        cursor.close();
        db.close();
        return car;
    }

    public void insertCarModel(Emission emission) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("model", emission.getModel());
        values.put("co2", emission.getCo2());
        db.insert(TABLE_2, null, values);
        db.close();
    }

    public List<String> getAllModels() {
        List<String> models = new ArrayList<String>();
        String query = "SELECT * FROM " + TABLE_2;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        do {
            models.add(cursor.getString(1));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return models;
    }

    public Float getTotalKm() {
        Float totalKm = null;
        String query = "SELECT * FROM " + TABLE_1 + " WHERE id=1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            totalKm = cursor.getFloat(8);
        }
        return totalKm;
    }

    public void deleteData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_1);
    }

    public void deleteModelsData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_2);
    }

    public int getCO2(String model) {
        int co2 = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_2 + " WHERE model = " + "'" + model + "'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            co2 = cursor.getInt(2);
        }
        return co2;
    }

    public void insertFaultCode(Fault fault) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", fault.getStatus());
        values.put("code", fault.getCode());
        values.put("description", fault.getDescription());
        values.put("part", fault.getPart());
        db.insert(TABLE_3, null, values);
        db.close();
    }

    public List<String> getCodes(String part) {
        List<String> codes = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_3 + " WHERE part = " + "'" + part + "'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        do {
            codes.add(cursor.getString(2));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return codes;
    }

    public List<String> getCurrentCodes(String part) {
        List<String> codes = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_3 + " WHERE part = " + "'" + part + "'" + " AND status = 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        do {
            codes.add(cursor.getString(2));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return codes;
    }

    public List<String> getOldCodes(String part) {
        List<String> codes = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_3 + " WHERE part = " + "'" + part + "'" + " AND status = 0";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                codes.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return codes;
    }

    public List<String> getCurrentDescription(String part) {
        List<String> desc = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_3 + " WHERE part = " + "'" + part + "'" + " AND status = 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        do {
            desc.add(cursor.getString(3));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return desc;
    }

    public List<String> getOldDescription(String part) {
        List<String> desc = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_3 + " WHERE part = " + "'" + part + "'" + " AND status = 0";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                desc.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return desc;
    }

    public boolean isThereFault(String part) {
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_3 + " WHERE part = " + "'" + part + "'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            do {
                i += cursor.getInt(1);
            } while (cursor.moveToNext());
        }
        if (i > 0) {
            return true;
        } else
            return false;
    }

    public boolean isThereOldFault(String part) {
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_3 + " WHERE part = " + "'" + part + "'" + "AND status = 0";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            do {
                i ++;
            } while (cursor.moveToNext());
        }
        if (i > 0) {
            return true;
        } else
            return false;
    }

    public boolean isThereCurrentFault(String part) {
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_3 + " WHERE part = " + "'" + part + "'" + "AND status = 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            do {
                i ++;
            } while (cursor.moveToNext());
        }
        if (i > 0) {
            return true;
        } else
            return false;
    }

    public void deleteFaultCodes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_3);
    }
}
