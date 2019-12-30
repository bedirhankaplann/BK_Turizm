package com.bedirhankaplan.bkturizm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.DatePicker;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Veritabani extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BK_TURIZM";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLO_BILETLER = "biletler";
    private static final String TABLO_KISILER = "kisiler";
    private static final String ROW_ID = "id";
    private static final String ROW_GIDIS = "gidis";
    private static final String ROW_NEREDEN = "nereden";
    private static final String ROW_NEREYE = "nereye";
    private static final String ROW_AD = "ad";
    private static final String ROW_SOYAD = "soyad";
    private static final String ROW_POSTA = "posta";
    private static final String ROW_TC = "tc";
    private static final String ROW_SIFRE = "sifre";
    private static final String ROW_DOGUM = "dogum";


    public Veritabani(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_BILETLER +
                "(" + ROW_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ROW_NEREDEN +
                " TEXT NOT NULL, " +
                ROW_NEREYE +
                " TEXT NOT NULL, " +
                ROW_GIDIS +
                " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLO_KISILER +
                "(" + ROW_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ROW_AD +
                " TEXT NOT NULL, " +
                ROW_SOYAD +
                " TEXT NOT NULL, " +
                ROW_POSTA +
                " TEXT NOT NULL, " +
                ROW_TC +
                " TEXT NOT NULL, " +
                ROW_SIFRE +
                " TEXT NOT NULL, " +
                ROW_DOGUM +
                " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_BILETLER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_KISILER);
        onCreate(db);
    }
    public void BiletEkle(String nereden, String nereye, String gidis){
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            ContentValues cv = new ContentValues();
            cv.put(ROW_NEREDEN, nereden);
            cv.put(ROW_NEREYE, nereye);
            cv.put(ROW_GIDIS, gidis);
            db.insert(TABLO_BILETLER, null,cv);
        }
        catch (Exception e)
        { }
        db.close();
    }
    public List<String> kisiSorgu(String eposta, String sifre){
        List<String> kisiler = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try
        {
            String[] sutunlar = {ROW_POSTA,ROW_SIFRE};
            Cursor cursor = db.query(TABLO_KISILER, sutunlar,null,null,null,null,null);
            while (cursor.moveToNext()){ kisiler.add(cursor.getInt(0) + " - " +
                    cursor.getString(1));
            }
        }
        catch
        (Exception e)
        {

        } db.close();
        return kisiler;

    }
    public void KisiEkle(String ad, String soyad, String posta, String tc, String sifre, String dogum){
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            ContentValues cv = new ContentValues();
            cv.put(ROW_AD, ad);
            cv.put(ROW_SOYAD, soyad);
            cv.put(ROW_POSTA, posta);
            cv.put(ROW_TC, tc);
            cv.put(ROW_SIFRE, sifre);
            cv.put(ROW_DOGUM, dogum);
            db.insert(TABLO_KISILER, null,cv);
        }
        catch (Exception e)
        { }
        db.close();
    }

    public List<String> VeriListele(){
        List<String> veriler = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try
        {
            String[] stunlar = {ROW_ID,ROW_NEREDEN,ROW_NEREYE,ROW_GIDIS};
            Cursor cursor = db.query(TABLO_BILETLER, stunlar,null,null,null,null,null);
            while (cursor.moveToNext()){ veriler.add(cursor.getInt(0) + " - " +
                    cursor.getString(1) + " - " + cursor.getString(2) + " - " +
                    cursor.getString(3));
            }
        }
        catch
        (Exception e)
        {

        } db.close();
        return veriler;
    }
    public List<String> KisiListele(){
        List<String> kisiler = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try
        {
            String[] sutunlar = {ROW_ID,ROW_AD,ROW_SOYAD,ROW_POSTA,ROW_TC,ROW_SIFRE,ROW_DOGUM};
            Cursor cursor = db.query(TABLO_KISILER, sutunlar,null,null,null,null,null);
            while (cursor.moveToNext()){ kisiler.add(cursor.getInt(0) + " - " +
                    cursor.getString(1) + " - " + cursor.getString(2) + " - " +
                    cursor.getString(3) );


            }
        }
        catch
        (Exception e)
        {

        } db.close();

        return kisiler;
    }
}
