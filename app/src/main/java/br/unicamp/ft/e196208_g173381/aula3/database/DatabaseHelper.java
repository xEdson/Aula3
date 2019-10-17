package br.unicamp.ft.e196208_g173381.aula3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "EXEMPLO";
    private static final int DB_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("CREATE TABLE tabela " +
                "(_id INTEGER PRIMARY KEY);");*/


        db.execSQL("CREATE TABLE tabela " +
                "(_id INTEGER PRIMARY KEY, " +
                "Texto Text);");

        db.execSQL("CREATE TABLE alunos " +
                "(_id INTEGER PRIMARY KEY, Nome Text, TentativaGlobal INTEGER, TentativaSelf INTEGER, Acerto INTEGER,  Erro INTEGER);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2){
            db.execSQL("ALTER TABLE tabela " +
                    "ADD COLUMN texto;");
        }

    }
}

