package com.example.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
    //declaration des variables
    public static final String DB_NAME = "GSB.db";
    public static final String DB_TABLE = "FRAIS";
    public static final String ID = "ID"; //ce sera les colonnes de la table frais
    public static final String TYPE = "TYPE";
    public static final String QUANTITE = "QUANTITE";
    public static final String DATE = "DATE";
    public static final String MONTANT = "MONTANT";
    public static final String DATEACTU = "DATEACTU";
    public static final String LIBELLE = "LIBELLE";

    //on cree une variable qui contient la requete sql pr la creation de la table
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TYPE + " TEXT," + QUANTITE + " INTEGER," + DATE + " TEXT," + MONTANT + " REAL," + LIBELLE + " TEXT," + DATEACTU + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    public SQLHelper(Context context) {

        super(context, DB_NAME, null, 1);//permet d'acceder aux membres de la classe mère

    }

    //constructeur de la classe
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//methode venant de SQLLite et qui permet d'exceuter une rquete SQL

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    //destructeur de la classe
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    //methode qui retourne un resultat de type booleen
    //insertData insert ds la bdd les données type et quantite renseignees par l'utilisateur
    public boolean insertData(String type, Integer quantite, String date, double montant, String libelle) {
        //on cree une variable de type sqllitedatabase pr pouvoir y acceder
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPE, type);
        contentValues.put(QUANTITE, quantite);
        contentValues.put(DATE, date);
        contentValues.put(MONTANT, montant);
        contentValues.put(LIBELLE, libelle);
        //insert sert a inserer des donnees, elle insere ds notre table contentValue les contenus des variables que l'utilisateur renseigne
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }

    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + DB_TABLE;
        //cursor: type, pointeur: pr parcourir les lignes ds les resultats de la requete. null car pas de where
        Cursor pointeur = db.rawQuery(query, null);
        return pointeur;

    }

    public boolean deleteData(Integer ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(DB_TABLE, "ID=" + ID, null);

        return result != -1;

    }

    public Cursor fetchAllFrais() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor Cursor = db.query(DB_TABLE, new String[]{"rowid _id",LIBELLE,
                            ID, DATE, DATEACTU, MONTANT, QUANTITE},
                    null, null, null, null, null);

            if (Cursor != null) {
                Cursor.moveToFirst();
            }
            return Cursor;
    }


    public SQLHelper open() throws SQLException {

        SQLiteDatabase db =this.getWritableDatabase();
        return this;

    }
}
